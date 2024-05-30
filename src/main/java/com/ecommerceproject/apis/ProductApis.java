package com.ecommerceproject.apis;

import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.payload.request.ProductProcessRequest;
import com.ecommerceproject.payload.response.ProductListResponse;
import com.ecommerceproject.payload.response.ResponseObject;
import com.ecommerceproject.service.ProductService;
import com.ecommerceproject.service.redis.ProductRedisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductApis {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRedisService productRedisService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseObject> getOneproduct(@PathVariable String id) throws Exception {
        ProductDTO productDTO = productService.getOne(Integer.parseInt(id));
        return ResponseEntity.ok().body(ResponseObject.builder()
                .message("Get product successfully")
                .data(productDTO)
                .httpStatus(HttpStatus.OK)
                .build());
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseObject> updateproduct(@RequestBody @Valid ProductProcessRequest product) throws Exception {
        productRedisService.clear();
        ProductDTO newproductDTO = productService.update(product);
        return ResponseEntity.ok().body(ResponseObject.builder()
                .httpStatus(HttpStatus.OK)
                .message("Update product successfully")
                .data(newproductDTO)
                .build());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addproduct(@RequestBody @Valid ProductProcessRequest product) throws Exception {
        ProductDTO newproductDTO = productService.add(product);
        productRedisService.clear();
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .data(newproductDTO)
                        .message("Add new product successfully")
                        .httpStatus(HttpStatus.CREATED)
                        .build()
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteproduct(@RequestParam String id) throws Exception {
        productService.delete(Integer.parseInt(id));
        productRedisService.clear();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("Delete product successfully")
                        .build()
        );
    }

    @GetMapping("/get_all")
    public ResponseEntity<ResponseObject> getByTitle(@RequestParam(value = "page_num", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "page_size", required = false, defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "sort_field", required = false, defaultValue = "") String sortField,
                                                     @RequestParam(value = "sort_dir", required = false, defaultValue = "") String sortDir,
                                                     @RequestParam(value = "title", required = false, defaultValue = "") String title,
                                                     @RequestParam(value = "category_id", required = false, defaultValue = "0") Integer categoryId,
                                                     @RequestParam(value = "min_price", required = false, defaultValue = "0") Integer priceMin,
                                                     @RequestParam(value = "max_price", required = false, defaultValue = "1000000000") Integer priceMax) throws Exception {
        Pageable pageable;
        if(!sortField.isEmpty()) {
            Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                    : Sort.by(sortField).descending();
            pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        }
        else {
            pageable = PageRequest.of(pageNum - 1, pageSize);
        }
        ProductListResponse response = productRedisService.getAllProducts(title, sortField, categoryId, pageable);
        if(response == null) {
             response = productService.getByOptions(
                    title,
                    categoryId,
                    priceMin,
                    priceMax,
                    pageable);
            productRedisService.saveAllProducts(response, title, sortField,categoryId, pageable);
        }
        return ResponseEntity.ok().body(
                ResponseObject.builder()
                        .message("Get product list successfully")
                        .data(response)
                        .httpStatus(HttpStatus.OK)
                        .build()
        );
    }


}

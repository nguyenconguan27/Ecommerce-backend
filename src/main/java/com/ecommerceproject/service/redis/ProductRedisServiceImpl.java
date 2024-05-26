package com.ecommerceproject.service.redis;

import com.ecommerceproject.dto.ProductDTO;
import com.ecommerceproject.payload.response.ProductListResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRedisServiceImpl implements ProductRedisService{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // tạo key từ tham số trong api
    private String getKey(String title,
                          String sortField,
                          Integer categoryId,
                          Pageable pageRequest) {
        int pageNum = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();
        Sort sort = pageRequest.getSort();
        String sortDir = sort.getOrderFor(sortField)
                .getDirection() == Sort.Direction.ASC ? "asc" : "desc";
        String key = String.format("all_products:%d%d%s%s", pageNum, pageSize, sortField,sortDir);
        return key;
    }
    @Override
    public void clear() {
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();

        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();

        DefaultStringRedisConnection defaultStringRedisConnection = new DefaultStringRedisConnection(redisConnection, redisSerializer);

        defaultStringRedisConnection.flushAll();
    }

    @Override
    public ProductListResponse getAllProducts(String title, String sortField,Integer categoryId, Pageable pageRequest) throws JsonProcessingException {
        String key = this.getKey(title, sortField, categoryId, pageRequest);
        String json  = (String) redisTemplate.opsForValue().get(key);
        ProductListResponse productListResponse = json != null ? objectMapper.readValue(json, new TypeReference<ProductListResponse>() {
        }) : null;
        return productListResponse;
    }

    @Override
    public void saveAllProducts(ProductListResponse productListResponse, String title, String sortField,Integer categoryId, Pageable pageRequest) throws JsonProcessingException {
    String key = this.getKey(title, sortField, categoryId, pageRequest);
    String json = objectMapper.writeValueAsString(productListResponse);
    redisTemplate.opsForValue().set(key, json);
    }
}

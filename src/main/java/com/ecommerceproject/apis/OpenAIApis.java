package com.ecommerceproject.apis;

import com.ecommerceproject.openAI.ChatGPTRequest;
import com.ecommerceproject.openAI.ChatGPTService;
import com.ecommerceproject.openAI.ChatGptResponse;
import com.ecommerceproject.payload.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class OpenAIApis {

    private final ChatGPTService chatGPTService;

    @PostMapping("/chat")
    public ResponseEntity<ResponseObject> sendPrompt(@RequestParam("prompt") String prompt) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Chatbot response")
                        .httpStatus(HttpStatus.OK)
                        .data(/*chatGPTService.chat(prompt)*/ "abs")
                        .build()
        );
    }
}
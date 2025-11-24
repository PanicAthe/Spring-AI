package com.example.demo.controller.ch09_vector_store_chat_memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ch09_vector_store_chat_memory.ChatMemoryService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/ai/vector_store_chat_memory")
@Slf4j
public class ChatMemoryController {
  // ##### 필드 ##### 
  @Autowired
  private ChatMemoryService aiService;
  
  // ##### 요청 매핑 메소드 #####
  @PostMapping(
    value = "/chat",
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    produces = MediaType.TEXT_PLAIN_VALUE
  )
  public String vectorStoreChatMemory(
      @RequestParam("question") String question, HttpSession session) {
    String answer = aiService.chat(question, session.getId());
    return answer;
  }    
}

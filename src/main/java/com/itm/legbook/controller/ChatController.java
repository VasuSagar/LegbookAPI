package com.itm.legbook.controller;

import com.itm.legbook.dto.MessageRequest;
import com.itm.legbook.service.ChatService;
import com.itm.legbook.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://cs.neonsolutions.xyz")
@AllArgsConstructor
public class ChatController
{
    private final ChatService chatService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message/{recipentId}")
    public Long broadcastNews(@DestinationVariable Long recipentId, MessageRequest messageRequest) {
        System.out.println("MESSAGE:"+messageRequest.getMessage());
        simpMessagingTemplate.convertAndSend("/topic/reply/"+recipentId,messageRequest.getMessage());
       return chatService.save(messageRequest.getMessage(),recipentId,messageRequest.getFromLogin());
    }

}

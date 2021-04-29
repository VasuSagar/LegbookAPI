package com.itm.legbook.service;

import com.itm.legbook.dto.MessageResponse;
import com.itm.legbook.dto.PostRequest;
import com.itm.legbook.dto.PostResponse;
import com.itm.legbook.mapper.ChatMapper;
import com.itm.legbook.mapper.PostMapper;
import com.itm.legbook.model.Message;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
import com.itm.legbook.repository.ChatRepository;
import com.itm.legbook.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ChatService
{
    private final AuthService authService;
    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;

    public Long save(String message,Long recipentId,Long senderId)
    {
        //get user who created this message
        //User currentUser=authService.getCurrentUser();
       Message message1=chatRepository.save(chatMapper.map(message,senderId,recipentId));
        return message1.getMessageId();
    }

    @Transactional(readOnly = true)
    public List<Message> getAllChats(Long senderId, Long recipentId)
    {
       return chatRepository.getChats(senderId,recipentId).stream().collect(Collectors.toList());
    }
}

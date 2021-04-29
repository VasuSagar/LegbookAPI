package com.itm.legbook.mapper;

import com.itm.legbook.dto.PostResponse;
import com.itm.legbook.model.Message;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
import com.itm.legbook.dto.PostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatMapper
{

    @Mapping(target = "senderId",source = "userId")
    @Mapping(target = "recipentId",source = "recipentId")
    @Mapping(target = "messageTime",expression = "java(java.time.Instant.now())")
    @Mapping(target = "messageText",source = "message")
    Message map(String message, Long userId, Long recipentId);


}

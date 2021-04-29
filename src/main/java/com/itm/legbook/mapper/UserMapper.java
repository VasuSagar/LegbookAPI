package com.itm.legbook.mapper;

import com.itm.legbook.dto.CommentRequest;
import com.itm.legbook.dto.UserResponse;
import com.itm.legbook.model.Comment;
import com.itm.legbook.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userName",expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(target = "userId",expression = "java(user.getUserId())")
    UserResponse mapToDto(User user);
}

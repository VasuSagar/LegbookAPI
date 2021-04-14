package com.itm.legbook.mapper;

import com.itm.legbook.dto.CommentRequest;
import com.itm.legbook.model.Comment;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper
{
    @Mapping(target = "commentId",ignore = true)
    @Mapping(target = "text",source = "commentRequest.text")
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "post",source = "post")
    @Mapping(target = "user",source = "user")
    Comment map(CommentRequest commentRequest, Post post, User user);

    @Mapping(target = "postId",expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName",expression = "java(comment.getUser().getFirstName() + \" \" + comment.getUser().getLastName())")
    @Mapping(target = "userId",expression = "java(comment.getUser().getUserId())")
    CommentRequest mapToDto(Comment comment);
}

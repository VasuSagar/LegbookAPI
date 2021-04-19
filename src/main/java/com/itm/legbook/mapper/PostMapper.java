package com.itm.legbook.mapper;

import com.itm.legbook.dto.PostResponse;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
import com.itm.legbook.dto.PostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper
{
    PostMapper INSTANCE= Mappers.getMapper(PostMapper.class);

    @Mapping(target = "user",source = "user")
    @Mapping(target = "description",source = "postRequest.description")
    @Mapping(target = "createdDate",expression = "java(java.time.Instant.now())")
    @Mapping(target = "likeCount",constant = "0")
    Post map(PostRequest postRequest,User user);

    @Mapping(target = "postId",source = "post.postId")
    @Mapping(target = "description",source = "post.description")
    @Mapping(target = "userId",source = "post.user.userId")
    @Mapping(target = "createdDate",source = "post.createdDate")
    @Mapping(target = "userName",expression = "java(post.getUser().getFirstName() + \" \" + post.getUser().getLastName())")
    @Mapping(target = "isLikedByMe",source = "count")
    PostResponse mapToDto(Post post,int count);
}

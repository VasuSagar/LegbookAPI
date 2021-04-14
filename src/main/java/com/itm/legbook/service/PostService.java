package com.itm.legbook.service;

import com.itm.legbook.dto.PostRequest;
import com.itm.legbook.dto.PostResponse;
import com.itm.legbook.exception.LegBookException;
import com.itm.legbook.mapper.PostMapper;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
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
public class PostService {

    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public Post save(PostRequest postRequest)
    {
        //get user who created this post
        User currentUser=authService.getCurrentUser();
        Post post=postRepository.save(postMapper.map(postRequest,currentUser));
        return post;
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId)
    {
        Post post=postRepository.findById(postId).orElseThrow(()->new LegBookException(postId.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts()
    {
        return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }
}

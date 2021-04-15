package com.itm.legbook.service;

import com.itm.legbook.dto.CommentRequest;
import com.itm.legbook.exception.LegBookException;
import com.itm.legbook.mapper.CommentMapper;
import com.itm.legbook.model.Comment;
import com.itm.legbook.model.Post;
import com.itm.legbook.repository.CommentRepository;
import com.itm.legbook.repository.PostRepository;
import com.itm.legbook.repository.UserRepositroy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService
{
    private final PostRepository postRepository;
    private final UserRepositroy userRepositroy;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentRequest save(CommentRequest commentRequest)
    {
        Post post=postRepository.findById(commentRequest.getPostId()).orElseThrow(()->new LegBookException("No post found with post id:"+commentRequest.getPostId().toString()));
        //Comment comment=commentMapper.map(commentRequest,post,authService.getCurrentUser());
     Comment com1=  commentRepository.save(commentMapper.map(commentRequest,post,authService.getCurrentUser()));
       System.out.println("COMMENT:"+com1);

       CommentRequest fin=commentMapper.mapFinal(com1,post);

       return fin;
    }

    public List<CommentRequest> getAllCommentsForPost(Long postId)
    {
        Post post=postRepository.findById(postId).orElseThrow(()->new LegBookException("post not found"));
        return commentRepository.findByPost(post).stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }
}

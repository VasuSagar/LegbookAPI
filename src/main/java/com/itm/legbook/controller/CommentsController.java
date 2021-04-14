package com.itm.legbook.controller;

import com.itm.legbook.dto.CommentRequest;
import com.itm.legbook.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://legbook.neonsolutions.xyz")
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController
{
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentRequest commentRequest)
    {
        commentService.save(commentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentRequest>> getAllCommentsForPost(@PathVariable Long postId)
    {
       return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllCommentsForPost(postId));
    }

    

}

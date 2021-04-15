package com.itm.legbook.controller;

import com.itm.legbook.dto.PostRequest;
import com.itm.legbook.dto.PostResponse;
import com.itm.legbook.model.Post;
import com.itm.legbook.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://cs.neonsolutions.xyz")
@RequestMapping("/api/posts/v1")
@AllArgsConstructor
public class PostController
{
    private final PostService postService;
    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest)
    {
        Post post=postService.save(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
        //return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId)
    {
        return  postService.getPost(postId);
    }

    @GetMapping("/")
    public List<PostResponse> getAllPosts()
    {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public void getPostsByUserId(String userId)
    {
        //return postService.getPostsByUserId(userId);
    }

}

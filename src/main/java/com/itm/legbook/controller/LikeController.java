package com.itm.legbook.controller;

import com.itm.legbook.dto.LikeDto;
import com.itm.legbook.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://legbook.neonsolutions.xyz")
@RequestMapping("/api/likes")
@AllArgsConstructor
public class LikeController
{
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> assginLike(@RequestBody LikeDto likeDto)
    {
        likeService.assignLike(likeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

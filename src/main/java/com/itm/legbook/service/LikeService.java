package com.itm.legbook.service;

import com.itm.legbook.dto.LikeDto;
import com.itm.legbook.exception.LegBookException;
import com.itm.legbook.model.Like;
import com.itm.legbook.model.Post;
import com.itm.legbook.repository.LikeRepository;
import com.itm.legbook.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;




@Service
@AllArgsConstructor
public class LikeService
{

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final AuthService authService;



    @Transactional
    public int assignLike(LikeDto likeDto)
    {
        Post post=postRepository.findById(likeDto.getPostId()).orElseThrow(()->new LegBookException("post not found with id:"+likeDto.getPostId()));
        Optional<Like> likeByPostAndUser=likeRepository.findTopByPostAndUser(post,authService.getCurrentUser());
        System.out.println("LIKKE"+likeByPostAndUser);
//        if(likeByPostAndUser.isPresent() && likeByPostAndUser.get().getUser().getUserId().equals(authService.getCurrentUser().getUserId()))
//        {
//            throw new LegBookException("You already liked this post by userId:"+authService.getCurrentUser().getUserId());
//        }
        //if user has already liked this post
        if(likeByPostAndUser.isPresent())
        {
            System.out.println("IF");
            //throw new LegBookException("You already liked this post by userId:"+authService.getCurrentUser().getUserId());

            //decrease like by 1
            post.setLikeCount(post.getLikeCount()-1);

            //remove existing like entry from like table
            likeRepository.deleteLikeByUserIdAndPostId(likeByPostAndUser.get().getUser().getUserId(),likeByPostAndUser.get().getPost().getPostId());
            postRepository.save(post);
            //return 0-for like Added 1-for like removed
            return 1;
        }
        else
            {

            System.out.println("ELSE");
            //if user has not liked the post
            //increase like by 1
            post.setLikeCount(post.getLikeCount()+1);
            likeRepository.save(mapToLike(likeDto,post));
            postRepository.save(post);
            return 0;
        }


    }

    private Like mapToLike(LikeDto likeDto, Post post) {
        return Like.builder()
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}

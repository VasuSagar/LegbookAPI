package com.itm.legbook.dto;

import com.itm.legbook.model.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse
{
    private Long postId;
    private String description;
    //private List<Long> imgId;
    private Long userId;
    private Instant createdDate;
    private String userName;
    private Integer likeCount;
    private Integer commentCount;
    private Integer isLikedByMe;
}

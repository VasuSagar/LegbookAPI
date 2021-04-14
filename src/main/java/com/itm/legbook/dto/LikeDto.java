package com.itm.legbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto
{
    private Long likeId;
    private String userName;
    private Long postId;
    private String userId;
}

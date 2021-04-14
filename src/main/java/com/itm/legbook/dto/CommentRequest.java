package com.itm.legbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest
{
    private Long commentId;
    private Long postId;
    private Instant createdDate;
    private String text;
    private Long userId;
    private String userName;
}

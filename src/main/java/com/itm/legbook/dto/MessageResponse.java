package com.itm.legbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
    private Long message_id;
    private String message_text;
    private Instant message_time;
    private Long sender_id;
    private Long recipent_id;
}

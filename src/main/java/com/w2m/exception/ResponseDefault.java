package com.w2m.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDefault {
    private String message;
    private LocalDateTime date= LocalDateTime.now();
}

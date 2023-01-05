package com.masai.exception;

import lombok.*;

import javax.validation.constraints.*;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorDetails {

    private LocalDateTime localDateTime;
    private String message;
    private String details;

}

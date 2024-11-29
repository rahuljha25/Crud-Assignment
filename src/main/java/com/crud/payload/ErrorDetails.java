package com.crud.payload;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String errorMessage;
    private Date date;
    private String webRequest;
}

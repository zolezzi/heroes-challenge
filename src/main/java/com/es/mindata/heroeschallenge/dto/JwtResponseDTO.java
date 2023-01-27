package com.es.mindata.heroeschallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {

    private String username;
    private String token;
}

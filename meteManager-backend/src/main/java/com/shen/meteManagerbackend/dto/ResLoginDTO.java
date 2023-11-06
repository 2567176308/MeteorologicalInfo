package com.shen.meteManagerbackend.dto;

import com.shen.meteManagerbackend.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResLoginDTO {
    private String token;
    private String userName;
    private Role role;
}

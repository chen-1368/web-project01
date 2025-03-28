package com.chen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInfo {
    private Integer id;
    private String name;
    private String username;
    private String token;
}

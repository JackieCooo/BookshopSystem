package com.bookshop.backend.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String mail;
    private String gender;
    private String password;
    private byte[] pic;
}

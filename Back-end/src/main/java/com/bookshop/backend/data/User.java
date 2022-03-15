package com.bookshop.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String mail;
    private String gender;
    private String password;
}

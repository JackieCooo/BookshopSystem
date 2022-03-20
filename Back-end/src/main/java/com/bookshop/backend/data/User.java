package com.bookshop.backend.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
    private Integer id = null;
    private String name = null;
    private String phone = null;
    private String mail = null;
    private String gender = null;
    private String password = null;
    private byte[] pic = null;
}

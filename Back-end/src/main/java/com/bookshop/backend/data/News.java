package com.bookshop.backend.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class News {
    private Integer id = null;
    private String title = null;
    private String content = null;
}

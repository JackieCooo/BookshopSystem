package com.bookshop.backend.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Review {
    private Integer bookId;
    private Integer id;
    private String title = null;
    private String content = null;
    private String date = null;
    private String author = null;
    private String intro = null;
}

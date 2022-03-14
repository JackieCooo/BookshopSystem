package com.bookshop.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewInfo {
    private Integer bookId = 0;
    private Integer id = 0;
    private String title = null;
    private String content = null;
    private String date = null;
    private String author = null;
}

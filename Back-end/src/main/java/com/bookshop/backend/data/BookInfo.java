package com.bookshop.backend.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BookInfo {

    @NonNull
    private Integer id;

    private String name = null;
    private String author = null;
    private String date = null;
    private String publisher = null;
    private Double price = null;
    private Boolean hasEBook = false;
    private Boolean hasSecondhandBook = false;
    private String bookIntroduction = null;
    private String authorIntroduction = null;
    private String directory = null;
    private String pic = null;
    private String isbn = null;

}

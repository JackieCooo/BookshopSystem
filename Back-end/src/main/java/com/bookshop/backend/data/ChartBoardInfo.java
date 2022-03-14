package com.bookshop.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ChartBoardInfo {

    private ArrayList<BookBriefInfo> chart = new ArrayList<>();
    private String coverUrl = null;

    /*第一本书的额外信息，仅详细榜单使用*/
    private String date = null;
    private String publisher = null;
    private Double price = 0.0;
    private Boolean hasEBook = false;
    private Boolean hasSecondhandBook = false;

}

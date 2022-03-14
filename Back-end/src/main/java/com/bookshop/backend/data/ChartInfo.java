package com.bookshop.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ChartInfo {
    private ArrayList<BookInfo> chart = new ArrayList<>();
}

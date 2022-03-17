package com.bookshop.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class ReviewListInfo {
    private ArrayList<Review> list = new ArrayList<>();
}

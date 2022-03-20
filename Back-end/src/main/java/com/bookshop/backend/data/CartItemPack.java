package com.bookshop.backend.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemPack {
    private Integer id;
    private String name;
    private String author;
    private String date;
    private String publisher;
    private Double price;
    private Integer quantity;
}

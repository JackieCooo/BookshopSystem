package com.bookshop.backend.data;

import lombok.*;

@Data
@NoArgsConstructor
public class Cart {
    private Integer userId;
    private Integer bookId;
    private Integer quantity;
}

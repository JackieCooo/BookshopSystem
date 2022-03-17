package com.bookshop.backend.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Chart {
    private Integer typeId;
    private Integer bookId;
    private Integer rank;
}

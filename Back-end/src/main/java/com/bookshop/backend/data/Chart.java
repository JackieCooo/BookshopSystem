package com.bookshop.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chart {
    private Integer typeId;
    private Integer bookId;
    private Integer rankNum;
}

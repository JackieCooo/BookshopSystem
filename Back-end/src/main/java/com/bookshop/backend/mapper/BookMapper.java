package com.bookshop.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bookshop.backend.data.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper extends BaseMapper<Book> {
}

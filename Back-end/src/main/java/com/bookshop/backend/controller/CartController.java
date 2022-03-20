package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Book;
import com.bookshop.backend.data.Cart;
import com.bookshop.backend.data.CartItemPack;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.BookMapper;
import com.bookshop.backend.mapper.CartMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CartController {

    @Resource
    private CartMapper cartMapper;
    @Resource
    private BookMapper bookMapper;

    @PostMapping("/cart")
    public JsonResult<String> addItemToCart(@RequestBody Cart cart) {
        QueryWrapper<Cart> cartWrapper = new QueryWrapper<>();
        cartWrapper.eq("user_id", cart.getUserId()).eq("book_id", cart.getBookId());
        Long cnt = cartMapper.selectCount(cartWrapper);

        // 如果用户之前的购物车有这件商品，则增加购买数量
        if (cnt != 0) {
            Cart oldCart = cartMapper.selectOne(cartWrapper);
            cart.setQuantity(cart.getQuantity() + oldCart.getQuantity());
        }
        cartMapper.insert(cart);

        return new JsonResult<>();
    }

    @GetMapping("/cart/{id}")
    public JsonResult<ArrayList<CartItemPack>> sendCartInfo(@PathVariable Integer id) {

        // 获取购物车信息
        QueryWrapper<Cart> cartWrapper = new QueryWrapper<>();
        cartWrapper.eq("user_id", id);
        List<Cart> carts = cartMapper.selectList(cartWrapper);

        // 获取商品信息
        ArrayList<CartItemPack> res = new ArrayList<>();
        for (Cart i : carts) {
            CartItemPack item = new CartItemPack();
            item.setId(i.getBookId());
            item.setQuantity(i.getQuantity());
            QueryWrapper<Book> bookWrapper = new QueryWrapper<>();
            bookWrapper.select("id", "name", "author", "publisher", "price", "date").eq("id", i.getBookId());
            Book book = bookMapper.selectOne(bookWrapper);
            item.setAuthor(book.getAuthor());
            item.setDate(book.getDate());
            item.setPrice(book.getPrice());
            item.setPublisher(book.getPublisher());
            item.setName(book.getName());

            res.add(item);
        }

        return new JsonResult<>(res);
    }
}

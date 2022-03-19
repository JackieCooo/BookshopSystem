package com.bookshop.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bookshop.backend.data.Cart;
import com.bookshop.backend.jsonconvert.JsonResult;
import com.bookshop.backend.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class CartController {

    @Autowired
    private CartMapper cartMapper;

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
}

package com.cart.controller;

import com.cart.model.Cart;
import com.cart.model.CartItem;
import com.cart.service.CartItemService;
import com.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/item")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    // Get all items in a cart
    @GetMapping("/{cartId}")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return cartItemService.getItemsByCartId(cartId);
    }

    // Add item to cart
    @PostMapping("/add")
    public Cart addItemToCart(@RequestBody CartItem cartItem) {
        return cartService.addItemToCart(cartItem);
    }

    // Remove item from cart
    @DeleteMapping("/remove/{cartItemId}")
    public Cart removeItemFromCart(@PathVariable Long cartItemId) {
        return cartService.removeItemFromCart(cartItemId);
    }
}

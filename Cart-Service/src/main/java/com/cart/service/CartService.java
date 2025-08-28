package com.cart.service;

import com.cart.model.Cart;
import com.cart.model.CartItem;

public interface CartService {

	public Cart createCart(Long userId);

	public Cart getCartByUserId(Long userId);

	public void clearCart(Long userId);

	public Cart addItemToCart(CartItem cartItem);

	public Cart removeItemFromCart(Long cartItemId);

}

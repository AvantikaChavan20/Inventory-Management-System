package com.cart.service;

import java.util.List;
import java.util.Optional;

import com.cart.model.CartItem;

public interface CartItemService {

	public List<CartItem> getItemsByCartId(Long cartId);
	public Optional<CartItem> getItemById(Long cartItemId);
	public CartItem addItem(CartItem cartItem);
	public void removeItem(Long cartItemId);
	public void clearItemsByCartId(Long cartId);
	
}

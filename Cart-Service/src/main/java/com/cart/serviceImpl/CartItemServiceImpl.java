package com.cart.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cart.model.CartItem;
import com.cart.repository.CartItemRepository;
import com.cart.service.CartItemService;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    private CartItemRepository cartItemRepository;

    // Get all items in a cart
    @Override
    public List<CartItem> getItemsByCartId(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    // Get a single cart item by ID
    @Override
    public Optional<CartItem> getItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    // Add item to cart
    @Override
    public CartItem addItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // Remove item from cart
    @Override
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // Remove all items from a cart
    @Transactional
    @Override
    public void clearItemsByCartId(Long cartId) {
        cartItemRepository.deleteByCartId(cartId);
    }
}


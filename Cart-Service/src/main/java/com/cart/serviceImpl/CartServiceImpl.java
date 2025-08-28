package com.cart.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cart.model.Cart;
import com.cart.model.CartItem;
import com.cart.model.ProductDTO;
import com.cart.repository.CartRepository;
import com.cart.service.CartItemService;
import com.cart.service.CartService;
import com.cart.service.ProductClient;

import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private ProductClient productClient;

    // Get cart by user ID
    @Override
    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> createCart(userId));

        List<CartItem> cartItems = cartItemService.getItemsByCartId(cart.getCartId());
        cart.setCartItem(cartItems);
        

		double totalPrice = 0.0;
		for (CartItem cartItem : cartItems) {
			ProductDTO product = productClient.getProductById(cartItem.getProductId());
			if (product != null) {
				totalPrice += product.getPrice() * cartItem.getQuantity();
				}
		}
		cart.setTotalPrice(totalPrice);
        return cart;
    }

    // Create a new cart for a user
    @Override
    public Cart createCart(Long userId) {
        Optional<Cart> existingCart = cartRepository.findByUserId(userId);
        if (existingCart.isPresent()) {
        	return getCartByUserId(existingCart.get().getUserId());
        }
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setTotalPrice(0.0);
        return cartRepository.save(newCart);
    }

    // Add item to cart
    @Override
    public Cart addItemToCart(CartItem cartItem) {
        // Ensure cart exists
        Cart cart = getCartByUserId(cartItem.getCartId());

        // Set the correct cart ID for the item
        cartItem.setCartId(cart.getCartId());
        cartItemService.addItem(cartItem);

        return getCartByUserId(cart.getUserId());
    }

    // Remove item from cart
    @Override
    public Cart removeItemFromCart(Long cartItemId) {
        CartItem cartItem = cartItemService.getItemById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        
        Long cartId = cartItem.getCartId();
        cartItemService.removeItem(cartItemId);

        return getCartByUserId(cartId);
    }

    // Clear all items in the cart
    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        
        cartItemService.clearItemsByCartId(cart.getCartId());
    }
}


package com.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.model.CartItem;

import jakarta.transaction.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	public List<CartItem> findByCartId(Long id);
	@Transactional
	public void deleteByCartId(Long cartId);
}

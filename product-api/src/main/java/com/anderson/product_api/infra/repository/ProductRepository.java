package com.anderson.product_api.infra.repository;

import com.anderson.product_api.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByOwnerIdAndName(UUID ownerId, String name);
    Optional<Product> findByOwnerIdAndId(UUID ownerId, UUID id);
    Page<Product> findByOwnerId(UUID ownerId, Pageable pageable);
}

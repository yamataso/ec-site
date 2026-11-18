package com.example.ec_site.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // JpaRepositoryを継承することで、findAll()（全件取得）などが標準で利用できます
}
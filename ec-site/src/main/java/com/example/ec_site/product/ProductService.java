package com.example.ec_site.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // データベースからすべての商品データを取得するメソッド
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    
    //指定したIDno商品を1件取得するメソッド
    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
}
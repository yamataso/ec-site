package com.example.ec_site.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // TOP画面表示
    @GetMapping({"/top", "/"})
    public String showTop(Model model) {
        model.addAttribute("productList", productService.findAllProducts());
        return "top"; 
    }

    // 【実装】商品詳細画面の表示（/product/1, /product/2 などのURLに対応）
    @GetMapping("/product/{id}")
    public String showProductDetail(@PathVariable("id") Integer id, Model model) {
        // IDに一致する商品をデータベースから取得
        Product product = productService.findProductById(id);
        
        // 取得した商品をModelにセット
        model.addAttribute("product", product);
        
        return "product_detail"; // templates/product_detail.html を表示
    }
}
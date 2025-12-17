package com.example.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;

import jakarta.validation.*;

@Controller
@RequestMapping("/ui/products")
public class ProductUiController {

    private final ProductRepository productRepository;

    public ProductUiController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /* ---------- LIST ---------- */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product/list";
    }

    /* ---------- ADD ---------- */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Product product,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "product/form";
        }
        productRepository.save(product);
        return "redirect:/ui/products";
    }

    /* ---------- EDIT ---------- */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "product/form";
    }
    
    
    /* ---------- DELETE CONFIRM PAGE ---------- */
    @GetMapping("/delete/{id}")
    public String confirmDelete(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        model.addAttribute("product", product);
        return "product/delete";
    }

    /* ---------- DELETE ACTION ---------- */
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "redirect:/ui/products";
    }
    
}


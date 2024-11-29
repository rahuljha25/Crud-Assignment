package com.crud.service;

import com.crud.entity.Category;
import com.crud.entity.Product;
import com.crud.exception.ProductNotFoundException;
import com.crud.repository.CategoryRepository;
import com.crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Product addProduct(Product product, long categoryId) throws ProductNotFoundException {
        Optional<Category> byId = categoryRepository.findById(categoryId);
        if (byId.isPresent()){
            Category category = byId.get();
            product.setCategory(category);
            return productRepository.save(product);
        }else {
            throw new ProductNotFoundException("Category not found");
        }
    }

    public Product getProductById(long productId) throws ProductNotFoundException {
        Optional<Product> byId = productRepository.findById(productId);
        if (byId.isPresent()){
            Product product = byId.get();
            return product;
        }else {
            throw new ProductNotFoundException("Product not Found");
        }

    }

    public List<Product> getAllProducts(int pageNo, int pageSize)throws ProductNotFoundException {
        Pageable pageable=PageRequest.of(pageNo,pageSize);
        Page<Product> all = productRepository.findAll(pageable);
        List<Product> content = all.getContent();
        return content;
    }

    public Product updateProduct(long productId, Product product)throws ProductNotFoundException {
        Optional<Product> byId = productRepository.findById(productId);
        if (byId.isPresent()){
            Product product1 = byId.get();
            product1.setName(product.getName());
            return productRepository.save(product1);
        }else {
            throw new ProductNotFoundException("Product not Found");
        }
    }

    public void deleteProduct(long productId) throws ProductNotFoundException {
        Optional<Product> byId = productRepository.findById(productId);
        if (byId.isPresent()){
            productRepository.deleteById(productId);
        }else {
            throw new ProductNotFoundException("Product not Found");
        }
    }
}

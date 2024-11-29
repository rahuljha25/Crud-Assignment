package com.crud.controller;

import com.crud.entity.Product;
import com.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{categoryId}")
    public ResponseEntity<Product> addProduct(@RequestBody Product product,
                                              @PathVariable long categoryId){
        Product savedProduct = productService.addProduct(product,categoryId);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable long productId){
        Product productById = productService.getProductById(productId);
        return new ResponseEntity<>(productById,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Product>>getAllProducts(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize
    ){
        List<Product> allProducts = productService.getAllProducts(pageNo, pageSize);
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable long productId,
                                                 @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Record is deleted!!",HttpStatus.OK);
    }
}

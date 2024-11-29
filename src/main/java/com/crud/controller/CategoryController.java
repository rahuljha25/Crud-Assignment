package com.crud.controller;

import com.crud.entity.Category;
import com.crud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category savedCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);

    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getByCategoryId(@PathVariable long categoryId){
        Category byCategoryId = categoryService.getByCategoryId(categoryId);
        return new ResponseEntity<>(byCategoryId,HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable long categoryId,
                                                   @RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String>deleteCategory(@PathVariable long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Record is deleted!!",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>>getAllCategories(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize
    ){
        List<Category> allCategories = categoryService.getAllCategories(pageNo, pageSize);
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }
}

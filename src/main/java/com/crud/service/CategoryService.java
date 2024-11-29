package com.crud.service;

import com.crud.entity.Category;
import com.crud.exception.CategoryNotFoundException;
import com.crud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getByCategoryId(long categoryId) throws CategoryNotFoundException {
        Optional<Category> byId = categoryRepository.findById(categoryId);
        if (byId.isPresent()) {
            Category category = byId.get();
            return category;
        }else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    public Category updateCategory(long categoryId, Category category)throws CategoryNotFoundException {
        Optional<Category> byId = categoryRepository.findById(categoryId);
        if (byId.isPresent()){
            Category category1 = byId.get();
            category1.setName(category.getName());
            return categoryRepository.save(category1);
        }else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    public void deleteCategory(long categoryId)throws CategoryNotFoundException {
        Optional<Category> byId = categoryRepository.findById(categoryId);
        if (byId.isPresent()){
            categoryRepository.deleteById(categoryId);
        }else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    public List<Category> getAllCategories(int pageNo, int pageSize) throws CategoryNotFoundException {
        Pageable pageable=PageRequest.of(pageNo,pageSize);

        Page<Category> all = categoryRepository.findAll(pageable);
        //for converting pageable list into list
        List<Category> content = all.getContent();
        return content;
    }
}

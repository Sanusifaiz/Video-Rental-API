package com.learningjava.expensetrackerapi.services;

import com.learningjava.expensetrackerapi.entity.Category;
import com.learningjava.expensetrackerapi.exceptions.EtResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer d);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    String addCategory(Category category) throws EtResourceNotFoundException;

    Category updateCategory(Category category) throws EtResourceNotFoundException;

    String removeCategory(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    String removeCategories(Integer userId);
}

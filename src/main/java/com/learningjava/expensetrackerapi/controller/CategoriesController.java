package com.learningjava.expensetrackerapi.controller;


import com.learningjava.expensetrackerapi.entity.Category;
import com.learningjava.expensetrackerapi.entity.User;
import com.learningjava.expensetrackerapi.services.CategoryServiceImpl;
import javassist.bytecode.stackmap.BasicBlock;
import org.aspectj.weaver.ast.Var;
import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("")
    public String getAllCategories(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");

        return "Authenticated: UserId: " + userId;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> AddCategory (@RequestBody Category category) {
        var cat = categoryService.addCategory(category);
        Map<String, String> map = new HashMap<>();
        map.put("message", cat);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Var>> UpdateCategory (@RequestBody Category category) {
        var cat = categoryService.updateCategory(category);
        if(cat != null) {
            Map<String, Category> map = new HashMap<>();
            map.put("Result", cat);
            return new ResponseEntity(map, HttpStatus.CREATED);
        }
        Map<String, Category> map = new HashMap<>();
        map.put("Result", cat);
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getCatById")
    public ResponseEntity<Map<String, Var>> GetCategory(@RequestParam Integer userId, Integer catId) {
        var cat= categoryService.fetchCategoryById(userId, catId);
        if (cat != null){
            Map<String, Category> map = new HashMap<>();
            map.put("Result", cat);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "Category does not exist");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getCatsById")
    public ResponseEntity<Map<String, Var>> GetCategory(@RequestParam Integer userId) {
        var cat= categoryService.fetchAllCategories(userId);
        if (cat != null){
            Map<String, List> map = new HashMap<>();
            map.put("Result", cat);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", "User has no categories");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/RemoveCatById")
    public ResponseEntity<Map<String, Var>> DeleteCategory(@RequestParam Integer userId, Integer categoryId) {
        var cat= categoryService.removeCategory(userId, categoryId);
        if (cat != "Category Deleted"){
            Map<String, String> map = new HashMap<>();
            map.put("Result", cat);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", cat);
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/RemoveCatsById")
    public ResponseEntity<Map<String, Var>> DeleteCategories(@RequestParam Integer userId) {
        var cat= categoryService.removeCategories(userId);
        if (cat != "Categories deleted"){
            Map<String, String> map = new HashMap<>();
            map.put("Result", cat);
            return new ResponseEntity(map, HttpStatus.OK);
        }
        Map<String, String> map = new HashMap<>();
        map.put("Result", cat);
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

}

package com.learningjava.expensetrackerapi.services;


import com.learningjava.expensetrackerapi.entity.Category;
import com.learningjava.expensetrackerapi.exceptions.EtBadRequestException;
import com.learningjava.expensetrackerapi.exceptions.EtResourceNotFoundException;
import com.learningjava.expensetrackerapi.logics.CopyProperty;
import com.learningjava.expensetrackerapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        try{
            if(userId != null){
               var catList =  categoryRepository.findAllCategories(userId);
               if (catList.isEmpty()){
                   return null;
               }
                return catList;
            }
            throw new EtBadRequestException("userId cannot be null");
        }catch (Exception e){
            throw  new EtBadRequestException("An Error Occurred");
        }
    }

    @Override
    public Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        try{
            if((userId != null) && (categoryId != null)) {
               var cat =  categoryRepository.findOneByUserIdAndCategoryId(userId, categoryId);
               if (cat != null){
                   return cat;
               }
               return null;
            }
            throw new EtBadRequestException("UserId or CategoryId cannot be null");
        }catch (Exception e){
            throw new EtBadRequestException("An Error occured");
        }
    }

    @Override
    public String addCategory(Category category) throws EtResourceNotFoundException {
        try {
            if(category != null) {
                var checkCat = categoryRepository.CheckCatIfExists(category.getUserId(), category.getTitle(), category.getDescription());
                if(checkCat == null) {
                    var saveCat = categoryRepository.save(category);
                    //var getSaved = categoryRepository.findbyId(saveCat.getUserId(), saveCat.getCategoryId());
                    var getSaved = categoryRepository.findById(saveCat.getCategoryId());

                    return "Category created";
               }
                return "Category already exists";
            }
            throw new EtBadRequestException("Input cant be null");

        }catch (Exception e) {
            throw new EtBadRequestException("An Error has occurred");
        }

    }

    @Override
    public Category updateCategory(Category category) throws EtResourceNotFoundException {
        try {
            if (category != null) {
                var getCat = categoryRepository.findOneByUserIdAndCategoryId(category.getUserId(), category.getCategoryId());
                if(getCat != null){
                        CopyProperty.CopyObject(category,getCat);
                        categoryRepository.save(getCat);
                        return getCat;
                }
                return null;
            }
            return null;

        }catch (Exception e) {
            throw new EtBadRequestException("An Error has Occurred");
        }
    }

    @Override
    public String removeCategory(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
      try {
          if((userId != null)||(categoryId != null)) {
              var getCat = categoryRepository.findOneByUserIdAndCategoryId(userId, categoryId);
              if(getCat != null){
                  categoryRepository.deleteByUserIdAndCategoryId(userId, categoryId);
                  return "Category Deleted";
              }
              return "Category does not exist";
          }
          throw new EtBadRequestException("UserId/CategoryId cannot be null");
      } catch (Exception e) {
          throw new EtBadRequestException("An Error has Occurred");
      }
    }

    @Override
    public String removeCategories(Integer userId) {
        try{
            if(userId != null){
                var catList =  categoryRepository.findAllCategories(userId);
                if (!catList.isEmpty()){
                    categoryRepository.deleteAllByUserId(userId);
                    return "Categories deleted";
                }
                return "User has no Category";
            }
            throw new EtBadRequestException("userId cannot be null");
        }catch(Exception e){
            throw new EtBadRequestException("An Error has occurred");
        }
    }


}

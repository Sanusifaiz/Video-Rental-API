package com.learningjava.expensetrackerapi.repositories;

import com.learningjava.expensetrackerapi.entity.Category;
import com.learningjava.expensetrackerapi.exceptions.EtResourceExistsException;
import com.learningjava.expensetrackerapi.exceptions.EtResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT ud FROM Category_TBL ud where ud.userId=?1")
    List<Category> findAllCategories(Integer userId) throws EtResourceNotFoundException;

    //@Query("SELECT ud FROM Category_TBL ud where ud.userId=?1 and ud.categoryId=?2")

    Category findOneByUserIdAndCategoryId(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    //@Query("DELETE FROM Category_TBL where userId=?1 and categoryId=?2")

    String deleteByUserIdAndCategoryId(Integer userId, Integer categoryId);

    @Query("SELECT ud FROM Category_TBL ud where ud.userId=?1 and ud.title=?2 and ud.description=?3")
    Category CheckCatIfExists(Integer userId, String title, String description ) throws EtResourceExistsException;

    String deleteAllByUserId(Integer userId);
}

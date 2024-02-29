package org.example.coffeeshop.service;

import org.example.coffeeshop.model.entity.CategoryEntity;
import org.example.coffeeshop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}

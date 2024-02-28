package org.example.coffeeshop.service.impl;

import org.example.coffeeshop.model.entity.CategoryEntity;
import org.example.coffeeshop.model.entity.enums.CategoryNameEnum;
import org.example.coffeeshop.repository.CategoryRepository;
import org.example.coffeeshop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryNameEnum.values())
                    .forEach(categoryNameEnum -> {
                        CategoryEntity category = new CategoryEntity();
                        category.setName(categoryNameEnum);
                        switch (categoryNameEnum) {
                            case CAKE -> category.setNeededTime(10);
                            case DRINK -> category.setNeededTime(1);
                            case COFFEE -> category.setNeededTime(2);
                            case OTHER -> category.setNeededTime(5);
                        }
                        categoryRepository.save(category);
                    });
        }
    }
}

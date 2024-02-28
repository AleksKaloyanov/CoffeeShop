package org.example.coffeeshop.init;

import org.example.coffeeshop.service.CategoryService;
import org.springframework.boot.CommandLineRunner;

public class DatabaseInit implements CommandLineRunner {

    private final CategoryService categoryService;

    public DatabaseInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
    }
}

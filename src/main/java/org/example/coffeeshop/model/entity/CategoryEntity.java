package org.example.coffeeshop.model.entity;

import jakarta.persistence.*;
import org.example.coffeeshop.model.entity.enums.CategoryNameEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;
    @Column(nullable = false)
    private Integer neededTime;

    public CategoryEntity() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public CategoryEntity setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
        return this;
    }
}

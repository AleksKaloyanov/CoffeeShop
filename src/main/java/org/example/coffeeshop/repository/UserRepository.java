package org.example.coffeeshop.repository;

import org.example.coffeeshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("select u from UserEntity u order by size(u.orders) desc")
    List<UserEntity> findAllByOrderCountDesc();
}

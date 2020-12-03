package com.intuit.demo.data.repo;

import com.intuit.demo.data.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, String> {
    Optional<TestEntity> findById(String id);
    List<TestEntity> findByName(String name);
}

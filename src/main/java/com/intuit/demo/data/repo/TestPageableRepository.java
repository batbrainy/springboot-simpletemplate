package com.intuit.demo.data.repo;

import com.intuit.demo.data.entity.TestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestPageableRepository extends PagingAndSortingRepository<TestEntity, String> {

}

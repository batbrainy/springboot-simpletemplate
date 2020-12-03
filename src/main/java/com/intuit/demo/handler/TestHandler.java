package com.intuit.demo.handler;

import com.intuit.demo.data.entity.TestEntity;
import com.intuit.demo.data.repo.TestPageableRepository;
import com.intuit.demo.data.repo.TestRepository;
import com.intuit.demo.data.vo.Test;
import com.intuit.demo.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestHandler {

    private final TestRepository testRepository;
    private final ModelMapper modelMapper;
    private final TestPageableRepository testPageableRepository;

    @Autowired
    public TestHandler(TestRepository testRepository, ModelMapper modelMapper, TestPageableRepository testPageableRepository) {
        this.testRepository = testRepository;
        this.modelMapper = modelMapper;
        this.testPageableRepository = testPageableRepository;
    }

    public Test getTest(String id) {
        final TestEntity testEntity = testRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(testEntity, Test.class);
    }

    public String insertTest(Test test){
        final TestEntity testEntity = modelMapper.map(test, TestEntity.class);
        return testRepository.save(testEntity).getId();
    }

    public List<Test> getTestByName(String name) {
        return testRepository.findByName(name).stream().map(x-> modelMapper.map(x, Test.class)).collect(Collectors.toList());
    }

    public List<Test> getPaged(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<TestEntity> pagedResult = testPageableRepository.findAll(paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent().stream().map(x-> modelMapper.map(x, Test.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}


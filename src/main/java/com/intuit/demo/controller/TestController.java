package com.intuit.demo.controller;

import com.intuit.demo.data.entity.TestEntity;
import com.intuit.demo.data.vo.Test;
import com.intuit.demo.handler.TestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/test")
public class TestController {

    private final TestHandler testHandler;

    @Autowired
    public TestController(TestHandler testHandler) {
        this.testHandler = testHandler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTest(@PathVariable String id){
        return ResponseEntity.ok(testHandler.getTest(id));
    }

    @GetMapping
    public ResponseEntity<List<Test>> getTestByName(@RequestParam String name){
        return ResponseEntity.ok(testHandler.getTestByName(name));
    }

    @PostMapping
    public ResponseEntity<?> insertTest(@RequestBody Test test){
        return ResponseEntity.ok(testHandler.insertTest(test));
    }

    @GetMapping("/paged")
    public ResponseEntity<List<Test>> getAllPaged(@RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "id") String sortBy){
        return ResponseEntity.ok(testHandler.getPaged(pageNo, pageSize, sortBy));
    }

}

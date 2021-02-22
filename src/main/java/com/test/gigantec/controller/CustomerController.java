package com.test.gigantec.controller;

import com.test.gigantec.entity.Customer;
import com.test.gigantec.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/api/test")
    public ResponseEntity<String> testSpringBoot() {
        return ResponseEntity.ok("Successssss");
    }

    @PostMapping(value = "create")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer entity) {
       customerService.create(entity);
        return ResponseEntity.ok(entity);
    }

}

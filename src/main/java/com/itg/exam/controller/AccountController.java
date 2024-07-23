package com.itg.exam.controller;

import com.itg.exam.model.CustomerDto;
import com.itg.exam.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    @Autowired
    private CustomerServiceImpl CustomerServiceImpl;

    @PostMapping("")
    public ResponseEntity<Object> addAccount(@RequestBody CustomerDto newCustomerAccount){
        Map<String,Object> response = new TreeMap<String,Object>();

        try {
            Long id = CustomerServiceImpl.customerAccountAdd(newCustomerAccount);
            response.put("customerNumber ",id);
            response.put("transactionStatusCode",201);
            response.put("transactionStatusDescription","Customer account created");

        }catch (Exception e){

            response.put("transactionStatusCode",400);
            response.put("transactionStatusDescription",e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatusCode.valueOf((Integer) response.get("transactionStatusCode")));
    }

    @GetMapping(path = "{customerNumber}")
    public ResponseEntity<Object> getAccount(@PathVariable("customerNumber") Long customerNumber){
        Map<String,Object> response = new TreeMap<String,Object>();

        response = CustomerServiceImpl.customerAccountList(customerNumber);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf((Integer) response.get("transactionStatusCode")));
    }


}

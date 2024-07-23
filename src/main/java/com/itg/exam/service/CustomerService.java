package com.itg.exam.service;

import com.itg.exam.model.AccountDto;
import com.itg.exam.model.CustomerDto;

import java.util.Map;

public interface CustomerService {

    public Map<String,Object> customerAccountList(Long customerNumber);

    public Long customerAccountAdd(CustomerDto newCustomerAccount) throws Exception;

}

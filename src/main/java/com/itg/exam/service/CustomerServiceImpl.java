package com.itg.exam.service;

import com.itg.exam.model.AccountDto;
import com.itg.exam.model.CustomerDto;
import com.itg.exam.model.repository.AccountRepository;
import com.itg.exam.model.repository.CustomerRepository;
import com.itg.exam.utility.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Long customerAccountAdd(CustomerDto newCustomerAccount) throws Exception {

        Helpers helper = new Helpers();

        if(newCustomerAccount.getCustomerName()==null || newCustomerAccount.getCustomerName().isEmpty()){
            throw new Exception("Customer name is required field");
        }

        if(newCustomerAccount.getCustomerMobile()==null || newCustomerAccount.getCustomerMobile().isEmpty()){
            throw new Exception("Customer mobile required field");
        }

        if(newCustomerAccount.getCustomerEmail()==null || newCustomerAccount.getCustomerEmail().isEmpty()){
            throw new Exception("Email is required field");
        }else{
            //additional validation
            if(!helper.checkEmailFormat(newCustomerAccount.getCustomerEmail())){
                throw new Exception("Email is not valid");
            }
        }

        if(newCustomerAccount.getAddress1()==null || newCustomerAccount.getAddress1().isEmpty()){
            throw new Exception("Address1 is required field");
        }

        if(newCustomerAccount.getAccountType()==null || newCustomerAccount.getAccountType().isEmpty()){
            throw new Exception("Account Type is required field");
        }if(
            //additional validation
                !newCustomerAccount.getAccountType().equalsIgnoreCase("S") &&
                !newCustomerAccount.getAccountType().equalsIgnoreCase("C")){
            throw new Exception("Account Type must be S or C ");
        }

        customerRepository.save(newCustomerAccount);

        AccountDto openNewSavingsAccount = new AccountDto();
        openNewSavingsAccount.setAccountType(newCustomerAccount.getAccountType());
        accountRepository.save(openNewSavingsAccount);

        return newCustomerAccount.getCustomerNumber();
    }


    public Map<String,Object> customerAccountList(Long customerNumber) {
        Map<String,Object> data = new TreeMap<String,Object>();
        Optional<CustomerDto> exist = customerRepository.findById(customerNumber);

        if(exist.isPresent()){
            CustomerDto customer =  customerRepository.getById(customerNumber);
            data= singleResponseCustomerBuilder(customer);

            data.put("transactionStatusCode",302);
            data.put("transactionStatusDescription","Customer Account found");

        }else{

            data.put("transactionStatusCode",401);
            data.put("transactionStatusDescription","Customer not found");

        }

        return data;
    }

    private Map<String, Object> singleResponseCustomerBuilder(CustomerDto customer){
        Map<String,Object> data = new TreeMap<String,Object>();

        data.put("customerNumber",customer.getCustomerNumber());
        data.put("customerName",customer.getCustomerName());
        data.put("customerMobile",customer.getCustomerMobile());
        data.put("customerEmail",customer.getCustomerEmail());
        data.put("address1",customer.getAddress1());
        data.put("address2",customer.getAddress2());
        data.put("savings",customer.getSavings());

        return data;

    }
}

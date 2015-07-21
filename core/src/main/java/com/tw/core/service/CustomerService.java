package com.tw.core.service;

import com.tw.core.dao.CustomerDao;
import com.tw.core.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers() {

        return customerDao.getCustomers();
    }
}

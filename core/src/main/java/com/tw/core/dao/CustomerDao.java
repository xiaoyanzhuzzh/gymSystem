package com.tw.core.dao;

import com.tw.core.entity.Customer;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {
    public List<Customer> getCustomers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Customer> customers = session.createQuery("from Customer ").list();

        session.close();
        return customers;
    }
}

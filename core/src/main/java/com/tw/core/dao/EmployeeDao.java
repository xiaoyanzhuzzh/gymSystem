package com.tw.core.dao;


import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    public void createEmployee(Employee employee){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(employee);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public Boolean verifyUserInfo(String name, String password) {
        Boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from User where name=? and password=?";
        Query query = session.createQuery(hql);

        query.setString(0, name);
        query.setString(1, EncryptionHelper.md5(password));

        List<User> users = query.list();
        if(users.size() == 1) {
            result = true;
        }
        session.close();

        return result;
    }

    public List<Employee> getEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.createQuery("from Employee ").list();

        session.close();
        return employees;
    }

    public void deleteEmployeeById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Employee employee = (Employee) session.load(Employee.class, id);
        session.delete(employee);

        session.getTransaction().commit();
        session.close();
    }

    public Employee getEmployeeById(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee = (Employee) session.get(Employee.class, id);
        session.close();
        return employee;
    }


    public static void main(String[] args) {
        System.out.println(new EmployeeDao().getEmployeeById(1));
    }
}

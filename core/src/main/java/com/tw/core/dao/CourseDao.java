package com.tw.core.dao;

import com.tw.core.entity.Course;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDao {

    public List<Course> getCourses(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = session.createQuery("from Course").list();

        session.close();
        return courses;
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

    public void createCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(course);
        session.getTransaction().commit();
        session.close();
    }
}

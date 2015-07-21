package com.tw.core.dao;

import com.tw.core.entity.Course;
import com.tw.core.entity.Schedule;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScheduleDao {

    public List<Schedule> getSchedules() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Schedule> schedules = session.createQuery("from Schedule").list();

        session.close();
        return schedules;
    }

    public void createSchedule(Schedule schedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(schedule);
        session.getTransaction().commit();
        session.close();
    }

    public boolean getScheduleByCourseAndTime(Course course, String time) {
        Boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Schedule where course=:course and time=:time";
        Query query = session.createQuery(hql);

        query.setParameter("course", course);
        query.setParameter("time", time);

        List<User> users = query.list();
        if(users.size() != 0) {
            result = true;
        }
        session.close();

        return result;
    }
}

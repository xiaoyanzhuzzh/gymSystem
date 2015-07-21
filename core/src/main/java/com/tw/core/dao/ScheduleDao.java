package com.tw.core.dao;

import com.tw.core.entity.Schedule;
import com.tw.core.util.HibernateUtil;
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
}

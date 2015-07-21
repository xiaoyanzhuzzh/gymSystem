package com.tw.core.service;

import com.tw.core.dao.ScheduleDao;
import com.tw.core.entity.Course;
import com.tw.core.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    public List<Schedule> getSchedules() {

        return scheduleDao.getSchedules();
    }

    public void createSchedule(Schedule schedule) {

        scheduleDao.createSchedule(schedule);
    }

    public boolean getScheduleByCourseAndTime(Course course, String time) {
        return scheduleDao.getScheduleByCourseAndTime(course, time);
    }

    public Schedule getScheduleById(int id) {

        return scheduleDao.getScheduleById(id);
    }

    public void updateSchedule(Schedule schedule) {

        scheduleDao.updateSchedule(schedule);
    }

    public void deleteScheduleById(int id) {

        scheduleDao.deleteScheduleById(id);
    }

    public List<Schedule> getSchedulesByCourse(Course course) {

        return scheduleDao.getScheduleByCourse(course);
    }
}

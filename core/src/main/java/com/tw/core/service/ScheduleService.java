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
}

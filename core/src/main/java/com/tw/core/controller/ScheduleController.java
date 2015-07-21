package com.tw.core.controller;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import com.tw.core.entity.Course;
import com.tw.core.entity.Schedule;
import com.tw.core.service.CourseService;
import com.tw.core.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value="/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getSchedules(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("schedules", "schedules", scheduleService.getSchedules());
        }
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public ModelAndView getCreateSchedulePage(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("createSchedule", "courses", courseService.getCourses());
        }
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createSchedule(@RequestParam String courseName,
                                       @RequestParam Date time) {

        Course course = courseService.getCourseByName(courseName);
        scheduleService.createSchedule(new Schedule(time, course));

        return new ModelAndView("redirect:/schedules/");
    }
}

package com.tw.core.controller;

import com.tw.core.entity.Course;
import com.tw.core.entity.Schedule;
import com.tw.core.service.CourseService;
import com.tw.core.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
                                       @RequestParam String time) {

        Course course = courseService.getCourseByName(courseName);
        if(!scheduleService.getScheduleByCourseAndTime(course, time)){

            scheduleService.createSchedule(new Schedule(time, course));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public ModelAndView getUpdateSchedulePage(@PathVariable int id,
                                              HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("updateSchedule", "schedule", scheduleService.getScheduleById(id));
        }
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ModelAndView updateSchedule(@RequestParam int id,
                                       @RequestParam String courseName,
                                       @RequestParam String time) {

        Course course = courseService.getCourseByName(courseName);
        if(!scheduleService.getScheduleByCourseAndTime(course, time)){

            scheduleService.updateSchedule(new Schedule(id, time, course));
        }

        return new ModelAndView("redirect:/schedules/");
    }
}

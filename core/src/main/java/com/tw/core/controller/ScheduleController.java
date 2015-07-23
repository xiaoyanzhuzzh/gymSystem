package com.tw.core.controller;

import com.tw.core.entity.*;
import com.tw.core.service.*;
import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.font.LayoutPathImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RelationService relationService;

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
    public ModelAndView createSchedule(@RequestParam int courseId,
                                       @RequestParam String time) {

        Course course = courseService.getCourseById(courseId);
        if(!scheduleService.getScheduleByCourseAndTime(course, time)){

            scheduleService.createSchedule(new Schedule(time, course));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value="/createPrivate", method= RequestMethod.GET)
    public ModelAndView getCreatePrivateCoursePage(HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            ModelAndView modelAndView = new ModelAndView("createPrivateSchedule", "customers", customerService.getCustomers());
            modelAndView.addObject("coaches", employeeService.getAllCoaches());
            modelAndView.addObject("courses", courseService.getCourses());
            return modelAndView;
        }
    }

    @RequestMapping(value="/createPrivate", method= RequestMethod.POST)
    public ModelAndView createPrivateCourse(@RequestParam int customerId,
                                            @RequestParam int coachId,
                                            @RequestParam int courseId,
                                            @RequestParam String time) {

        Employee employee = employeeService.getEmployeeById(coachId);
        List<Course> courses = courseService.getCoursesByEmployee(employee);

        Boolean isExisted = false;
        for(Course course: courses) {
            isExisted = scheduleService.getScheduleByCourseAndTime(course, time);
            if(isExisted){
                break;
            }
        }

        if(!isExisted) {

            String name = customerService.getCustomerById(customerId).getName();
            Customer customer = new Customer(customerId, name, employee);
            customerService.updateCustomer(customer);

            Course currentCourse = courseService.getCourseById(courseId);
            Course newCourse  = new Course(currentCourse.getName(), employee);
            relationService.createRelation(new CourseCustomerRelation(newCourse, customer));

            scheduleService.createSchedule(new Schedule(time, newCourse));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public ModelAndView getUpdateSchedulePage(@PathVariable int id,
                                              HttpServletRequest request) {

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            ModelAndView modelAndView = new ModelAndView("updateSchedule", "schedule", scheduleService.getScheduleById(id));
            modelAndView.addObject("courses", courseService.getCourses());
            return modelAndView;
        }
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ModelAndView updateSchedule(@RequestParam int id,
                                       @RequestParam int courseId,
                                       @RequestParam String time) {

        Course course = courseService.getCourseById(courseId);
        if(!scheduleService.getScheduleByCourseAndTime(course, time)){

            scheduleService.updateSchedule(new Schedule(id, time, course));
        }

        return new ModelAndView("redirect:/schedules/");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable int id){

        scheduleService.deleteScheduleById(id);

        return new ModelAndView("redirect:/schedules/");
    }

}

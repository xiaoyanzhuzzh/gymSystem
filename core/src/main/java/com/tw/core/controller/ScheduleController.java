package com.tw.core.controller;

import com.tw.core.entity.*;
import com.tw.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

            ModelAndView modelAndView = new ModelAndView("schedules");
            List<Schedule> schedules = scheduleService.getSchedules();

            List<Course> publicCourses = new ArrayList<Course>();
            List<Schedule> publicSchedules = new ArrayList<Schedule>();

            //公共课
            List<Course> courses = courseService.getCourses();
            for (Course course : courses) {
                if (relationService.getRelationsByCourse(course).size() == 0) {

                    publicCourses.add(course);
                }
            }

            for(Course course: publicCourses) {
                publicSchedules.addAll(scheduleService.getSchedulesByCourse(course));
            }

            for(Schedule schedule: publicSchedules) {
                schedules.remove(schedule);
            }

            for(int i = 0; i < publicSchedules.size(); i ++) {
                for(int j = 0; j < schedules.size(); j++) {
                    if(publicSchedules.get(i).getId() == schedules.get(j).getId()) {
                        schedules.remove(j);
                    }
                }
            }

            modelAndView.addObject("publicSchedules", publicSchedules);
            modelAndView.addObject("privateSchedules", schedules);
            return modelAndView;
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

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id){

        scheduleService.deleteScheduleById(id);
        return "yes";
    }
}

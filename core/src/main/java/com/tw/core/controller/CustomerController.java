package com.tw.core.controller;

import com.tw.core.entity.Course;
import com.tw.core.entity.CourseCustomerRelation;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Schedule;
import com.tw.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Customer> customers = customerService.getCustomers();
            ModelAndView modelAndView = new ModelAndView("customers", "customers", customers);
            List<Course> courses = new ArrayList<Course>();

            for(Customer customer: customers) {

                List<CourseCustomerRelation> relations = relationService.getRelationsByCustomer(customer);

                for(int i = 0; i < relations.size(); i++) {

                    courses.add(relations.get(i).getCourse());
                }
            }
            modelAndView.addObject("courses", courses);
            return modelAndView;
        }
    }

    @RequestMapping(value="/create", method = RequestMethod.GET)
    public ModelAndView getCreateCustomerPage(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            ModelAndView modelAndView = new ModelAndView("createCustomer");
            modelAndView.addObject("employees", employeeService.getAllCoaches());
            modelAndView.addObject("courses", courseService.getCourses());

            return modelAndView;
        }
    }
}

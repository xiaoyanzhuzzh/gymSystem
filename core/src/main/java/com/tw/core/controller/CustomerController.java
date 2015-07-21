package com.tw.core.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tw.core.entity.CourseCustomerRelation;
import com.tw.core.entity.Customer;
import com.tw.core.entity.Schedule;
import com.tw.core.service.CustomerService;
import com.tw.core.service.RelationService;
import com.tw.core.service.ScheduleService;
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            List<Customer> customers = customerService.getCustomers();
            ModelAndView modelAndView = new ModelAndView("customers", "customers", customers);
            List<Schedule> schedules = new ArrayList<Schedule>();

            for(Customer customer: customers) {
                System.out.println(relationService.getRelationsByCustomer(customer));
                for(CourseCustomerRelation relation: relationService.getRelationsByCustomer(customer)){

                    schedules.addAll(scheduleService.getSchedulesByCourse(relation.getCourse()));
                }
            }
            modelAndView.addObject("schedules", schedules);
            System.out.println(schedules);
            return modelAndView;
        }
    }
}

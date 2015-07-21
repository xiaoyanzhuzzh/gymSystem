package com.tw.core.controller;

import com.tw.core.entity.Employee;
import com.tw.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("employees", "employees", employeeService.getEmployees());
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUpdateUserAge(@PathVariable int id,
                                         HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            Employee employee = employeeService.getEmployeeById(id);
            System.out.println(employee);
            return new ModelAndView("updateEmployee", "employee", employee);
        }
        //根据employee中的user_id去User表中去找到user,将其放到页面
    }
}

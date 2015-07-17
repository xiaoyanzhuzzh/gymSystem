package com.tw.core.controller;

import com.tw.core.entity.Employee;
import com.tw.core.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(){

        return new ModelAndView("index", "employees", employeeService.getEmployees());
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateUserPage(){

        return new ModelAndView("createUser");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createUser(@RequestParam String name,
                                   @RequestParam String gender,
                                   @RequestParam int age,
                                   @RequestParam String email,
                                   @RequestParam String password){

//        User user = new User(name, gender, age, email, EncryptionHelper.md5(password));
//        userService.createUser(user);
        return new ModelAndView("redirect:/users/");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable int id){

        employeeService.deleteEmployeeById(id);

        return new ModelAndView("redirect:/employees/");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUpdateUserAge(@PathVariable int id){
        Employee employee = employeeService.getEmployeeById(id);
        //根据employee中的user_id去User表中去找到user,将其放到页面
        return new ModelAndView("updateEmployee", "employee", employee);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView UpdateUser(@RequestParam int id,
                                   @RequestParam String name,
                                   @RequestParam String gender,
                                   @RequestParam int age,
                                   @RequestParam String email,
                                   @RequestParam String password){
//        User user = new User(id, name, gender, age, email, EncryptionHelper.md5(password));
//        userService.updateUser(user);
//
        return new ModelAndView("redirect:/users/");
    }
}

package com.tw.core.controller;

import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.EmployeeService;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("employees", "employees", employeeService.getEmployees());
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCreateUserPage(HttpServletRequest request) {
        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("createEmployee");
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createEmployee(@RequestParam String nickname,
                                       @RequestParam String name,
                                       @RequestParam String role,
                                       @RequestParam String password,
                                       @RequestParam String gender,
                                       @RequestParam int age,
                                       @RequestParam String email,
                                       HttpServletRequest request){


        Employee employee = new Employee(name, gender, age, email, role);
        employeeService.createEmployee(employee);

        if (!userService.getUserByName(nickname)) {

            User user = new User(nickname, EncryptionHelper.md5(password), employee);
            userService.createUser(user);
            request.getSession().setAttribute("currentUser", name);
        }

        return new ModelAndView("redirect:/employees/");
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView getUpdateUserAge(@PathVariable int id){
        User user = userService.getUserById(id);
        return new ModelAndView("updateUser", "user", user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView UpdateUser(@RequestParam int id,
                                   @RequestParam int employee_id,
                                   @RequestParam String nickName,
                                   @RequestParam String name,
                                   @RequestParam String role,
                                   @RequestParam String password,
                                   @RequestParam String gender,
                                   @RequestParam int age,
                                   @RequestParam String email){

        Employee employee = new Employee(employee_id, name, gender, age, email, role);
        userService.updateUser(new User(id, nickName, EncryptionHelper.md5(password), employee));

        return new ModelAndView("redirect:/employees/");
    }
}

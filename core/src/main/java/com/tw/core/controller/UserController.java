package com.tw.core.controller;

import com.tw.core.entity.Employee;
import com.tw.core.entity.User;
import com.tw.core.helper.EncryptionHelper;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAllUsers(HttpServletRequest request){

        if(request.getSession().getAttribute("currentUser") == null){

            return new ModelAndView("redirect:/login");
        } else {

            return new ModelAndView("users", "users", userService.getUsers());
        }
    }

//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteUser(@PathVariable int id){
//
//        userService.deleteUserById(id);
//
//        return new ModelAndView("redirect:/users/");
//    }

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

        return new ModelAndView("redirect:/courses/");
    }
}

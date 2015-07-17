//package com.tw.core.controller;
//
//import com.tw.core.entity.User;
//import com.tw.core.helper.EncryptionHelper;
//import com.tw.core.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView getAllUsers(){
//
//        return new ModelAndView("index", "users", userService.getUsers());
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView getCreateUserPage(){
//
//        return new ModelAndView("createUser");
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public ModelAndView createUser(@RequestParam String name,
//                                   @RequestParam String gender,
//                                   @RequestParam int age,
//                                   @RequestParam String email,
//                                   @RequestParam String password){
//
////        User user = new User(name, gender, age, email, EncryptionHelper.md5(password));
////        userService.createUser(user);
//        return new ModelAndView("redirect:/users/");
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteUser(@PathVariable int id){
//
//        userService.deleteUserById(id);
//
//        return new ModelAndView("redirect:/users/");
//    }
//
//    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//    public ModelAndView getUpdateUserAge(@PathVariable int id){
//        User user = userService.getUserById(id);
//        return new ModelAndView("updateUser", "user", user);
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public ModelAndView UpdateUser(@RequestParam int id,
//                                   @RequestParam String name,
//                                   @RequestParam String gender,
//                                   @RequestParam int age,
//                                   @RequestParam String email,
//                                   @RequestParam String password){
////        User user = new User(id, name, gender, age, email, EncryptionHelper.md5(password));
////        userService.updateUser(user);
////
//        return new ModelAndView("redirect:/users/");
//    }
//}

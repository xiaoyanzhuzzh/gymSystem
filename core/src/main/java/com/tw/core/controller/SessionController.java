package com.tw.core.controller;

import com.tw.core.helper.CookieHelper;
import com.tw.core.service.AdminService;
import com.tw.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SessionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView getLoginPage() {

        return new ModelAndView("login");
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String name,
                              @RequestParam String password,
                              HttpServletRequest request){

        if(userService.verifyUserInfo(name, password)) {

            System.out.println("++++++++");
            request.getSession().setAttribute("currentUser", name);
        }

        String url = request.getHeader("referer");

        if(url.equals("http://localhost:8080/web/login")) {

            url = "/employees";
            System.out.println(url + "++++++++++++++++++++");
            return new ModelAndView("redirect:" + url);
        } else {

        return new ModelAndView("redirect:" + url);
        }
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request){

        request.getSession().removeAttribute("currentUser");
        return new ModelAndView("redirect:/login");
    }
}

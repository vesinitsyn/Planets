package com.gmail.vesinitsyn.planets.web.controller;

import com.gmail.vesinitsyn.planets.model.Planet;
import com.gmail.vesinitsyn.planets.model.User;
import com.gmail.vesinitsyn.planets.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserIdentificationController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserServiceInterface userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showContent() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        User user = userService.findUser(name);
        List<Planet> planets = user.getPlanets();

        ModelAndView modelAndView = new ModelAndView("content");
        modelAndView.addObject(user);
        modelAndView.addObject("planets", planets);
        return modelAndView;
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public ModelAndView log(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", "Неверный адрес почты или пароль");
        }

        if (logout != null) {
            model.addObject("msg", "Вы успешно вышли");
        }
        model.setViewName("loginPage");

        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(User user, BindingResult result) {

        user.setPassword(encoder.encode(user.getPassword()));
        ModelAndView modelAndView = new ModelAndView("loginPage");
        userService.addUser(user);
        modelAndView.addObject(user);

        return modelAndView;
    }
}

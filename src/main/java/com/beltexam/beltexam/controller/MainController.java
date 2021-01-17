package com.beltexam.beltexam.controller;

import com.beltexam.beltexam.models.Show;
import com.beltexam.beltexam.models.User;
import com.beltexam.beltexam.services.ShowService;
import com.beltexam.beltexam.services.UserService;
import com.beltexam.beltexam.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final ShowService showService;

    public MainController(UserService userService, UserValidator userValidator, ShowService showService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.showService = showService;
    }

    @RequestMapping("/")
    public String index(@ModelAttribute("user") User user) {
        return "index.jsp";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "index.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/shows";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
                            HttpSession session, @ModelAttribute("user") User user) {
        boolean isAuthenticated = userService.authenticateUser(email, password);
        if (isAuthenticated) {
            User u = userService.findByEmail(email);
            session.setAttribute("userId", u.getId());
            return "redirect:/shows";
        } else {
            model.addAttribute("error", "Invalid. Please try again.");
            return "index.jsp";
        }
    }

    @RequestMapping("/shows")
    public String homepage(HttpSession session, Model model) {

        if (session.getAttribute("userId") != null) {
            Long userId = (Long) session.getAttribute("userId");
            User u = userService.findUserById(userId);
            model.addAttribute("user", u);
            List<Show> showlist = showService.getAll();
            model.addAttribute("shows", showlist);
            return "homePage.jsp";
        } else {
            System.out.println("You should login");
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/shows/new")
    public String displayShowCreation(@ModelAttribute("show") Show myShow, Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            List<User> allusers = userService.findAll();
            model.addAttribute("users", allusers);
            Long userId = (Long) session.getAttribute("userId");
            User u = userService.findUserById(userId);
            model.addAttribute("currentUser", u);
            return "show.jsp";
        } else {
            System.out.println("You have to login");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/shows/new", method = RequestMethod.POST)
    public String createNewShow(@Valid @ModelAttribute("show") Show myShow, BindingResult result, HttpSession session,
                                Model model, RedirectAttributes limitError) {
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        model.addAttribute("user", u);

        if (result.hasErrors()) {
            List<User> allusers = userService.findAll();
            model.addAttribute("users", allusers);
            return "show.jsp";
        } else {
            Show newShow = showService.createShow(myShow);
            newShow.setCreator(u);
            showService.updateShow(newShow);
            return "redirect:/shows";
        }
    }

    @RequestMapping("/shows/{id}")
    public String displayShow(Model model, HttpSession session, @PathVariable("id") Long showId) {
        Show thisShow = showService.findShow(showId);
        model.addAttribute("show", thisShow);
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        model.addAttribute("currentUserId", u.getId());
        return "showshow.jsp";
    }

    @RequestMapping("/shows/{id}/edit")
    public String displayEditPage(Model model, @ModelAttribute("editshow") Show myShow, @PathVariable("id") Long showId,
                                  HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User u = userService.findUserById(userId);
        Show editingShow = showService.findShow(showId);
        if (u.getId() == editingShow.getCreator().getId()) {
            List<User> allUsers = userService.findAll();
            model.addAttribute("creator", editingShow.getCreator());
            model.addAttribute("editshow", editingShow);
            model.addAttribute("users", allUsers);
            model.addAttribute("id", editingShow.getId());
            return "editshow.jsp";
        } else {
            System.out.println("You are not a creator of this show ... get loss !");
            return "redirect:/shows";
        }
    }

    @RequestMapping(value = "/shows/{id}/edit", method = RequestMethod.POST)
    public String updateShow(Model model, @Valid @ModelAttribute("editshow") Show myShow, BindingResult result,
                             @PathVariable("id") Long showId) {
        System.out.println("Im in update routing");

        if (result.hasErrors()) {
            List<User> allusers = userService.findAll();
            model.addAttribute("users", allusers);
            return "editshow.jsp";
        } else {
            showService.createShow(myShow);
            return "redirect:/shows";
        }
    }

    @RequestMapping("/shows/{id}/delete")
    public String deleteShow(@PathVariable("id") Long myId) {
        Show delshow = showService.findShow(myId);
        if (delshow != null) {
            showService.deleteShow(myId);
            return "redirect:/shows";
        } else {
            return "redirect:/shows";
        }
    }

}
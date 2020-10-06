package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/add")
    public String displayAddUserForm(Model model, User user) {
        model.addAttribute(new User());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getPassword());
        return "user/add";
    }

    @PostMapping
    public String processAddUserForm(Model model, String verify, @ModelAttribute @Valid User user, Errors errors) {
        model.addAttribute("verify", verify);

        if (errors.hasErrors()){
            model.addAttribute("errorMsg", "There are errors in the data");
            return "user/add";
        }

        if (user.getPassword().equals(verify)) {
           return "user/index";
        }
        else {
            model.addAttribute("error", "Passwords do not match");
            return "user/add";
        }



    }


}

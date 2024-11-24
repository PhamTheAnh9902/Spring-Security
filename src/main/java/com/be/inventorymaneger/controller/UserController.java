package com.be.inventorymaneger.controller;

import com.be.inventorymaneger.model.Role;
import com.be.inventorymaneger.model.User;
import com.be.inventorymaneger.repository.RoleRepository;
import com.be.inventorymaneger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/logout")
    public String showLogoutForm(){
        return "logout";
    }


    @PostMapping("/process_register")
    public String processRegister(@Valid User user, BindingResult bindingResult, Model model){
        model.addAttribute("user",user);
        if(bindingResult.hasErrors()){
            return "register";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role role = roleRepository.findByDescription("USER");
        if (role != null){
            user.getRoles().add(role);
        }
        userRepository.save(user);
        return "redirect:/login";
    }
}

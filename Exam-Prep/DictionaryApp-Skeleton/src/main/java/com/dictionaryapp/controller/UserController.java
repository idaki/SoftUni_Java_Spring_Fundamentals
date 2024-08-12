package com.dictionaryapp.controller;

import com.dictionaryapp.model.dto.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
//g
    @ModelAttribute("registerData")
    public RegisterDto createEmptyDTO() {
        return new RegisterDto();
    }


    @GetMapping("/register")
    public String viewRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister (@Valid RegisterDto registerDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
if (bindingResult.hasErrors()) {
    redirectAttributes.addFlashAttribute("registerData", registerDto);
    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData",bindingResult);

    return "redirect:/register";

}

        return "redirect:/login";
    }



}
package com.philately.controller;

import com.philately.config.UserSession;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.UserRepository;
import com.philately.service.StampService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class HomeController {
    private final UserSession userSession;
    private final StampService stampService;
    private final UserRepository userRepository;

    public HomeController(UserSession userSession, StampService stampService, UserRepository userRepository) {
        this.userSession = userSession;
        this.stampService = stampService;
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String nonLoggedIndex() {
        if (userSession.isUserLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }



    @GetMapping("/home")
    @Transactional
    public String loggedInIndex(Model model) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        String username = userSession.getUsername();
        User loggedInUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("No user found with username: " + username));

        // Fetch data in one call if possible
        List<Stamp> myStamps = stampService.findStampsByUser(loggedInUser);
        List<Stamp> myPurchases = loggedInUser.getPurchasedStamps();
        List<Stamp> offeredStamps = stampService.findOfferedStamps(loggedInUser);
        List<Stamp> wishlist = loggedInUser.getWishedStamps();

        // Add attributes to the model
        model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("myStamps", myStamps);
        model.addAttribute("myPurchases", myPurchases);
        model.addAttribute("offeredStamps", offeredStamps);
        model.addAttribute("wishlist", wishlist);

        return "home";
    }





}

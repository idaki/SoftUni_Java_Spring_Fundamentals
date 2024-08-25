package com.philately.controller;

import com.philately.config.UserSession;
import com.philately.model.dto.AddStampDTO;
import com.philately.model.entity.PaperType;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import com.philately.service.StampService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
@Controller
public class StampController {

    private final StampService stampService;
    private final UserSession userSession;
    private final UserRepository userRepository;
    private final StampRepository stampRepository;

    public StampController(StampService stampService, UserSession userSession, UserRepository userRepository, StampRepository stampRepository) {
        this.stampService = stampService;
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.stampRepository = stampRepository;
    }

    @ModelAttribute("paperTypes")
    public List<String> paperTypes() {
        return Arrays.stream(PaperType.values())
                .map(PaperType::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/add-stamp")
    public String addStamp(Model model) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        // Add a new instance of AddStampDTO to the model
        model.addAttribute("addStampDTO", new AddStampDTO());

        return "add-stamp"; // This should be the name of the HTML template file without the extension
    }

    @PostMapping("/add-stamp")
    public String doAddStamp(
            @Valid @ModelAttribute("addStampDTO") AddStampDTO addStampDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addStampDTO", addStampDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addStampDTO", bindingResult);
            return "redirect:/add-stamp";
        }

        String username = userSession.getUsername();
        User loggedInUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("No user found with username: " + username));

        boolean success = stampService.create(addStampDTO, loggedInUser);

        if (!success) {
            redirectAttributes.addFlashAttribute("addStampDTO", addStampDTO);
            redirectAttributes.addFlashAttribute("errorMessage", "Error occurred while creating the stamp.");
            return "redirect:/add-stamp";
        }

        return "redirect:/home";
    }

    @PostMapping("add-to-wishlist/{stampId}")
    public String addToWishList(@PathVariable long stampId){
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        stampService.addToWishlist(userSession.getId(), stampId);

        return "redirect:/home";
    }

    @GetMapping("remove-from-wishlist/{stampId}")
    public String removeFromWishList(@PathVariable long stampId){
        if (!userSession.isUserLoggedIn()) {
            return "redirect:/";
        }

        stampService.removeFromWishlist(userSession.getId(), stampId);

        return "redirect:/home";
    }


}

package com.example.demo.controller;

import com.example.demo.dto.AddBuahReqDto;
import com.example.demo.dto.EditBuahReqDto;
import com.example.demo.dto.LoginReqDto;
import com.example.demo.service.*;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ViewController {

    @Autowired
    private ListBuahService listBuahService;

    @Autowired
    private GetBuahService getBuahService;

    @Autowired
    private AddBuahService addBuahService;

    @Autowired
    private EditBuahService editBuahService;

    @Autowired
    private DeleteBuahService deleteBuahService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
    public String index() {
        return "buah/index";
    }

    @GetMapping("/list")
    public String buahList(Model model, HttpServletRequest request) {

        model.addAttribute("items", listBuahService.buahList());
        return "buah/list-buah";
    }

    @GetMapping("/add")
    public String createBuah(Model model) {
        model.addAttribute("buah", new AddBuahReqDto());
        return "buah/add-buah";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        var response = getBuahService.getBuahById(id);

        if (!response.getResponseCode().equals("00")) {
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            return "redirect:/list";
        }

        model.addAttribute("buah", response.getResult());
        return "buah/edit-buah";
    }

    @PostMapping("/saveUpdate")
    public String saveBuah(@ModelAttribute EditBuahReqDto reqDto, RedirectAttributes redirectAttributes) {

        var checkData = reqDto;

        var response = editBuahService.editBuah(reqDto);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/list";
    }

    @PostMapping("/save")
    public String saveBuah(@ModelAttribute AddBuahReqDto reqDto, RedirectAttributes redirectAttributes) {

        var checkData = reqDto;

        var response = addBuahService.addBuah(reqDto);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteBuah(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        var response = deleteBuahService.deleteBuah(id);
        redirectAttributes.addFlashAttribute("message", response.getMessage());
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "buah/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setUsername(username);
        loginReqDto.setPassword(password);
        jwtUtil.generateToken(loginReqDto.getUsername());
        return "redirect:/list"; // Redirect to home or another page after login
    }
}

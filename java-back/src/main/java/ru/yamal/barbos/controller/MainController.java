package ru.yamal.barbos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yamal.barbos.configuration.security.ftl.CookieUtil;
import ru.yamal.barbos.configuration.security.ftl.FtlAuthProvider;
import ru.yamal.barbos.exception.CustomException;
import ru.yamal.barbos.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO temporary created for hackathon. In production it is preferable to use vue.js
@RequiredArgsConstructor
@Controller
@RequestMapping("/web/")
public class MainController {

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    private final UserService userService;
    private final FtlAuthProvider ftlAuthProvider;

    @GetMapping
    public String index() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse httpServletResponse) {
        CookieUtil.clear(httpServletResponse, jwtTokenCookieName);
        return "redirect:/web/";
    }

    @PostMapping(value = "login")
    public String login(HttpServletResponse httpServletResponse, String phoneNumber, String password, String redirect, Model model) {
        try {
            String token = userService.signin(phoneNumber, password);
            CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
            return "redirect:/web/me";
        } catch (CustomException e) {
            return "login";
        }
    }

    @GetMapping("me")
    public String me(HttpServletRequest request, Model model) {
        User user = ftlAuthProvider.getUser(request);
        model.addAttribute("userName", user.getUsername());
        return "me";
    }
}
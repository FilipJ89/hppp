package com.pg.hppp.controllers;

import com.pg.hppp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"", "/", "index"})
    public String readMainPage() {
        return "index";
    }

    @GetMapping({"/login"})
    public String readLoginPage() { return "user/login"; }

    @GetMapping({"/register"})
    public String readRegisterPage() { return "user/register"; }

}
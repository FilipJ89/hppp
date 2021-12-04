package com.pg.hppp.controllers;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.User;
import com.pg.hppp.repositories.LineRepository;
import com.pg.hppp.services.LineService;
import com.pg.hppp.services.TestUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class ActionController {

    private final LineService lineService;
    private final TestUser testUser;
    private final LineRepository lineRepository;

    @GetMapping("/risks/actions/{lineId}")
    public String showLineActions(@PathVariable("lineId") Integer id, Model model, User authUser) {
        Set<Line> authLines = lineService.getAllLinesForAuthUser(testUser.getTestUser(1));
        Set<Integer> authLinesIds = authLines.stream().map(line -> line.getId()).collect(Collectors.toSet());
        if (authLinesIds.contains(id)) {
            Line line = lineRepository.findById(id).orElse(null);
            model.addAttribute("line",line);
            model.addAttribute("actions", line.getActions());
            return "/action/showActions";
        } else {
            return "/error"; // todo if line id not found or authorized redirect to error page with comment
        }
    }
}

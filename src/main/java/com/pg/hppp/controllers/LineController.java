package com.pg.hppp.controllers;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.User;
import com.pg.hppp.repositories.UserRepository;
import com.pg.hppp.services.LineService;
import com.pg.hppp.services.MaterialFormFilter;
import com.pg.hppp.services.TestUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Set;

@AllArgsConstructor
@Controller
public class LineController {

    private final LineService lineService;
    private final TestUser testUser;

    @GetMapping("/materials")
    public String showMaterials(MaterialFormFilter lineFilterDefault, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", lineFilterDefault.builder().build());

        Set<Line> lines = lineService.getAllLinesForAuthUser(testUser.getTestUser(1));
        model.addAttribute("lines", lines);

        return "line/materials";
    }

    @PostMapping("/materials")
    public String filterMaterials(MaterialFormFilter lineFilterForm, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", lineFilterForm);

        Set<Line> lines = lineService.getAllLinesForAuthUserFilterMaterials(testUser.getTestUser(1),lineFilterForm);
        model.addAttribute("lines", lines);

        return "line/materials";
    }

    @GetMapping("/risks")
    public String showRisks(MaterialFormFilter lineFilterForm, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("filter", lineFilterForm);

        Set<Line> lines = lineService.getAllLinesWithRisksFilteredForAuthUser(testUser.getTestUser(1));
        model.addAttribute("lines", lines);

        return "line/risks";
    }

    @PostMapping("/risks")
    public String filterRisks(MaterialFormFilter lineFilterForm, Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("filter", lineFilterForm);

        Set<Line> lines = lineService.getAllLinesForAuthUserFilterRisks(testUser.getTestUser(1),lineFilterForm);
        model.addAttribute("lines", lines);

        return "line/risks";
    }
}

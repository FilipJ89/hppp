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
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Set;

@AllArgsConstructor
@Controller
public class LineController {

    private final LineService lineService;
    private final TestUser testUser;

    @RequestMapping("/materials")
    public String showMaterials(MaterialFormFilter lineFilterDefault, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", lineFilterDefault);

        Set<Line> authLines = lineService.getAllLinesForAuthUser(testUser.getTestUser(1));
        Set<Line> authLinesFiltered = lineService.getAllLinesFiltered(authLines, lineFilterDefault);
        model.addAttribute("lines", authLinesFiltered);

        return "line/materials";
    }

    @RequestMapping("/risks")
    public String showRisks(MaterialFormFilter lineFilterForm, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", lineFilterForm);

        Set<Line> authLinesRisk = lineService.getAllLinesWithRisksFilteredForAuthUser(testUser.getTestUser(1));
        Set<Line> authLinesRiskFiltered = lineService.getAllLinesFiltered(authLinesRisk,lineFilterForm);
        model.addAttribute("lines", authLinesRiskFiltered);

        return "line/risks";
    }

}

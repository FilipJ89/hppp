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
import java.util.TreeSet;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class LineController {

    private final LineService lineService;
    private final TestUser testUser;

    @RequestMapping("/materials")
    public String showMaterials(MaterialFormFilter lineFilter, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", lineFilter);
        Set<Line> authLines = lineService.getAllLinesForAuthUser(testUser.getTestUser(1));
        Set<Line> authLinesFiltered = lineService.getAllLinesFiltered(authLines, lineFilter);

        Set<String> materialFamilies = authLines.stream().map(family -> family.getMaterial().getMaterialFamily()).collect(Collectors.toSet());
        TreeSet<String> materialFamiliesDescending = new TreeSet<>(materialFamilies);

        model.addAttribute("lines", authLinesFiltered);
        model.addAttribute("families", materialFamiliesDescending);

        return "line/materials";
    }

    @RequestMapping("/risks")
    public String showRisks(MaterialFormFilter lineFilterForm, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", lineFilterForm);

        Set<Line> authLinesRisk = lineService.getAllLinesWithRisksFilteredForAuthUser(testUser.getTestUser(1));
        Set<Line> authLinesRiskFiltered = lineService.getAllLinesFiltered(authLinesRisk,lineFilterForm);

        Set<String> materialFamilies = authLinesRisk.stream().map(family -> family.getMaterial().getMaterialFamily()).collect(Collectors.toSet());
        TreeSet<String> materialFamiliesDescending = new TreeSet<>(materialFamilies);

        model.addAttribute("families", materialFamiliesDescending);
        model.addAttribute("lines", authLinesRiskFiltered);

        return "line/risks";
    }

}

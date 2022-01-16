package com.pg.hppp.controllers;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.User;
import com.pg.hppp.services.LineService;
import com.pg.hppp.services.MaterialFormFilter;
import com.pg.hppp.services.TestUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class RiskController {

    private final LineService lineService;
    private final TestUser testUser;
    private Set<Line> lastFilteredRiskLines;

    @RequestMapping("/risks")
    public String showRisks(MaterialFormFilter filter, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", filter);

        Set<Line> authLinesRisk = lineService.getAllLinesWithRisksFilteredForAuthUser(testUser.getTestUser(1));
        Set<Line> authLinesRiskFiltered = lineService.getAllLinesFiltered(authLinesRisk,filter);
        lastFilteredRiskLines = new HashSet<>(authLinesRiskFiltered); // todo how to pass it more elegantly to below controller?

        Set<String> materialFamilies = authLinesRisk.stream().map(family -> family.getMaterial().getMaterialFamily()).collect(Collectors.toSet());
        TreeSet<String> materialFamiliesDescending = new TreeSet<>(materialFamilies);

        model.addAttribute("families", materialFamiliesDescending);
        model.addAttribute("lines", authLinesRiskFiltered);

        return "risk/showRisks";
    }

    @GetMapping("/risks/edit")
    public String editRisks(MaterialFormFilter filter, Model model) {

        model.addAttribute("filter", filter);
        model.addAttribute("lines", lastFilteredRiskLines);

        return "risk/updateRisks";
    }
}

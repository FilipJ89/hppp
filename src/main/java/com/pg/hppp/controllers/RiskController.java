package com.pg.hppp.controllers;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.User;
import com.pg.hppp.services.LineService;
import com.pg.hppp.services.MaterialFormFilter;
import com.pg.hppp.services.RiskService;
import com.pg.hppp.services.TestUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class RiskController {

    private final LineService lineService;
    private final RiskService riskService;
    private final TestUser testUser;
    private Set<Line> lastFilteredLines;

    @GetMapping("/risks")
    public String showRisks(Model model, @AuthenticationPrincipal User authUser) {

        Set<Line> authLinesRisk = lineService.getAllLinesForAuthUserIsRisk(true, testUser.getTestUser(1));
        model.addAttribute("lines", authLinesRisk);

        return "risk/showRisks";
    }

    @RequestMapping("/risks/update")
    public String updateRisks(MaterialFormFilter filter, Model model) {
        Set<Line> authLinesRisk = lineService.getAllLinesForAuthUserIsRisk(true, testUser.getTestUser(1));
        Set<Line> authLinesRiskFiltered = lineService.getAllLinesFiltered(authLinesRisk,filter);

        Set<String> materialFamilies = authLinesRisk.stream().map(family -> family.getMaterial().getMaterialFamily()).collect(Collectors.toSet());
        TreeSet<String> materialFamiliesDescending = new TreeSet<>(materialFamilies);
        model.addAttribute("families", materialFamiliesDescending);
        model.addAttribute("filter", filter);
        model.addAttribute("lines", authLinesRiskFiltered);

        lastFilteredLines = new HashSet<>(authLinesRiskFiltered); // todo find more elegant way to pass data between controllers

        return "risk/updateRisks";
    }

    @GetMapping("/risks/update/edit")
    public String editRisksForm(MaterialFormFilter filter, Model model) {

        model.addAttribute("filter", filter);
        model.addAttribute("lines", lastFilteredLines);

        return "risk/editRisks";
    }

    @PostMapping("/risks/update/edit")
    public String editRisksProcess(MaterialFormFilter filter, Model model) {

        model.addAttribute("filter", filter);
        riskService.updateRisks(lastFilteredLines,filter);

        return "redirect:/risks";
    }

    @RequestMapping("/risks/new")
    public String newRisks(MaterialFormFilter filter, Model model) {
        Set<Line> authLinesRisk = lineService.getAllLinesForAuthUserIsRisk(false, testUser.getTestUser(1));
        Set<Line> authLinesRiskFiltered = lineService.getAllLinesFiltered(authLinesRisk,filter);

        Set<String> materialFamilies = authLinesRisk.stream().map(family -> family.getMaterial().getMaterialFamily()).collect(Collectors.toSet());
        TreeSet<String> materialFamiliesDescending = new TreeSet<>(materialFamilies);
        model.addAttribute("families", materialFamiliesDescending);
        model.addAttribute("filter", filter);
        model.addAttribute("lines", authLinesRiskFiltered);

        lastFilteredLines = new HashSet<>(authLinesRiskFiltered);

        return "risk/newRisks";
    }

    @GetMapping("/risks/new/add")
    public String newRisksForm(MaterialFormFilter filter, Model model) {

        model.addAttribute("filter", filter);
        model.addAttribute("lines", lastFilteredLines);

        return "risk/addRisks";
    }

    @PostMapping("/risks/new/add")
    public String newRisksProcess(MaterialFormFilter filter, Model model) {

        model.addAttribute("filter", filter);
        riskService.updateRisks(lastFilteredLines,filter);

        return "redirect:/risks";
    }
}

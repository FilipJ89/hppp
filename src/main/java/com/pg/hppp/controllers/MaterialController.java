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
public class MaterialController {

    private final LineService lineService;
    private final TestUser testUser;

    @RequestMapping("/materials")
    public String showMaterials(MaterialFormFilter filter, Model model, @AuthenticationPrincipal User authUser) {
        model.addAttribute("filter", filter);
        Set<Line> authLines = lineService.getAllLinesForAuthUser(testUser.getTestUser(1));
        Set<Line> authLinesFiltered = lineService.getAllLinesFiltered(authLines, filter);

        Set<String> materialFamilies = authLines.stream().map(family -> family.getMaterial().getMaterialFamily()).collect(Collectors.toSet());
        TreeSet<String> materialFamiliesDescending = new TreeSet<>(materialFamilies);

        model.addAttribute("lines", authLinesFiltered);
        model.addAttribute("families", materialFamiliesDescending);

        return "material/showMaterials";
    }
}

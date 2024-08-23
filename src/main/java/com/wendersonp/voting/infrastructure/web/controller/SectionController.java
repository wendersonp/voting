package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.OpenSectionDTO;
import com.wendersonp.voting.application.service.ISectionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SectionController {

    private final ISectionService sectionService;


    public SectionController(ISectionService sectionService) {
        this.sectionService = sectionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/abrir-secao")
    public void openSection(@RequestBody @Valid OpenSectionDTO openSectionDTO) {
        sectionService.openSection(openSectionDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/fechar-secao")
    public void closeSection(@RequestParam(name = "idSessao") UUID sectionId) {
        sectionService.closeSection(sectionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/boletim-urna/{idSessao}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getSectionBulletin(@PathVariable(name = "idSessao") UUID sectionId) {
        return sectionService.generateBulletin(sectionId);
    }
}

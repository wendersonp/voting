package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.OpenSectionDTO;
import com.wendersonp.voting.application.dto.ViewSectionDTO;
import com.wendersonp.voting.application.service.ISectionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Seções", description = "Rotas para gerenciar cadastro de seções (abrir, fechar, buscar e gerar boletim)")
public class SectionController {

    private final ISectionService sectionService;


    public SectionController(ISectionService sectionService) {
        this.sectionService = sectionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/abrir-secao")
    @Operation(summary = "Cria uma nova seção de votação, com candidatos e cargo em disputa")
    public void openSection(@RequestBody @Valid OpenSectionDTO openSectionDTO) {
        sectionService.openSection(openSectionDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/fechar-secao")
    @Operation(summary = "Fecha uma seção que foi aberta", description = "Apenas seções abertas podem ser fechadas")
    public void closeSection(@RequestParam(name = "idSessao") UUID sectionId) {
        sectionService.closeSection(sectionId);
    }

    @GetMapping("/secao")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retorna uma página com as seções", description = "São fornecidos dados de id da seção, status " +
            "data de abertura e data de fechamento da seção")
    public ResponseEntity<List<ViewSectionDTO>> findAll(Pageable pageRequest) {
        Page<ViewSectionDTO> page = sectionService.findAll(pageRequest);
        return ResponseEntity
                .ok()
                .header("totalPages", String.valueOf(page.getTotalPages()))
                .header("totalElements", String.valueOf(page.getTotalElements()))
                .body(page.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/boletim-urna/{idSessao}", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Faz apuração de uma seção e gera seu boletim",
            description = "A seção é apurada e seu boletim é armazenado em cache e retornado. " +
                    "Se a seção recebeu apenas um voto, não é possível garantir a anonimidade dos eleitores, " +
                    "então não há vencedores. Caso a votação resulte em empate, também não haverá vencedores ")
    public String getSectionBulletin(@PathVariable(name = "idSessao") UUID sectionId) {
        return sectionService.generateBulletin(sectionId);
    }
}

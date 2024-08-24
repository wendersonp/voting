package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.CandidateDTO;
import com.wendersonp.voting.application.service.ICandidateService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidatos")
public class CandidateController {

    private final ICandidateService candidateService;

    public CandidateController(ICandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CandidateDTO candidateDTO) {
        candidateService.create(candidateDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO findById(@PathVariable UUID id) {
        return candidateService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CandidateDTO>> findAll(Pageable pageRequest) {
        Page<CandidateDTO> page = candidateService.findAll(pageRequest);
        return ResponseEntity
                .ok()
                .header("totalPages", String.valueOf(page.getTotalPages()))
                .header("totalElements", String.valueOf(page.getTotalElements()))
                .body(page.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody @Valid CandidateDTO candidateDTO) {
        candidateService.update(id, candidateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        candidateService.delete(id);
    }
}

package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.PositionDTO;
import com.wendersonp.voting.application.service.IPositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/cargos")
@Tag(name = "Cargos", description = "Rotas para gerenciar cadastro de cargos")
public class PositionController {

    private final IPositionService positionService;

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra um cargo")
    public void create(@RequestBody @Valid PositionDTO positionDTO) {
        positionService.create(positionDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um cargo por seu Id")
    public PositionDTO findById(@PathVariable UUID id) {
        return positionService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca uma página de cargos registrados")
    public ResponseEntity<List<PositionDTO>> findAll(Pageable pageRequest) {
        Page<PositionDTO> page = positionService.findAll(pageRequest);
        return ResponseEntity
                .ok()
                .header("totalPages", String.valueOf(page.getTotalPages()))
                .header("totalElements", String.valueOf(page.getTotalElements()))
                .body(page.toList());
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza o nome de um cargo")
    public void update(@PathVariable UUID id, @RequestBody @Valid PositionDTO positionDTO) {
        positionService.update(id, positionDTO);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Remove um cargo do registro")
    public void delete(@PathVariable UUID id) {
        positionService.delete(id);
    }
}

package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.PositionDTO;
import com.wendersonp.voting.application.service.IPositionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cargos")
public class PositionController {

    private final IPositionService positionService;

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid PositionDTO positionDTO) {
        positionService.create(positionDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PositionDTO findById(@PathVariable UUID id) {
        return positionService.findById(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PositionDTO> findAll(Pageable pageRequest) {
        return positionService.findAll(pageRequest);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody @Valid PositionDTO positionDTO) {
        positionService.update(id, positionDTO);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        positionService.delete(id);
    }
}

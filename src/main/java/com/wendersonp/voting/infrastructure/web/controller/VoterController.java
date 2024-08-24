package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.VoteDTO;
import com.wendersonp.voting.application.dto.VoterDTO;
import com.wendersonp.voting.application.service.IVoteService;
import com.wendersonp.voting.application.service.IVoterService;
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
@RequestMapping("/eleitores")
@Tag(name = "Eleitores", description = "Rotas para gerenciar cadastro de eleitores e registrar votos")
public class VoterController {

    private final IVoteService voteService;

    private final IVoterService voterService;

    public VoterController(IVoteService voteService, IVoterService voterService) {
        this.voteService = voteService;
        this.voterService = voterService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra um novo eleitor")
    public void create(@RequestBody @Valid VoterDTO voterDTO) {
        voterService.create(voterDTO);
    }

    @PostMapping("/{id}/votar")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra um voto de um eleitor",
            description = "Um eleitor pode votar em uma seção apenas uma vez. " +
            "Apenas seções abertas podem receber votos"
    )
    public void registerVote(@PathVariable(name = "id") UUID voterId, @RequestBody VoteDTO vote) {
        voteService.registerVote(voterId, vote);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca um eleitor registrado por seu Id")
    public VoterDTO findById(@PathVariable UUID id) {
        return voterService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca uma página com eleitores")
    public ResponseEntity<List<VoterDTO>> findAll(Pageable pageRequest) {
        Page<VoterDTO> page = voterService.findAll(pageRequest);
        return ResponseEntity
                .ok()
                .header("totalPages", String.valueOf(page.getTotalPages()))
                .header("totalElements", String.valueOf(page.getTotalElements()))
                .body(page.toList());
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza o nome de um eleitor")
    public void update(@PathVariable UUID id, @RequestBody @Valid VoterDTO voterDTO) {
        voterService.update(id, voterDTO);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Remove o registro de um eleitor", description = "Apenas eleitores que nunca votaram podem " +
            "ter seu registro apagado")
    public void delete(@PathVariable UUID id) {
        voterService.delete(id);
    }
}

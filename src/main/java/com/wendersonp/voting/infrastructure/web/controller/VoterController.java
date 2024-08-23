package com.wendersonp.voting.infrastructure.web.controller;

import com.wendersonp.voting.application.dto.VoteDTO;
import com.wendersonp.voting.application.dto.VoterDTO;
import com.wendersonp.voting.application.service.IVoteService;
import com.wendersonp.voting.application.service.IVoterService;
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
@RequestMapping("/eleitores")
public class VoterController {

    private final IVoteService voteService;

    private final IVoterService voterService;

    public VoterController(IVoteService voteService, IVoterService voterService) {
        this.voteService = voteService;
        this.voterService = voterService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid VoterDTO voterDTO) {
        voterService.create(voterDTO);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerVote(@PathVariable UUID voterId, @RequestBody VoteDTO vote) {
        voteService.registerVote(voterId, vote);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VoterDTO findById(@PathVariable UUID id) {
        return voterService.findById(id);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<VoterDTO> findAll(Pageable pageRequest) {
        return voterService.findAll(pageRequest);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody @Valid VoterDTO voterDTO) {
        voterService.update(id, voterDTO);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        voterService.delete(id);
    }
}

package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.CandidateDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.ICandidateService;
import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.repository.ICandidateRepository;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CandidateServiceImpl implements ICandidateService {

    public static final Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

    private final ICandidateRepository repository;

    private final IVoteRepository voteRepository;

    public CandidateServiceImpl(ICandidateRepository repository, IVoteRepository voteRepository) {
        this.repository = repository;
        this.voteRepository = voteRepository;
    }


    @Override
    public void create(CandidateDTO candidateDTO) {
        try {
            final var candidateEntity = candidateDTO.toEntity();
            repository.save(candidateEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException(ErrorMessages.CANDIDATE_ALREADY_EXISTS, ex);
        }
    }

    @Override
    public CandidateDTO findById(UUID id) {
        var candidateEntity = repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
        return new CandidateDTO(candidateEntity);
    }

    @Override
    public Page<CandidateDTO> findAll(Pageable pageRequest) {
        try {
            return repository.findAll(pageRequest).map(CandidateDTO::new);
        } catch (PropertyReferenceException exception) {
            throw new BadRequestException(ErrorMessages.SORT_FIELD_DOESNT_EXIST);
        }
    }

    @Override
    public void update(UUID id, CandidateDTO candidateDTO) {
        validateIfExists(id);
        repository.save(candidateDTO.toEntity());
    }

    @Override
    public void delete(UUID id) {
        validateIfExists(id);
        validateIfAlreadyReceivedVote(id);
        repository.deleteById(id);
    }

    private void validateIfAlreadyReceivedVote(UUID id) {
        if (voteRepository.existsByCandidateId(id)) {
            throw new BadRequestException(ErrorMessages.CANDIDATE_CANNOT_BE_DELETED);
        }
    }

    private void validateIfExists(UUID candidateId) {
        if (!repository.existsById(candidateId)) {
            throw new NotFoundException();
        }
    }
}

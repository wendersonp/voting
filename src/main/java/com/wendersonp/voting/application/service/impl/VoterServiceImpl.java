package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.VoterDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.IVoterService;
import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import com.wendersonp.voting.domain.repository.IVoterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoterServiceImpl implements IVoterService {
    public static final Logger logger = LoggerFactory.getLogger(VoterServiceImpl.class);

    private final IVoterRepository repository;

    private final IVoteRepository voteRepository;

    public VoterServiceImpl(IVoterRepository repository, IVoteRepository voteRepository) {
        this.repository = repository;
        this.voteRepository = voteRepository;
    }


    @Override
    public void create(VoterDTO voterDTO) {
        try {
            final var voterEntity = voterDTO.toEntity();
            repository.save(voterEntity);
        } catch (DataIntegrityViolationException ex) {
            logger.warn("{}; name: {}", ErrorMessages.VOTER_ALREADY_EXISTS, voterDTO.name());
            throw new BadRequestException(ErrorMessages.VOTER_ALREADY_EXISTS, ex);
        }
    }

    @Override
    public VoterDTO findById(UUID id) {
        var voterEntity = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.VOTER_NOT_FOUND));
        return new VoterDTO(voterEntity);
    }

    @Override
    public Page<VoterDTO> findAll(Pageable pageRequest) {
        try {
            return repository.findAll(pageRequest).map(VoterDTO::new);
        } catch (PropertyReferenceException exception) {
            throw new BadRequestException(ErrorMessages.SORT_FIELD_DOESNT_EXIST);
        }
    }

    @Override
    public void update(UUID id, VoterDTO voterDTO) {
        validateIfExists(id);
        repository.save(voterDTO.toEntity(id));
    }

    @Override
    public void delete(UUID id) {
        validateIfExists(id);
        validateIfAlreadyVoted(id);
        repository.deleteById(id);
    }

    private void validateIfAlreadyVoted(UUID id) {
        if (voteRepository.existsByVoterId(id)) {
            logger.warn("{}; id: {}", ErrorMessages.VOTER_CANNOT_BE_DELETED, id);
            throw new BadRequestException(ErrorMessages.VOTER_CANNOT_BE_DELETED);
        }
    }

    private void validateIfExists(UUID id) {
        if (!repository.existsById(id)) {
            logger.warn("{}; id: {}", ErrorMessages.VOTER_NOT_FOUND, id);
            throw new NotFoundException(ErrorMessages.VOTER_NOT_FOUND);
        }
    }
}

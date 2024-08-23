package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.VoterDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.IVoterService;
import com.wendersonp.voting.domain.repository.IVoterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VoterServiceImpl implements IVoterService {
    public static final Logger LOGGER = LoggerFactory.getLogger(VoterServiceImpl.class);

    private final IVoterRepository repository;

    public VoterServiceImpl(IVoterRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(VoterDTO voterDTO) {
        try {
            final var voterEntity = voterDTO.toEntity();
            repository.save(voterEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("entity already present", ex);
        }
    }

    @Override
    public VoterDTO findById(UUID id) {
        var voterEntity = repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
        return new VoterDTO(voterEntity);
    }

    @Override
    public Page<VoterDTO> findAll(Pageable pageRequest) {
        return repository.findAll(pageRequest).map(VoterDTO::new);
    }

    @Override
    public void update(UUID id, VoterDTO voterDTO) {
        validateIfExists(id);
        repository.save(voterDTO.toEntity());
    }

    @Override
    public void delete(UUID id) {
        validateIfExists(id);
        repository.deleteById(id);
    }

    private void validateIfExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException();
        }
    }
}

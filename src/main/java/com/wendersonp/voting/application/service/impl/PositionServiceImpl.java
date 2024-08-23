package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.PositionDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.IPositionService;
import com.wendersonp.voting.domain.repository.IPositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PositionServiceImpl implements IPositionService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PositionServiceImpl.class);

    private final IPositionRepository repository;

    public PositionServiceImpl(IPositionRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(PositionDTO positionDTO) {
        try {
            final var positionEntity = positionDTO.toEntity();
            repository.save(positionEntity);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Posição já existe", ex);
        }
    }

    @Override
    public PositionDTO findById(UUID id) {
        var positionEntity = repository
                .findById(id)
                .orElseThrow(NotFoundException::new);
        return new PositionDTO(positionEntity);
    }

    @Override
    public Page<PositionDTO> findAll(Pageable pageRequest) {
        return repository.findAll(pageRequest).map(PositionDTO::new);
    }

    @Override
    public void update(UUID id, PositionDTO positionDTO) {
        validateIfExists(id);
        repository.save(positionDTO.toEntity());
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

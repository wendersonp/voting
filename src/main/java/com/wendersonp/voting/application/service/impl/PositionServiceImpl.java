package com.wendersonp.voting.application.service.impl;

import com.wendersonp.voting.application.dto.PositionDTO;
import com.wendersonp.voting.application.exception.BadRequestException;
import com.wendersonp.voting.application.exception.NotFoundException;
import com.wendersonp.voting.application.service.IPositionService;
import com.wendersonp.voting.application.util.ErrorMessages;
import com.wendersonp.voting.domain.repository.IPositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PositionServiceImpl implements IPositionService {
    public static final Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

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
            logger.warn("{}; name: {}", ErrorMessages.POSITION_ALREADY_EXISTS, positionDTO.name());
            throw new BadRequestException(ErrorMessages.POSITION_ALREADY_EXISTS, ex);
        }
    }

    @Override
    public PositionDTO findById(UUID id) {
        var positionEntity = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.POSITION_NOT_FOUND));
        return new PositionDTO(positionEntity);
    }

    @Override
    public Page<PositionDTO> findAll(Pageable pageRequest) {
        try {
            return repository.findAll(pageRequest).map(PositionDTO::new);
        } catch (PropertyReferenceException exception) {
            throw new BadRequestException(ErrorMessages.SORT_FIELD_DOESNT_EXIST);
        }
    }

    @Override
    public void update(UUID id, PositionDTO positionDTO) {
        validateIfExists(id);
        repository.save(positionDTO.toEntity(id));
    }

    @Override
    public void delete(UUID id) {
        validateIfExists(id);
        repository.deleteById(id);
    }

    private void validateIfExists(UUID id) {
        if (!repository.existsById(id)) {
            logger.warn("{}; id: {}", ErrorMessages.POSITION_NOT_FOUND, id);
            throw new NotFoundException(ErrorMessages.POSITION_NOT_FOUND);
        }
    }
}

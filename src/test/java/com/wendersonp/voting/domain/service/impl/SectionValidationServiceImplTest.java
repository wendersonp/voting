package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.fixture.SectionEntityFixture;
import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.repository.ICandidateRepository;
import com.wendersonp.voting.domain.repository.IPositionRepository;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import com.wendersonp.voting.util.Util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SectionValidationServiceImplTest {

    @Mock
    private ICandidateRepository candidateRepository;

    @Mock
    private IPositionRepository positionRepository;

    @Mock
    private IVoteRepository voteRepository;

    @InjectMocks
    private SectionValidationServiceImpl sectionValidationService;

    @DisplayName("section should be valid to open")
    @Test
    void sectionShouldBeValidToOpen() {
        SectionEntity section = SectionEntityFixture.buildToOpen();

        when(candidateRepository.countCandidatesById(any())).thenReturn(section.getCandidates().size());
        when(positionRepository.existsById(any())).thenReturn(true);

        boolean result = sectionValidationService.validateToOpenSection(
                section.getCandidates().stream().map(CandidateEntity::getId).collect(Collectors.toSet()),
                section.getRunningPosition().getId()
                );

        assertTrue(result);
    }

    @DisplayName("section should have candidates that does not exist")
    @Test
    void sectionShouldHaveCandidatesThatDoesNotExist() {
        SectionEntity section = SectionEntityFixture.buildToOpen();

        when(candidateRepository.countCandidatesById(any())).thenReturn(section.getCandidates().size());

        boolean result = sectionValidationService.validateToOpenSection(
                Util.generateSet(4, UUID::randomUUID),
                section.getRunningPosition().getId()
        );

        assertFalse(result);
    }

    @DisplayName("section should not have valid position id")
    @Test
    void sectionShouldNotHaveValidPositionId() {
        SectionEntity section = SectionEntityFixture.buildToOpen();

        when(candidateRepository.countCandidatesById(any())).thenReturn(section.getCandidates().size());
        when(positionRepository.existsById(any())).thenReturn(false);

        boolean result = sectionValidationService.validateToOpenSection(
                section.getCandidates().stream().map(CandidateEntity::getId).collect(Collectors.toSet()),
                section.getRunningPosition().getId()
        );

        assertFalse(result);
    }
}
package com.wendersonp.voting.domain.service.impl;

import com.wendersonp.voting.domain.fixture.CandidateEntityFixture;
import com.wendersonp.voting.domain.fixture.SectionEntityFixture;
import com.wendersonp.voting.domain.fixture.VoteEntityFixture;
import com.wendersonp.voting.domain.fixture.VoterEntityFixture;
import com.wendersonp.voting.domain.model.CandidateEntity;
import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.VoteEntity;
import com.wendersonp.voting.domain.model.VoterEntity;
import com.wendersonp.voting.domain.repository.ISectionRepository;
import com.wendersonp.voting.domain.repository.IVoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class VoteValidationServiceImplTest {

    @Mock
    private ISectionRepository sectionRepository;

    @Mock
    private IVoteRepository voteRepository;


    @InjectMocks
    private VoteValidationServiceImpl voteValidationService;

    @DisplayName("should verify that vote is valid")
    @Test
    void shouldVerifyThatVoteIsValid() {
        SectionEntity section = SectionEntityFixture.buildToOpen();
        CandidateEntity candidate = section.getCandidates().iterator().next();
        VoterEntity voter = VoterEntityFixture.build();
        VoteEntity vote = VoteEntityFixture.build(section, voter, candidate);

        Mockito.when(voteRepository
                .existsBySectionIdAndVoterId(
                        any(),
                        any()
                )).thenReturn(false);
        Mockito.when(sectionRepository.findById(any())).thenReturn(Optional.of(section));

        boolean result = voteValidationService.isVoteValid(vote);

        Assertions.assertTrue(result);
    }

    @DisplayName("should verify that vote already exists")
    @Test
    void shouldVerifyThatVoteAlreadyExists() {
        SectionEntity section = SectionEntityFixture.buildToOpen();
        CandidateEntity candidate = section.getCandidates().iterator().next();
        VoterEntity voter = VoterEntityFixture.build();
        VoteEntity vote = VoteEntityFixture.build(section, voter, candidate);

        Mockito.when(voteRepository
                .existsBySectionIdAndVoterId(
                        any(),
                        any()
                )).thenReturn(true);
        Mockito.when(sectionRepository.findById(any())).thenReturn(Optional.of(section));

        boolean result = voteValidationService.isVoteValid(vote);

        Assertions.assertFalse(result);
    }

    @DisplayName("should verify that vote is for not open section")
    @Test
    void shouldVerifyThatVoteIsForNotOpenSection() {
        SectionEntity section = SectionEntityFixture.buildToOpen();
        CandidateEntity candidate = section.getCandidates().iterator().next();
        VoterEntity voter = VoterEntityFixture.build();
        VoteEntity vote = VoteEntityFixture.build(section, voter, candidate);
        section.closeSection();

        Mockito.when(voteRepository
                .existsBySectionIdAndVoterId(
                        any(),
                        any()
                )).thenReturn(false);
        Mockito.when(sectionRepository.findById(any())).thenReturn(Optional.of(section));

        boolean result = voteValidationService.isVoteValid(vote);

        Assertions.assertFalse(result);
    }

    @DisplayName("should verify that vote is for candidate not running")
    @Test
    void shouldVerifyThatVoteIsForCandidateNotRunning() {
        SectionEntity section = SectionEntityFixture.buildToOpen();
        CandidateEntity candidate = CandidateEntityFixture.build();
        VoterEntity voter = VoterEntityFixture.build();
        VoteEntity vote = VoteEntityFixture.build(section, voter, candidate);

        Mockito.when(voteRepository
                .existsBySectionIdAndVoterId(
                        any(),
                        any()
                )).thenReturn(false);
        Mockito.when(sectionRepository.findById(any())).thenReturn(Optional.of(section));

        boolean result = voteValidationService.isVoteValid(vote);

        Assertions.assertFalse(result);
    }
}
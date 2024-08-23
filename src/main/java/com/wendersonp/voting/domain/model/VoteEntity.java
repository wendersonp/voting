package com.wendersonp.voting.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_vote")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fk_section")
    private SectionEntity section;

    @OneToOne
    @JoinColumn(name = "fk_candidate")
    private CandidateEntity candidate;

    @OneToOne
    @JoinColumn(name = "fk_voter")
    private VoterEntity voter;

    private LocalDateTime registeredTime = LocalDateTime.now();

    public VoteEntity() {
    }

    public VoteEntity(final UUID sectionId, final UUID candidateId, final UUID voterId) {
        section = new SectionEntity(sectionId);
        candidate = new CandidateEntity(candidateId);
        voter = new VoterEntity(voterId);
    }


    public UUID getId() {
        return id;
    }

    public SectionEntity getSection() {
        return section;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public VoterEntity getVoter() {
        return voter;
    }

    public LocalDateTime getRegisteredTime() {
        return registeredTime;
    }
}

package com.wendersonp.voting.domain.model;


import com.wendersonp.voting.domain.model.enumeration.SectionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_section")
public class SectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @OneToMany
    @JoinColumn(name = "fk_candidate")
    private Set<CandidateEntity> candidates;

    @ManyToOne
    private PositionEntity runningPosition;

    @OneToMany(mappedBy = "section")
    private Set<VoteEntity> votes;

    @Enumerated(value = EnumType.STRING)
    private SectionStatus status;


    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public SectionEntity(Set<UUID> runningCandidateIds, UUID positionId) {
        this.candidates = runningCandidateIds.stream().map(
                candidateId -> new CandidateEntity(candidateId, null)
        ).collect(Collectors.toSet());
        this.runningPosition = new PositionEntity(positionId, null);
    }

    public SectionEntity(UUID id) {
        this.id = id;
    }
    public SectionEntity() {
    }

    public SectionEntity(Set<CandidateEntity> candidates, PositionEntity runningPosition, SectionStatus status, LocalDateTime startDate) {
        this.candidates = candidates;
        this.runningPosition = runningPosition;
        this.status = status;
        this.startDate = startDate;
    }

    public UUID getId() {
        return id;
    }

    public Set<CandidateEntity> getCandidates() {
        return candidates;
    }

    public PositionEntity getRunningPosition() {
        return runningPosition;
    }

    public Set<VoteEntity> getVotes() {
        return votes;
    }

    public boolean isOpen() {
        return SectionStatus.OPEN.equals(status);
    }

    public boolean isClosed() {
        return SectionStatus.CLOSED.equals(status);
    }

    public void openSection() {
        if (Objects.isNull(status)) {
            startDate = LocalDateTime.now();
            status = SectionStatus.OPEN;
        }
    }

    public void closeSection() {
        if (SectionStatus.OPEN.equals(status)) {
            endDate = LocalDateTime.now();
            status = SectionStatus.CLOSED;
        }
    }
}

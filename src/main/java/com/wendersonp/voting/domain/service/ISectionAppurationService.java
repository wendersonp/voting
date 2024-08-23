package com.wendersonp.voting.domain.service;

import com.wendersonp.voting.domain.model.SectionEntity;
import com.wendersonp.voting.domain.model.SectionReportEntity;

import java.util.UUID;

public interface ISectionAppurationService {

    SectionReportEntity countVotes(SectionEntity sectionEntity);
}

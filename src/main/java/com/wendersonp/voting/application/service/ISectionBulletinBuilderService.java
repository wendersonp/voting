package com.wendersonp.voting.application.service;

import com.wendersonp.voting.domain.model.SectionReportEntity;

public interface ISectionBulletinBuilderService {

    String buildBulletin(SectionReportEntity sectionReportEntity);
}

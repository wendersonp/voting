package com.wendersonp.voting.application.service;

import com.wendersonp.voting.application.dto.OpenSectionDTO;

import java.util.UUID;

public interface ISectionService {

    void openSection(OpenSectionDTO sectionDTO);

    void closeSection(UUID sectionId);

    String generateBulletin(UUID sectionId);
}

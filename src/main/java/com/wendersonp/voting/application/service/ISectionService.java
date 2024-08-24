package com.wendersonp.voting.application.service;

import com.wendersonp.voting.application.dto.OpenSectionDTO;
import com.wendersonp.voting.application.dto.ViewSectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ISectionService {

    Page<ViewSectionDTO> findAll(Pageable pageable);

    void openSection(OpenSectionDTO sectionDTO);

    void closeSection(UUID sectionId);

    String generateBulletin(UUID sectionId);
}

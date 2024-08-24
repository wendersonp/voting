package com.wendersonp.voting.application.service;

import com.wendersonp.voting.application.dto.CandidateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICandidateService extends ICRUDService<CandidateDTO>{

}

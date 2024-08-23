package com.wendersonp.voting.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICRUDService<T> {

    void create(T candidate);

    T findById(UUID id);

    Page<T> findAll(Pageable pageRequest);

    void update(UUID id, T candidate);

    void delete(UUID id);
}

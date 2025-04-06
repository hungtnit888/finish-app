package com.example.base.service;

import com.example.base.dto.BaseDTO;
import com.example.base.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService<T extends BaseEntity, D extends BaseDTO, ID> {
    protected final JpaRepository<T, ID> repository;

    protected BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected abstract D convertToDTO(T entity);
    protected abstract T convertToEntity(D dto);

    @Transactional(readOnly = true)
    public List<D> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public D findById(ID id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Transactional
    public D save(D dto) {
        T entity = convertToEntity(dto);
        return convertToDTO(repository.save(entity));
    }

    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public D create(D dto) {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    public D update(ID id, D dto) {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    public void delete(ID id) {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }

    public Page<D> findAll(Pageable pageable) {
        // Implementation needed
        throw new UnsupportedOperationException("Method not implemented");
    }
} 
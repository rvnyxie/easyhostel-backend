package com.easyhostel.backend.domain.repository.interfaces.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for readonly method
 *
 * @param <TEntity> Abstract entity
 * @param <TId> Abstract type of ID
 * @author Nyx
 */
@NoRepositoryBean
public interface IBaseReadonlyRepository<TEntity, TId> extends JpaRepository<TEntity, TId> {
}

package com.easyhostel.backend.domain.repository.interfaces.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for modification methods
 *
 * @param <TEntity> Abstract entity
 * @param <TId> Abstract type of ID
 * @author Nyx
 */
@NoRepositoryBean
public interface IBaseRepository<TEntity, TId> extends IBaseReadonlyRepository<TEntity, TId> {
}

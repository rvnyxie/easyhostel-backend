package com.easyhostel.backend.application.service.implementations.base;

import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Base service for modification operations
 *
 * @param <TEntity> Abstract entity
 * @param <TDtoEntity> Abstract DTO entity
 * @param <TCreationDtoEntity> Abstract creation DTO entity
 * @param <TUpdateDtoEntity> Abstract update DTO entity
 * @param <TId> Type of entity's ID
 * @author Nyx
 */
public abstract class BaseService<TEntity, TDtoEntity, TCreationDtoEntity, TUpdateDtoEntity, TId> extends BaseReadonlyService<TEntity, TDtoEntity, TId> implements IBaseService<TDtoEntity, TCreationDtoEntity, TUpdateDtoEntity, TId> {

    private final IBaseRepository<TEntity, TId> _baseRepository;

    public BaseService(IBaseRepository<TEntity, TId> baseRepository) {
        super(baseRepository);
        _baseRepository = baseRepository;
    }

    @Override
    @Async
    public CompletableFuture<TDtoEntity> insertAsync(TCreationDtoEntity creationDtoEntity) {
        CompletableFuture.runAsync(() -> validateCreationBusiness(creationDtoEntity));

        return CompletableFuture.supplyAsync(() ->
                mapEntityToDto(_baseRepository.save(mapCreationDtoToEntity(creationDtoEntity)))
        );
    }

    @Override
    @Async
    public CompletableFuture<TDtoEntity> updateAsync(TUpdateDtoEntity updateDtoEntity) {
        CompletableFuture.runAsync(() -> validateUpdateBusiness(updateDtoEntity));

        return CompletableFuture.supplyAsync(() ->
                mapEntityToDto(_baseRepository.save(mapUpdateDtoToEntity(updateDtoEntity)))
        );
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteByIdAsync(TId id) {
        return CompletableFuture.runAsync(() ->
                _baseRepository.deleteById(id));
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteManyByIdsAsync(List<TId> ids) {
        return CompletableFuture.runAsync(() ->
                _baseRepository.deleteAllById(ids));
    }

    /**
     * Convert from creation DTO entity to entity
     *
     * @param creationDtoEntity Creation DTO entity
     * @return An entity
     * @author Nyx
     */
    public abstract TEntity mapCreationDtoToEntity(TCreationDtoEntity creationDtoEntity);

    /**
     * Convert from update DTO entity to entity
     *
     * @param updateDtoEntity Update DTO entity
     * @return An entity
     * @author Nyx
     */
    public abstract TEntity mapUpdateDtoToEntity(TUpdateDtoEntity updateDtoEntity);

    /**
     * Validate creation business on specified entity
     *
     * @param creationDtoEntity Creation DTO Entity
     * @return A CompletableFuture with no data
     * @author Nyx
     */
    @Async
    public CompletableFuture<Void> validateCreationBusiness(TCreationDtoEntity creationDtoEntity) {
        return CompletableFuture.runAsync(() -> { });
    }

    /**
     * Validate update business on specified entity
     *
     * @param updateDtoEntity Update DTO entity
     * @return A CompletableFuture with no data
     * @author Nyx
     */
    @Async
    public CompletableFuture<Void> validateUpdateBusiness(TUpdateDtoEntity updateDtoEntity) {
        return CompletableFuture.runAsync(() -> { });
    }
}

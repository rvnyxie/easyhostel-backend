package com.easyhostel.backend.application.service.implementations.base;

import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Base service for read-only operations
 *
 * @param <TEntity> Abstract entity
 * @param <TDtoEntity> Abstract DTO entity
 * @param <TId> Type of entity's ID
 * @author Nyx
 */
public abstract class BaseReadonlyService<TEntity, TDtoEntity, TId> implements IBaseReadonlyService<TDtoEntity, TId> {

    private final IBaseReadonlyRepository<TEntity, TId> _baseReadonlyRepository;
    private final TaskExecutor _taskExecutor;

    public BaseReadonlyService(IBaseReadonlyRepository<TEntity, TId> baseReadonlyRepository,
                               TaskExecutor taskExecutor) {
        _baseReadonlyRepository = baseReadonlyRepository;
        _taskExecutor = taskExecutor;
    }

    @Override
    @Async
    public CompletableFuture<List<TDtoEntity>> getAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            var entities = _baseReadonlyRepository.findAll();

            return entities.stream()
                    .map(this::mapEntityToDto)
                    .toList();
        });
    }

    @Override
    @Async
    public CompletableFuture<TDtoEntity> getByIdAsync(TId id) {
        return validateGettingBusinessAsync(id)
            .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                var entity = _baseReadonlyRepository.findById(id);

                return entity.map(this::mapEntityToDto).orElseThrow();
            }));
    }

    @Override
    @Async
    public CompletableFuture<List<TDtoEntity>> getManyWithPaginationAsync(int offset, int limit) {
        return validateGettingManyBusinessAsync()
            .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                var withPagination = PageRequest.of(offset, limit);

                return _baseReadonlyRepository.findAll(withPagination)
                        .stream()
                        .map(this::mapEntityToDto)
                        .toList();
            }));
    }

    @Override
    @Async
    public CompletableFuture<List<TDtoEntity>> getManyWithPaginationAndSortingAsync(int offset, int limit, String sortField) {
        return validateGettingManyBusinessAsync()
            .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                var withPaginationAndSorting = PageRequest.of(offset, limit, Sort.by(sortField));

                return _baseReadonlyRepository.findAll(withPaginationAndSorting)
                        .stream()
                        .map(this::mapEntityToDto)
                        .toList();
            }));
    }

    @Override
    @Async
    public CompletableFuture<Long> getCountAsync() {
        return CompletableFuture.supplyAsync(_baseReadonlyRepository::count);
    }

    /**
     * Convert from entity to DTO entity
     *
     * @param entity An entity object
     * @return An DTO entity
     * @author Nyx
     */
    public abstract TDtoEntity mapEntityToDto(TEntity entity);

    /**
     * Asynchronously validate getting business on specified entity
     *
     * @param id Entity's ID
     * @return A CompletableFuture with no data
     * @author Nyx
     */
    public CompletableFuture<Void> validateGettingBusinessAsync(TId id) {
        return CompletableFuture.runAsync(() -> { }, _taskExecutor);
    }

    /**
     * Asynchronously validate getting business on many entities
     *
     * @return A CompletableFuture with no data
     * @author Nyx
     */
    public CompletableFuture<Void> validateGettingManyBusinessAsync() {
        return CompletableFuture.runAsync(() -> { }, _taskExecutor);
    }

}

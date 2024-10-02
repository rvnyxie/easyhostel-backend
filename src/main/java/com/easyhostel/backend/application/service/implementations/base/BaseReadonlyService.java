package com.easyhostel.backend.application.service.implementations.base;

import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
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

    public BaseReadonlyService(IBaseReadonlyRepository<TEntity, TId> baseReadonlyRepository) {
        _baseReadonlyRepository = baseReadonlyRepository;
    }

    @Override
    @Async
    public CompletableFuture<List<TDtoEntity>> getAllAsync() {
        return CompletableFuture.supplyAsync(() ->
                _baseReadonlyRepository.findAll() // Synchronously fetch data
                        .stream()
                        .map(this::mapEntityToDto)
                        .toList()
        );
    }

    @Override
    @Async
    public CompletableFuture<Optional<TDtoEntity>> getByIdAsync(TId id) {
        return CompletableFuture.supplyAsync(() ->
            _baseReadonlyRepository.findById(id).map(this::mapEntityToDto)
        );
    }

    @Override
    @Async
    public CompletableFuture<List<TDtoEntity>> getManyWithPaginationAsync(int offset, int limit) {
        var withPagination = PageRequest.of(offset, limit);

        return CompletableFuture.supplyAsync(() ->
                _baseReadonlyRepository.findAll(withPagination)
                        .stream()
                        .map(this::mapEntityToDto)
                        .toList()
                );
    }

    @Override
    @Async
    public CompletableFuture<List<TDtoEntity>> getManyWithPaginationAndSortingAsync(int offset, int limit, String sortField) {
        var withPaginationAndSorting = PageRequest.of(offset, limit, Sort.by(sortField));

        return CompletableFuture.supplyAsync(() ->
                _baseReadonlyRepository.findAll(withPaginationAndSorting)
                        .stream()
                        .map(this::mapEntityToDto)
                        .toList()
        );
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

}

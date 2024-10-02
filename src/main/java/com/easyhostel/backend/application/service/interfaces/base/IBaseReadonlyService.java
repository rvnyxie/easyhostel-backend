package com.easyhostel.backend.application.service.interfaces.base;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Interface base for readonly service
 *
 * @param <TDtoEntity> Abstract DTO entity
 * @author Nyx
 */
public interface IBaseReadonlyService<TDtoEntity, TId> {


    //region Read-only GET methods

    /**
     * Asynchronous method to retrieve all records
     *
     * @return A CompletableFuture containing a List of TDtoEntity objects
     * @author Nyx
     */
    CompletableFuture<List<TDtoEntity>> getAllAsync();

    /**
     * Asynchronous method to retrieve a record by ID
     *
     * @param id ID of the record
     * @return A CompletableFuture containing a DTO entity with specified ID
     * @author Nyx
     */
    CompletableFuture<Optional<TDtoEntity>> getByIdAsync(TId id);

    /**
     * Asynchronous method to retrieve records with pagination
     *
     * @param offset Page number
     * @param limit Number of record per page
     * @return A CompletableFuture containing a List of DTO entities
     * @author Nyx
     */
    CompletableFuture<List<TDtoEntity>> getManyWithPaginationAsync(int offset, int limit);

    /**
     * Asynchronous method to retrieve records with pagination and field sorting
     *
     * @param offset Page number
     * @param limit Number of record per page
     * @param sortField Field name to be sorted
     * @return A CompletableFuture containing a List of DTO entities
     * @author Nyx
     */
    // TODO: we may need to have many sorting requires, it can be a list of Sort
    CompletableFuture<List<TDtoEntity>> getManyWithPaginationAndSortingAsync(int offset, int limit, String sortField);

    /**
     * Asynchronous method to get total records quantity
     *
     * @return Number of total records quantity
     * @author Nyx
     */
    CompletableFuture<Long> getCountAsync();

    //endregion

}

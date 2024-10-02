package com.easyhostel.backend.application.service.interfaces.base;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface base for modification services
 *
 * @param <TDtoEntity> Abstract DTO entity which will be sent to client
 * @param <TCreationDtoEntity> Abstract creation DTO
 * @param <TUpdateDtoEntity> Abstract update DTO
 * @author Nyx
 */
public interface IBaseService<TDtoEntity, TCreationDtoEntity, TUpdateDtoEntity, TId> extends IBaseReadonlyService<TDtoEntity, TId> {

    //region Insert, Update, Delete

    /**
     * Asynchronous method to insert a record to Database
     *
     * @param creationDtoEntity Abstract creation DTO
     * @return A DTO entity object
     * @author Nyx
     */
    CompletableFuture<TDtoEntity> insertAsync(TCreationDtoEntity creationDtoEntity);

    /**
     * Asynchronous method to insert a record to Database
     *
     * @param updateDtoEntity Abstract update DTO
     * @return A DTO entity object
     * @author Nyx
     */
    CompletableFuture<TDtoEntity> updateAsync(TUpdateDtoEntity updateDtoEntity);

    /**
     * Asynchronous method to delete a record from Database
     *
     * @param id ID of the record which will be deleted
     * @author Nyx
     */
    CompletableFuture<Void> deleteByIdAsync(TId id);

    /**
     * Asynchronous method to delete many records from Database
     *
     * @param ids IDs of the records which will be deleted
     * @author Nyx
     */
    CompletableFuture<Void> deleteManyByIdsAsync(List<TId> ids);

    //endregion

}

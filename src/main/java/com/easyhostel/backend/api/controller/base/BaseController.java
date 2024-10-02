package com.easyhostel.backend.api.controller.base;

import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.response.FormattedResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Base controller for modification operations
 *
 * @param <TDtoEntity> Abstract DTO entity
 * @param <TCreationDtoEntity> Abstract creation DTO entity
 * @param <TUpdateDtoEntity> Abstract update DTO entity
 * @param <TId> Type of entity's ID
 * @author Nyx
 */
@RestController
@Validated
@RequestMapping("${api.base-path}/modification")
public class BaseController<TDtoEntity, TCreationDtoEntity, TUpdateDtoEntity, TId> extends BaseReadOnlyController<TDtoEntity, TId>{

    protected final IBaseService<TDtoEntity, TCreationDtoEntity, TUpdateDtoEntity, TId> baseService;

    public BaseController(IBaseService<TDtoEntity, TCreationDtoEntity, TUpdateDtoEntity, TId> baseService) {
        super(baseService);
        this.baseService = baseService;
    }

    /**
     * Insert a new record
     *
     * @param creationDto Creation DTO entity
     * @return Full formatted response
     * @author Nyx
     */
    @PostMapping
    public ResponseEntity<FormattedResponse<TDtoEntity>> insertAsync(@Valid @RequestBody TCreationDtoEntity creationDto) {
        var futureDtoEntity = baseService.insertAsync(creationDto);

        var dtoEntity = futureDtoEntity.join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale(""),
                dtoEntity
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Update a record
     *
     * @param updateDto Update DTO entity
     * @return Full formatted response
     * @author Nyx
     */
    @PutMapping
    public ResponseEntity<FormattedResponse<TDtoEntity>> updateAsync(@Valid @RequestBody TUpdateDtoEntity updateDto) {
        var futureDtoEntity = baseService.updateAsync(updateDto);

        var dtoEntity = futureDtoEntity.join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale(""),
                dtoEntity
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a record with specified ID
     *
     * @param id ID of record will be deleted
     * @return Full formatted response
     * @author Nyx
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<FormattedResponse<Void>> deleteByIdAsync(@PathVariable TId id) {
        baseService.deleteByIdAsync(id).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale(""),
                null
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Delete many records by a List of ID
     *
     * @param ids List of records ID will be deleted
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping
    public ResponseEntity<FormattedResponse<Void>> deleteManyByIdsAsync(@Valid @RequestBody List<TId> ids) {
        baseService.deleteManyByIdsAsync(ids).join();

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale(""),
                null
        );
        return ResponseEntity.ok(response);
    }

}

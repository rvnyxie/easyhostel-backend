package com.easyhostel.backend.api.controller.base;

import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.response.FormattedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Base controller for readonly operations
 *
 * @param <TDtoEntity> Abstract DTO entity
 * @param <TId> Type of entity's ID
 * @author Nyx
 */
@RestController
@RequestMapping("/api/v0.1/readonly")
public class BaseReadOnlyController<TDtoEntity, TId> {

    protected final IBaseReadonlyService<TDtoEntity, TId> baseReadOnlyService;

    public BaseReadOnlyController(IBaseReadonlyService<TDtoEntity, TId> baseReadOnlyService) {
        this.baseReadOnlyService = baseReadOnlyService;
    }

    /**
     * Get all records
     *
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping
    public ResponseEntity<FormattedResponse<List<TDtoEntity>>> getAllAsync() {
        var futureDtoEntity = baseReadOnlyService.getAllAsync();

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
     * Get a record with specified ID
     *
     * @param id ID of the record
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{id}")
    public ResponseEntity<FormattedResponse<Optional<TDtoEntity>>> getByIdAsync(@PathVariable String id) {
        var futureDtoEntity = baseReadOnlyService.getByIdAsync((TId) UUID.fromString(id));

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
     * Get many records with pagination
     *
     * @param offset Page number (start with 0)
     * @param limit Number of records each page
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{offset}&{limit}")
    public ResponseEntity<FormattedResponse<List<TDtoEntity>>> getManyWithPagination(@PathVariable int offset,@PathVariable int limit) {
        var futureDtoEntity = baseReadOnlyService.getManyWithPaginationAsync(offset, limit);

        var dtoEntities = futureDtoEntity.join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale(""),
                dtoEntities
        );
        return ResponseEntity.ok(response);
    }

}
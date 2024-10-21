package com.easyhostel.backend.api.controller.managerhouse;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseCreationDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseUpdateDto;
import com.easyhostel.backend.application.service.interfaces.managerhouse.IManagerHouseService;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for ManagerHouse
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/managers-houses")
@Tag(name = "ManagerHouse Controller")
public class ManagerHouseController extends BaseController<ManagerHouseDto, ManagerHouseCreationDto, ManagerHouseUpdateDto, ManagerHouseId> {

    private final IManagerHouseService _managerHouseService;

    public ManagerHouseController(IManagerHouseService managerHouseService) {
        super(managerHouseService);
        _managerHouseService = managerHouseService;
    }

    /**
     * Asynchronously get ManagerHouse by IDs
     *
     * @param managerId Manager's ID
     * @param houseId House's ID
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{managerId}/{houseId}")
    public ResponseEntity<FormattedResponse<ManagerHouseDto>> getRolePermissionByIdsAsync(@PathVariable @Valid String managerId,
                                                                                            @PathVariable @Valid String houseId) {
        var managerHouseId = ManagerHouseId.builder().managerId(managerId).houseId(houseId).build();

        var dtoEntity = _managerHouseService.getByIdAsync(managerHouseId).join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("get.managerHouse.success"),
                dtoEntity
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Asynchronously delete ManagerHouse by IDs
     *
     * @param managerId Manager's ID
     * @param houseId House's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{managerId}/{houseId}")
    public ResponseEntity<FormattedResponse<Void>> deleteRolePermissionByIdsAsync(@PathVariable @Valid String managerId,
                                                                                  @PathVariable @Valid String houseId) {
        var managerHouseId = ManagerHouseId.builder().managerId(managerId).houseId(houseId).build();

        _managerHouseService.deleteByIdAsync(managerHouseId);

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("delete.managerHouse.success"),
                null
        );

        return ResponseEntity.ok(response);
    }

}

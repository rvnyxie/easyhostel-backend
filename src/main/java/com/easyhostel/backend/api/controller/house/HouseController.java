package com.easyhostel.backend.api.controller.house;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.application.service.interfaces.house.IHouseService;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Controller for House
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/houses")
@Tag(name = "House Controller")
public class HouseController extends BaseController<HouseDto, HouseCreationDto, HouseUpdateDto, String> {

    private final IHouseService _houseService;

    public HouseController(@Qualifier("houseService") IHouseService houseService) {
        super(houseService);
        _houseService = houseService;
    }

    /**
     * Asynchronously delete Room from House by ID
     *
     * @param houseId House's ID
     * @param roomId Room's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{houseId}/rooms/{roomId}")
    public CompletableFuture<ResponseEntity<FormattedResponse<Void>>> deleteRoomFromHousedAsync(
            @PathVariable String houseId,
            @PathVariable String roomId) {
        return _houseService.deleteRoomFromHouseByIdAsync(houseId, roomId)
                .thenApply(result -> {
                    var response = new FormattedResponse<>(
                            true,
                            HttpStatus.OK.value(),
                            null,
                            Translator.toLocale("delete.roomFromHouse.success"),
                            result
                    );
                    return ResponseEntity.ok(response);
                });
    }

}

package com.easyhostel.backend.api.controller.room;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.service.interfaces.room.IRoomService;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * Controller for Room
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/rooms")
@Tag(name = "Room Controller")
public class RoomController extends BaseController<RoomDto, RoomCreationDto, RoomUpdateDto, String> {

    private final IRoomService _roomService;

    public RoomController(@Qualifier("roomService") IRoomService roomService) {
        super(roomService);
        _roomService = roomService;
    }

    /**
     * Asynchronously delete Contract from Room by ID
     *
     * @param roomId Room's ID
     * @param contractId Contract's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{roomId}/contracts/{contractId}")
    public CompletableFuture<ResponseEntity<FormattedResponse<Void>>> deleteContractFromRoomAsync(
            @PathVariable String roomId,
            @PathVariable String contractId) {
        return _roomService.deleteContractFromRoomByIdAsync(roomId, contractId)
                .thenApply(result -> {
                    var response = new FormattedResponse<>(
                            true,
                            HttpStatus.OK.value(),
                            null,
                            Translator.toLocale("delete.contractFromRoom.success"),
                            result
                    );
                    return ResponseEntity.ok(response);
                });
    }

}

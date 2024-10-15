package com.easyhostel.backend.api.controller.rolepermission;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionCreationDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionUpdateDto;
import com.easyhostel.backend.application.service.interfaces.rolepermission.IRolePermissionService;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.util.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for RolePermission
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/roles-permissions")
@Tag(name = "RolePermission Controller")
public class RolePermissionController extends BaseController<RolePermissionDto, RolePermissionCreationDto, RolePermissionUpdateDto, RolePermissionId> {

    private final IRolePermissionService _rolePermissionService;

    public RolePermissionController(IRolePermissionService rolePermissionService) {
        super(rolePermissionService);
        _rolePermissionService = rolePermissionService;
    }

    /**
     * Asynchronously get RolePermission by IDs
     *
     * @param roleId Role's ID
     * @param permissionId Permission's ID
     * @return Full formatted response
     * @author Nyx
     */
    @GetMapping("/{roleId}/{permissionId}")
    public ResponseEntity<FormattedResponse<RolePermissionDto>> getRolePermissionByIdsAsync(@PathVariable @Valid Integer roleId,
                                                                                                     @PathVariable @Valid Integer permissionId) {
        var rolePermissionId = RolePermissionId.builder().roleId(roleId).permissionId(permissionId).build();

        var dtoEntity = _rolePermissionService.getByIdAsync(rolePermissionId).join();

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("get.rolePermission.success"),
                dtoEntity
        );
        return ResponseEntity.ok(response);
    }

    /**
     * Asynchronously delete RolePermission by IDs
     *
     * @param roleId Role's ID
     * @param permissionId Permission's ID
     * @return Full formatted response
     * @author Nyx
     */
    @DeleteMapping("/{roleId}/{permissionId}")
    public ResponseEntity<FormattedResponse<Void>> deleteRolePermissionByIdsAsync(@PathVariable @Valid Integer roleId,
                                                                                  @PathVariable @Valid Integer permissionId) {
        var rolePermissionId = RolePermissionId.builder().roleId(roleId).permissionId(permissionId).build();

        _rolePermissionService.deleteByIdAsync(rolePermissionId);

        var response = new FormattedResponse<Void>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("delete.rolePermission.success"),
                null
        );

        return ResponseEntity.ok(response);
    }

}

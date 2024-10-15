package com.easyhostel.backend.application.service.interfaces.rolepermission;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionCreationDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for RolePermission service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRolePermissionService extends IBaseService<RolePermissionDto, RolePermissionCreationDto, RolePermissionUpdateDto, RolePermissionId> {

    /**
     * Asynchronously validate RolePermission for getting by ID
     *
     * @param rolePermissionId RolePermissionId object
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> validateGettingBusinessAsync(RolePermissionId rolePermissionId);

    /**
     * Asynchronously validate RolePermission for deleting by ID
     *
     * @param rolePermissionId RolePermissionId object
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> validateDeletionBusinessAsync(RolePermissionId rolePermissionId);

}

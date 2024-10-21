package com.easyhostel.backend.infrastructure.util.custom.security.expression;

import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.RoleType;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractReadonlyRepository;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * Custom method security root
 *
 * @author Nyx
 */
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final IContractReadonlyRepository _contractReadonlyRepository;

    private final Authentication _authentication;
    private Object _filterObject;
    private Object _returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication,
                                              IContractReadonlyRepository contractReadonlyRepository) {
        super(authentication);
        _authentication = authentication;

        _contractReadonlyRepository = contractReadonlyRepository;
    }

    //region Custom expressions

    /**
     * Security expression that checks the getting permission
     *
     * @param targetObjectId Target object's ID
     * @return true if having permission, false if not
     * @author Nyx
     */
    public boolean hasGettingPermission(Object targetObjectId) {
        // Check if user's Role is SYSADMIN, can do all operations
        if (isUserRoleSysadmin()) {
            return true;
        }
        
        // Check type of return object
        if (_returnObject instanceof ResponseEntity<?>) {
            // Get data inside response sent to client
            var responseEntity = ((ResponseEntity<?>) _returnObject).getBody();
            var formattedResponse = (FormattedResponse<?>) responseEntity;

            var data = formattedResponse.getData();

            if (data instanceof HouseDto) {
                return checkIfHouseAssignedToManager(targetObjectId);
            } else if (data instanceof RoomDto) {
                var houseThatRoomBelongedTo = ((RoomDto) data).getHouse();

                return checkIfHouseAssignedToManager(houseThatRoomBelongedTo.getHouseId());
            } else if (data instanceof ContractDto) {
                var houseThatContractBelongedTo = ((ContractDto) data).getRoom().getHouse();

                return checkIfHouseAssignedToManager(houseThatContractBelongedTo.getHouseId());
            } else if (data instanceof ContractInteriorDto) {
                var contract = _contractReadonlyRepository.findById(((ContractInteriorDto) data).getContractId());

                if (contract.isPresent()) {
                    var houseThatContractInteriorBelongedTo = contract.get().getRoom().getHouse();

                    return checkIfHouseAssignedToManager(houseThatContractInteriorBelongedTo.getHouseId());
                }
            } else if (data instanceof ContractRoomAmenityDto) {
                var contract = _contractReadonlyRepository.findById(((ContractRoomAmenityDto) data).getContractId());

                if (contract.isPresent()) {
                    var houseThatContractRoomAmenityBelongedTo = contract.get().getRoom().getHouse();

                    return checkIfHouseAssignedToManager(houseThatContractRoomAmenityBelongedTo.getHouseId());
                }
            }  else if (data instanceof ContractVehicleDto) {
                var contract = _contractReadonlyRepository.findById(((ContractVehicleDto) data).getContractId());

                if (contract.isPresent()) {
                    var houseThatContractVehicleBelongedTo = contract.get().getRoom().getHouse();

                    return checkIfHouseAssignedToManager(houseThatContractVehicleBelongedTo.getHouseId());
                }
            }
//                if (interior.isPresent()) {
//                    // Collect houses that the interior is related to
//                    var housesThatInteriorBelongedTo = interior.get().getContractInteriors()
//                            .stream()
//                            .map(contractInterior -> {
//                                // Find the contract related to the contractInterior
//                                var contract = _contractReadonlyRepository.findById(contractInterior.getContractInteriorId().getContractId());
//
//                                // If the contract is present, get the house related to the room of the contract
//                                return contract.map(c -> c.getRoom().getHouse()).orElse(null);
//                            })
//                            .filter(Objects::nonNull)  // Filter out null values
//                            .collect(Collectors.toSet());  // Collect unique houses into a set
//                }
        }

        return false;
    }

    /**
     * Security expression that checks the getting many permissions
     *
     * @return true if having permission, false if not
     * @author Nyx
     */
    public boolean hasGettingManyPermission() {
        // Check if user's Role is SYSADMIN, can do all operations
        if (isUserRoleSysadmin()) {
            return true;
        }
        
        // Check return object type
        if (_returnObject instanceof ResponseEntity<?>) {
            // Get data inside response sent to client
            var responseEntity = ((ResponseEntity<?>) _returnObject).getBody();
            assert responseEntity != null;
            var data = ((FormattedResponse<?>) responseEntity).getData();
            
            if (data instanceof HouseDto) {

            } else if (data instanceof RoomDto) {
                
            } else if (data instanceof ContractDto) {
                
            } else if (data instanceof ContractInteriorDto) {
                
            } else if (data instanceof ContractRoomAmenityDto) {
                
            } else if (data instanceof ContractVehicleDto) {
                
            }
        }

        return false;
    }

    /**
     * Security expression that checks the creating permission
     *
     * @return true if having permission, false if not
     * @author Nyx
     */
    public boolean hasCreationPermission() {
        // Check if user's Role is SYSADMIN, can do all operations
        if (isUserRoleSysadmin()) {
            return true;
        }

        return false;
    }

    /**
     * Security expression that checks the updating permission
     *
     * @return true if having permission, false if not
     * @author Nyx
     */
    public boolean hasUpdatePermission() {
        // Check if user's Role is SYSADMIN, can do all operations
        if (isUserRoleSysadmin()) {
            return true;
        }

        return false;
    }

    /**
     * Security expression that checks the deletion permission
     *
     * @return true if having permission, false if not
     * @author Nyx
     */
    public boolean hasDeletionPermission() {
        // Check if user's Role is SYSADMIN, can do all operations
        if (isUserRoleSysadmin()) {
            return true;
        }

        return false;
    }

    /**
     * Check if current authenticated user's Role is SYSADMIN
     *
     * @return true if SYSADMIN, false if not
     * @author Nyx
     */
    private boolean isUserRoleSysadmin() {
        return _authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + RoleType.SYSADMIN.name()));
    }

    /**
     * Check if House is assigned to Manager
     *
     * @param houseId House's ID
     * @return true if assigned, false if not
     * @author Nyx
     */
    private boolean checkIfHouseAssignedToManager(Object houseId) {
        var managerDetail = (Manager) _authentication.getPrincipal();

        var managerHouses = managerDetail.getManagerHouses();

        var isHouseAssignedToManager = false;

        for (var managerHouse : managerHouses) {
            if (managerHouse.getHouse().getHouseId().equals(houseId)) {
                isHouseAssignedToManager = true;
                break;
            }
        }

        return isHouseAssignedToManager;
    }

    //endregion`

    //region Overriding methods

    @Override
    public void setFilterObject(Object filterObject) {
        _filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return _filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        _returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return _returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    //endregion

}

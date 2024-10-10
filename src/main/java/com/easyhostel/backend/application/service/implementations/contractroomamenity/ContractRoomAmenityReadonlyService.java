package com.easyhostel.backend.application.service.implementations.contractroomamenity;

import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractRoomAmenityMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contractroomamenity.IContractRoomAmenityReadonlyService;
import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.repository.interfaces.contractroomamenity.IContractRoomAmenityRepository;
import org.springframework.stereotype.Service;

/**
 * ContractRoomAmenity readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractRoomAmenityReadonlyService extends BaseReadonlyService<ContractRoomAmenity, ContractRoomAmenityDto, ContractRoomAmenityId> implements IContractRoomAmenityReadonlyService {

    private final IContractRoomAmenityMapper _contractRoomAmenityMapper;

    public ContractRoomAmenityReadonlyService(IContractRoomAmenityRepository contractRoomAmenityRepository, IContractRoomAmenityMapper contractRoomAmenityMapper) {
        super(contractRoomAmenityRepository);
        _contractRoomAmenityMapper = contractRoomAmenityMapper;
    }

    @Override
    public ContractRoomAmenityDto mapEntityToDto(ContractRoomAmenity contractRoomAmenity) {
        return _contractRoomAmenityMapper.MAPPER.mapContractRoomAmenityToContractRoomAmenityDto(contractRoomAmenity);
    }

}

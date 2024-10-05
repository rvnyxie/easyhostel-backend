package com.easyhostel.backend.application.mapping.interfaces;


import com.easyhostel.backend.application.dto.rentpayment.RentPaymentCreationDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentDto;
import com.easyhostel.backend.application.dto.rentpayment.RentPaymentUpdateDto;
import com.easyhostel.backend.domain.entity.RentPayment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for RentPayment entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRentPaymentMapper {

    IRentPaymentMapper MAPPER = Mappers.getMapper(IRentPaymentMapper.class);

    RentPaymentDto mapRentPaymentToRentPaymentDto(RentPayment rentPayment);

    RentPayment mapRentPaymentCreationDtoToRentPayment(RentPaymentCreationDto rentPaymentCreationDto);

    RentPayment mapRentPaymentUpdateDtoToRentPayment(RentPaymentUpdateDto rentPaymentUpdateDto);

}

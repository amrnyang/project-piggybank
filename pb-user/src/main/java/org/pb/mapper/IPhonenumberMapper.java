package org.pb.mapper;

import org.mapstruct.Mapper;
import org.pb.dto.PhoneNumberDTO;
import org.pb.model.PhoneNumber;

@Mapper(componentModel = "spring")
public interface IPhonenumberMapper extends AbstractMapper<PhoneNumber, PhoneNumberDTO> {
    @Override
    PhoneNumber toEntity(PhoneNumberDTO phoneNumberDTO);

    @Override
    PhoneNumberDTO toDTO(PhoneNumber phoneNumber);
}

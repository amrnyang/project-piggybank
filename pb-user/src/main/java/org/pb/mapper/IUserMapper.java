package org.pb.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.pb.dto.UserDTO;
import org.pb.model.User;

@Mapper(componentModel = "spring", uses = {IPhonenumberMapper.class})
public interface IUserMapper extends AbstractMapper<User, UserDTO> {
    @Override
    @InheritInverseConfiguration
    User toEntity(UserDTO userDTO);

    @Override
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    UserDTO toDTO(User user);
}

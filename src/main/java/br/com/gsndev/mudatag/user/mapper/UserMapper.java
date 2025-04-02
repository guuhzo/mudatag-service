package br.com.gsndev.mudatag.user.mapper;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authId", source = "authId")
    UserModel toModel(BaseUserDTO dto);

    BaseUserDTO toDTO(UserModel model);
}

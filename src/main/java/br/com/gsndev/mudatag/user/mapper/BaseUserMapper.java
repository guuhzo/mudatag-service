package br.com.gsndev.mudatag.user.mapper;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.model.UserModel;

public abstract class BaseUserMapper {

    public static BaseUserDTO map(UserModel userData) {
        return new BaseUserDTO(
                userData.getId(),
                userData.getFullName(),
                userData.isBlocked(),
                userData.getAuthId()
        );
    }

    public static UserModel map(BaseUserDTO userData) {
        return new UserModel(
                userData.id(),
                userData.fullName(),
                null,
                userData.blocked(),
                userData.authId()
        );
    }

}

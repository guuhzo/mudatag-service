package br.com.gsndev.mudatag.user.service;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.mapper.BaseUserMapper;
import br.com.gsndev.mudatag.user.model.UserModel;
import br.com.gsndev.mudatag.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValidUser(String authId) {
        Optional<UserModel> user = this.userRepository.findUserByAuthId(authId);
        BaseUserDTO baseUserDTO = user.map(BaseUserMapper::map).orElse(null);

        return !(baseUserDTO == null || baseUserDTO.blocked());
    }
}

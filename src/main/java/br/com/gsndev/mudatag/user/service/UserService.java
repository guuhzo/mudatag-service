package br.com.gsndev.mudatag.user.service;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.mapper.UserMapper;
import br.com.gsndev.mudatag.user.model.UserModel;
import br.com.gsndev.mudatag.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<BaseUserDTO> findByAuthId(String authId) {
        Optional<UserModel> user = this.userRepository.findUserByAuthId(authId);

        return user.map(this.userMapper::toDTO);
    }

        return !(baseUserDTO == null || baseUserDTO.blocked());
    }
}

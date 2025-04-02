package br.com.gsndev.mudatag.user.controller;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.dto.CreateUserDTO;
import br.com.gsndev.mudatag.user.mapper.UserMapper;
import br.com.gsndev.mudatag.user.service.UserService;
import br.com.gsndev.mudatag.util.AuthUser;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("")
    public BaseUserDTO create(@RequestBody CreateUserDTO bodyData) {
        String currAuthId = AuthUser.getAuthId();

        BaseUserDTO baseUserDTO = new BaseUserDTO();
        baseUserDTO.setFullName(bodyData.fullName());
        baseUserDTO.setBlocked(false);
        baseUserDTO.setAuthId(currAuthId);

        try {
            return this.userService.create(this.userMapper.toModel(baseUserDTO));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
    }

    @GetMapping("me")
    public BaseUserDTO findSelf() {
        return AuthUser.getAuthUser();
    }
}

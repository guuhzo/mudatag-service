package br.com.gsndev.mudatag.group.controller;

import br.com.gsndev.mudatag.util.PaginationResponse;
import br.com.gsndev.mudatag.group.dto.BaseGroupDTO;
import br.com.gsndev.mudatag.group.dto.CreateGroupDTO;
import br.com.gsndev.mudatag.group.dto.ShortGroupDTO;
import br.com.gsndev.mudatag.group.dto.UpdateGroupDTO;
import br.com.gsndev.mudatag.group.service.GroupService;
import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.util.AuthUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private static final String NOT_FOUND_MESSAGE = "Group not found";
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public ResponseEntity<PaginationResponse<ShortGroupDTO>> findAllByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
        BaseUserDTO authUser = AuthUser.getAuthUser();
        Pageable pageable = PageRequest.of(page, size);

        Page<ShortGroupDTO> pageResult = this.groupService.findAllByUser(authUser.getId(), pageable);

        return ResponseEntity.ok(PaginationResponse.map(pageResult));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseGroupDTO> findById(@PathVariable("id") String id) {
        try {
            UUID parsedId = UUID.fromString(id);
            BaseUserDTO authUser = AuthUser.getAuthUser();

            BaseGroupDTO group = this.groupService
                    .findById(authUser.getId(), parsedId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE));

            return ResponseEntity.ok(group);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseGroupDTO> create(@RequestBody CreateGroupDTO body) {
        BaseUserDTO authUser = AuthUser.getAuthUser();
        BaseGroupDTO baseGroupDTO = new BaseGroupDTO();

        List<BaseUserDTO> members = new ArrayList<>();
        members.add(authUser);

        baseGroupDTO.setName(body.getName());
        baseGroupDTO.setDescription(body.getDescription());
        baseGroupDTO.setOwner(authUser);
        baseGroupDTO.setMembers(members);

        BaseGroupDTO group = this.groupService.save(baseGroupDTO);

        return ResponseEntity.ok(group);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseGroupDTO> update(@PathVariable("id") String id, @RequestBody UpdateGroupDTO body) {
        try {
            UUID parsedId = UUID.fromString(id);
            BaseUserDTO authUser = AuthUser.getAuthUser();

            BaseGroupDTO baseGroupDTO = this.groupService
                    .findById(authUser.getId(), parsedId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE));

            body.getName().ifPresent(baseGroupDTO::setName);
            body.getDescription().ifPresent(baseGroupDTO::setDescription);

            return ResponseEntity.ok(this.groupService.save(baseGroupDTO));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            UUID parsedId = UUID.fromString(id);
            BaseUserDTO authUser = AuthUser.getAuthUser();

            this.groupService
                    .findById(authUser.getId(), parsedId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE));

            this.groupService.delete(parsedId);

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_MESSAGE);
        }
    }
}










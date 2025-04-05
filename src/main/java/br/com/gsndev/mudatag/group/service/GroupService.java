package br.com.gsndev.mudatag.group.service;

import br.com.gsndev.mudatag.group.dto.BaseGroupDTO;
import br.com.gsndev.mudatag.group.dto.GroupWithMembersCountDTO;
import br.com.gsndev.mudatag.group.dto.ShortGroupDTO;
import br.com.gsndev.mudatag.group.dto.WriteGroupDTO;
import br.com.gsndev.mudatag.group.mapper.GroupMapper;
import br.com.gsndev.mudatag.group.model.GroupModel;
import br.com.gsndev.mudatag.group.repository.GroupRepository;
import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.mapper.UserMapper;
import br.com.gsndev.mudatag.user.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserMapper userMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, UserMapper userMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.userMapper = userMapper;
    }

    public Page<ShortGroupDTO> findAllByUser(UUID userId, Pageable pageable) {
        Page<GroupModel> page = this.groupRepository.findAllByUserId(userId, pageable);
        return this.groupMapper.toPageDTO(page);
    }

//    public Optional<BaseGroupDTO> findById(UUID userId, UUID groupId) {
//        return this.groupRepository.findOneById(userId, groupId).map(this.groupMapper::toDTO);
//    }

    public Optional<BaseGroupDTO> findById(UUID userId, UUID groupId) {
        Optional<GroupWithMembersCountDTO> baseData = this.groupRepository.findOneById(userId, groupId);

        if (baseData.isEmpty()) {
            return Optional.empty();
        }

        Pageable pageable = PageRequest.ofSize(3);
        Page<UserModel> membersPage = this.groupRepository.findTopMembers(userId, groupId, pageable);
        List<UserModel> membersModel = membersPage.getContent();
        List<BaseUserDTO> members = membersModel.stream().map(this.userMapper::toDTO).toList();

        return baseData.map(g -> this.groupMapper.toDto(g, members));


    }

    public BaseGroupDTO save(BaseGroupDTO createData) {
        GroupModel groupModel = this.groupMapper.toModel(createData);
        groupModel = this.groupRepository.save(groupModel);

        return this.groupMapper.toDto(groupModel);
    }

    public BaseGroupDTO update(BaseGroupDTO group, WriteGroupDTO updateData) {
        Optional.ofNullable(updateData.getName()).ifPresent(group::setName);
        Optional.ofNullable(updateData.getName()).ifPresent(group::setName);

        return this.save(group);
    }

    public void delete(UUID groupId) {
        this.groupRepository.deleteById(groupId);
    }
}

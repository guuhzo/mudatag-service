package br.com.gsndev.mudatag.group.service;

import br.com.gsndev.mudatag.group.dto.BaseGroupDTO;
import br.com.gsndev.mudatag.group.dto.ShortGroupDTO;
import br.com.gsndev.mudatag.group.mapper.GroupMapper;
import br.com.gsndev.mudatag.group.model.GroupModel;
import br.com.gsndev.mudatag.group.repository.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }


    public Page<ShortGroupDTO> findAllByUser(UUID userId, Pageable pageable) {
        Page<GroupModel> page = this.groupRepository.findAllByUserId(userId, pageable);
        return this.groupMapper.toPageDTO(page);
    }

    public Optional<BaseGroupDTO> findById(UUID userId, UUID groupId) {
        return this.groupRepository.findOneById(userId, groupId).map(this.groupMapper::toDTO);
    }

    public BaseGroupDTO save(BaseGroupDTO createData) {
        GroupModel groupModel = this.groupMapper.toModel(createData);
        groupModel = this.groupRepository.save(groupModel);

        return this.groupMapper.toDTO(groupModel);
    }

    public void delete(UUID groupId) {
        this.groupRepository.deleteById(groupId);
    }
}

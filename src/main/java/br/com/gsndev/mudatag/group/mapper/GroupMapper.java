package br.com.gsndev.mudatag.group.mapper;

import br.com.gsndev.mudatag.group.dto.BaseGroupDTO;
import br.com.gsndev.mudatag.group.dto.GroupWithMembersCountDTO;
import br.com.gsndev.mudatag.group.dto.ShortGroupDTO;
import br.com.gsndev.mudatag.group.model.GroupModel;
import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {UserMapper.class})
public interface GroupMapper {

    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "members", target = "members")
    GroupModel toModel(BaseGroupDTO dto);

    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "members", target = "members")
    BaseGroupDTO toDto(GroupModel model);

    @Mapping(source = "group.id", target = "id")
    @Mapping(source = "group.name", target = "name")
    @Mapping(source = "group.description", target = "description")
    @Mapping(source = "group.owner", target = "owner")
    @Mapping(source = "membersCount", target = "membersCount")
    BaseGroupDTO toCountDto(GroupWithMembersCountDTO model);

    ShortGroupDTO toShortDTO(GroupModel model);

    default Page<ShortGroupDTO> toPageDTO(Page<GroupModel> page) {
        List<ShortGroupDTO> dto = page.getContent().stream()
                .map(this::toShortDTO)
                .toList();

        return new PageImpl<>(dto, page.getPageable(), page.getTotalElements());
    }

    default BaseGroupDTO toDto(GroupWithMembersCountDTO rootData, List<BaseUserDTO> members) {
        BaseGroupDTO dto = this.toCountDto(rootData);
        dto.setMembers(members);

        return dto;
    }
}


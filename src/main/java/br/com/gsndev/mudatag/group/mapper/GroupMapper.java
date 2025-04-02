package br.com.gsndev.mudatag.group.mapper;

import br.com.gsndev.mudatag.group.dto.BaseGroupDTO;
import br.com.gsndev.mudatag.group.dto.ShortGroupDTO;
import br.com.gsndev.mudatag.group.model.GroupModel;
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
    BaseGroupDTO toDTO(GroupModel model);

    ShortGroupDTO toShortDTO(GroupModel model);

    default Page<ShortGroupDTO> toPageDTO(Page<GroupModel> page) {
        List<ShortGroupDTO> dto = page.getContent().stream()
                .map(this::toShortDTO)
                .toList();

        return new PageImpl<>(dto, page.getPageable(), page.getTotalElements());
    }
}


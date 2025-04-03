package br.com.gsndev.mudatag.box.mapper;

import br.com.gsndev.mudatag.box.dto.BaseBoxLabelDTO;
import br.com.gsndev.mudatag.box.dto.ShortBoxLabelDTO;
import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.group.mapper.GroupMapper;
import br.com.gsndev.mudatag.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",  uses = {GroupMapper.class, UserMapper.class})
public interface BoxLabelMapper {

    @Mapping(source = "group", target = "group")
    BoxLabelModel toModel(BaseBoxLabelDTO dto);

    @Mapping(source = "group", target = "group")
    BaseBoxLabelDTO toDto(BoxLabelModel model);

    ShortBoxLabelDTO toShortDto(BoxLabelModel model);

    default Page<ShortBoxLabelDTO> toPageDto(Page<BoxLabelModel> page) {
        List<ShortBoxLabelDTO> dto = page.getContent().stream()
                .map(this::toShortDto)
                .toList();

        return new PageImpl<>(dto, page.getPageable(), page.getTotalElements());
    }
}

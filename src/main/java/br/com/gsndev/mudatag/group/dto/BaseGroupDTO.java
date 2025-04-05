package br.com.gsndev.mudatag.group.dto;

import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseGroupDTO {
    private UUID id;
    private String name;
    private String description;
    private BaseUserDTO owner;
    private List<BaseUserDTO> members;
    private Long membersCount;
}

package br.com.gsndev.mudatag.group.dto;

import br.com.gsndev.mudatag.group.model.GroupModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupWithMembersCountDTO {
    GroupModel group;
    int membersCount;
}

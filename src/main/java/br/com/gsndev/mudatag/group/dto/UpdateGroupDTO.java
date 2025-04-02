package br.com.gsndev.mudatag.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupDTO {
    private Optional<String> name = Optional.empty();
    private Optional<String> description = Optional.empty();
}

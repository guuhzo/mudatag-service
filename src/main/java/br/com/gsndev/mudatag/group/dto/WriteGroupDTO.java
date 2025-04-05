package br.com.gsndev.mudatag.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WriteGroupDTO {
    private String name;
    private String description;
}

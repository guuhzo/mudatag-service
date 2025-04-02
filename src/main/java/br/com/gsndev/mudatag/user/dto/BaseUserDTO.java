package br.com.gsndev.mudatag.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDTO {
    private UUID id;
    private String fullName;

    @JsonIgnore
    private boolean blocked;

    @JsonIgnore
    private String authId;
}
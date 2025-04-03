package br.com.gsndev.mudatag.box.controller;

import br.com.gsndev.mudatag.box.dto.BaseBoxLabelDTO;
import br.com.gsndev.mudatag.box.dto.ShortBoxLabelDTO;
import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.box.service.BoxLabelService;
import br.com.gsndev.mudatag.user.dto.BaseUserDTO;
import br.com.gsndev.mudatag.util.AuthUser;
import br.com.gsndev.mudatag.util.PaginationResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/boxes")
public class BoxLabelController {

    private final BoxLabelService boxLabelService;

    public BoxLabelController(BoxLabelService boxLabelService) {
        this.boxLabelService = boxLabelService;
    }

    @GetMapping("")
    public ResponseEntity<PaginationResponse<ShortBoxLabelDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        BaseUserDTO authUser = AuthUser.getAuthUser();
        Pageable pageable = PageRequest.of(page, size);
        Page<ShortBoxLabelDTO> pageResult = this.boxLabelService.findAll(authUser.getId(), pageable);

        return ResponseEntity.ok(PaginationResponse.map(pageResult));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseBoxLabelDTO> findById(@PathVariable String id) {
        BaseUserDTO authUser = AuthUser.getAuthUser();

        try {
            UUID parsedId = UUID.fromString(id);

            BaseBoxLabelDTO box = this.boxLabelService
                    .findById(authUser.getId(), parsedId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Box label not found"));

            return ResponseEntity.ok(box);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Box label not found");
        }
    }

}

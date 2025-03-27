package br.com.gsndev.mudatag.box.controller;

import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.box.service.BoxLabelService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<BoxLabelModel> findAll() {
        return this.boxLabelService.findAll();
    }

    @GetMapping("/{id}")
    public BoxLabelModel findById(@PathVariable String id) {
        try {
            UUID parsedId = UUID.fromString(id);
            return this.boxLabelService.findById(parsedId);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}

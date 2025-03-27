package br.com.gsndev.mudatag.box.controller;

import br.com.gsndev.mudatag.box.model.BoxItemModel;
import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.box.service.BoxItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boxes")
public class BoxItemController {
    private final BoxItemService boxItemService;

    public BoxItemController(BoxItemService boxItemService) {
        this.boxItemService = boxItemService;
    }

    @GetMapping("/items")
    public List<BoxItemModel> findAll() {
        return this.boxItemService.findAll();
    }
}

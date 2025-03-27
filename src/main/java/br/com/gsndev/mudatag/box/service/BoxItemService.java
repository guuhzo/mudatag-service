package br.com.gsndev.mudatag.box.service;

import br.com.gsndev.mudatag.box.model.BoxItemModel;
import br.com.gsndev.mudatag.box.repository.BoxItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxItemService {
    private final BoxItemRepository boxItemRepository;

    public BoxItemService(BoxItemRepository boxItemRepository) {
        this.boxItemRepository = boxItemRepository;
    }

    public List<BoxItemModel> findAll () {
        return this.boxItemRepository.findAll();
    }
}

package br.com.gsndev.mudatag.box.service;

import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.box.repository.BoxLabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BoxLabelService {

    private final BoxLabelRepository boxLabelRepository;

    public BoxLabelService(BoxLabelRepository boxLabelRepository) {
        this.boxLabelRepository = boxLabelRepository;
    }

    public List<BoxLabelModel> findAll () {
        return this.boxLabelRepository.findAll();
    }

    public BoxLabelModel findById (UUID id) {
        return this.boxLabelRepository.findById(id).orElse(null);
    }
}

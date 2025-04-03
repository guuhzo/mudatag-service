package br.com.gsndev.mudatag.box.service;

import br.com.gsndev.mudatag.box.dto.BaseBoxLabelDTO;
import br.com.gsndev.mudatag.box.dto.ShortBoxLabelDTO;
import br.com.gsndev.mudatag.box.mapper.BoxLabelMapper;
import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.box.repository.BoxLabelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BoxLabelService {

    private final BoxLabelRepository boxLabelRepository;
    private final BoxLabelMapper boxLabelMapper;

    public BoxLabelService(BoxLabelRepository boxLabelRepository, BoxLabelMapper boxLabelMapper) {
        this.boxLabelRepository = boxLabelRepository;
        this.boxLabelMapper = boxLabelMapper;
    }

    public Page<ShortBoxLabelDTO> findAll (UUID userId, Pageable pageable) {
        Page<BoxLabelModel> page = this.boxLabelRepository.findAllByUserId(userId, pageable);
        return this.boxLabelMapper.toPageDto(page);
    }

    public Optional<BaseBoxLabelDTO> findById (UUID userId, UUID id) {
        return this.boxLabelRepository.findOneById(userId, id).map(this.boxLabelMapper::toDto);
    }
}

package br.com.gsndev.mudatag.box.repository;

import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoxLabelRepository extends JpaRepository<BoxLabelModel, UUID> {
}

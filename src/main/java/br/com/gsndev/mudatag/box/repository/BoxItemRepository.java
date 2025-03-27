package br.com.gsndev.mudatag.box.repository;

import br.com.gsndev.mudatag.box.model.BoxItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxItemRepository extends JpaRepository<BoxItemModel, Integer> {
}

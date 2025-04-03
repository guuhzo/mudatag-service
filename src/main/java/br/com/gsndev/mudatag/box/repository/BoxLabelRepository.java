package br.com.gsndev.mudatag.box.repository;

import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BoxLabelRepository extends JpaRepository<BoxLabelModel, UUID> {
    @Query("""
        SELECT bl FROM BoxLabelModel bl
        JOIN bl.group g
        JOIN g.members m
        WHERE m.id = :userId
    """)
    Page<BoxLabelModel> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query("""
        SELECT bl FROM BoxLabelModel bl
        JOIN FETCH bl.group g
        JOIN g.members m
        WHERE bl.id = :boxId AND m.id = :userId
    """)
    Optional<BoxLabelModel> findOneById(@Param("userId") UUID userId, @Param("boxId") UUID boxId);
}

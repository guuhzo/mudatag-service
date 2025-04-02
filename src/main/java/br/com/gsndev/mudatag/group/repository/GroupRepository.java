package br.com.gsndev.mudatag.group.repository;

import br.com.gsndev.mudatag.group.model.GroupModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<GroupModel, UUID> {
    @Query("""
        SELECT g FROM GroupModel g
        JOIN g.members m
        WHERE m.id = :userId
    """)
    Page<GroupModel> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query("""
        SELECT g FROM GroupModel g
        JOIN FETCH g.owner o
        JOIN FETCH g.members m
        WHERE g.id = :groupId
        AND EXISTS (
            SELECT m2.id FROM GroupModel g2
            JOIN g2.members m2
            WHERE g2.id = :groupId
            AND m2.id = :userId
        )
    """)
    Optional<GroupModel> findOneById(@Param("userId") UUID userId, @Param("groupId") UUID groupId);
}

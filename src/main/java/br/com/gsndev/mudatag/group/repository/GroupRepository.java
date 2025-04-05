package br.com.gsndev.mudatag.group.repository;

import br.com.gsndev.mudatag.group.dto.GroupWithMembersCountDTO;
import br.com.gsndev.mudatag.group.model.GroupModel;
import br.com.gsndev.mudatag.user.model.UserModel;
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
                SELECT new br.com.gsndev.mudatag.group.dto.GroupWithMembersCountDTO(
                    g,
                    SIZE(g.members)
                )
                FROM GroupModel g
                WHERE g.id = :groupId
                AND EXISTS (
                    SELECT m.id FROM GroupModel g2
                    JOIN g2.members m
                    WHERE g2.id = :groupId AND m.id = :userId
                )
            """)
    Optional<GroupWithMembersCountDTO> findOneById(@Param("userId") UUID userId, @Param("groupId") UUID groupId);

    @Query("""
                SELECT m
                FROM GroupModel g
                JOIN g.members m
                WHERE g.id = :groupId
                AND EXISTS (
                     SELECT m.id FROM GroupModel g2
                    JOIN g2.members m
                    WHERE g2.id = :groupId AND m.id = :userId
                )
            """)
    Page<UserModel> findTopMembers(
            @Param("userId") UUID userId,
            @Param("groupId") UUID groupId,
            Pageable pageable
    );
}

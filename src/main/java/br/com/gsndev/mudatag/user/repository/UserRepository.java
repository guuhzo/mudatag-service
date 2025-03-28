package br.com.gsndev.mudatag.user.repository;

import br.com.gsndev.mudatag.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    @Query("SELECT u FROM UserModel u WHERE u.authId = :authId")
    Optional<UserModel> findUserByAuthId(@Param("authId") String authId);
}

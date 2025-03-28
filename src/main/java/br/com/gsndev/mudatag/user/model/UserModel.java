package br.com.gsndev.mudatag.user.model;

import br.com.gsndev.mudatag.group.model.GroupModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<GroupModel> ownedGroups;

    private boolean blocked;

    @Column(name = "auth_id")
    private String authId;
}

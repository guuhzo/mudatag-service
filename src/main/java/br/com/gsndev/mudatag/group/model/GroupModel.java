package br.com.gsndev.mudatag.group.model;

import br.com.gsndev.mudatag.box.model.BoxLabelModel;
import br.com.gsndev.mudatag.user.model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "groups", indexes = {
        @Index(name = "idx_owner_id", columnList = "owner_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserModel owner;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<BoxLabelModel> boxes;

    @ManyToMany
    @JoinTable(name = "group_members",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> members;
}

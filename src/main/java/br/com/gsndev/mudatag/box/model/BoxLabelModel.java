package br.com.gsndev.mudatag.box.model;

import br.com.gsndev.mudatag.group.model.GroupModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "box_labels")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoxLabelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupModel group;

    @OneToMany(mappedBy = "boxLabel")
    @JsonIgnore
    private List<BoxItemModel> items;
}

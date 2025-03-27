package br.com.gsndev.mudatag.box.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "box_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int index;

    private String name;

    @ManyToOne
    @JoinColumn(name = "box_id")
    @JsonIgnore private BoxLabelModel boxLabel;
}
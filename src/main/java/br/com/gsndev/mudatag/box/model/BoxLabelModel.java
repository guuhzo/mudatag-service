package br.com.gsndev.mudatag.box.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}

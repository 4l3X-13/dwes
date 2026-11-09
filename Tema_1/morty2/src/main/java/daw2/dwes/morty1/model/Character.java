package daw2.dwes.morty1.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "character_name", nullable = false, length = 255)
    private String name;

    @Column(length = 50)
    private String status;

    @Column(length = 100)
    private String species;

    @Column(name = "character_type", length = 100)
    private String type;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // TODO: Añadir la relación para las localizaciones

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "image_url", length = 255)
    private String image;

    private Float weight;
    private Float height;

    // TODO: Añadir la relación para los episodios

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "character_episode",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "episode_id")
    )
    private List<Episode> episodes = new ArrayList<>();
}


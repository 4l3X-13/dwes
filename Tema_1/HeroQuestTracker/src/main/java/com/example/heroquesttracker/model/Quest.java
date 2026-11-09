package com.example.heroquesttracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "solved", nullable = false)
    private Boolean solved = false;

    @Column(name = "experience", nullable = false)
    private float experience;

    // Mejora (Para ampliar): recompensa en oro o botín
    @Column(name = "reward_gold")
    private int rewardGold = 0;

    @Column(name = "reward_item", length = 100)
    private String rewardItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_name")
    private Hero hero;

    public Quest(String title, Boolean solved, float experience, Hero hero) {
        this.title = title;
        this.solved = solved != null ? solved : false;
        this.experience = experience;
        this.hero = hero;
    }
}

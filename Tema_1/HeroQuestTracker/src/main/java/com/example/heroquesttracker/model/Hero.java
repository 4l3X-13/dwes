package com.example.heroquesttracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heroes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    @Id
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "\"class\"", length = 50)
    private String heroClass;

    @Column(name = "nivel")
    private int nivel;

    @Column(name = "experience")
    private float experience;

    // Mejora (Para ampliar): oro acumulado por el héroe
    @Column(name = "gold")
    private int gold = 0;

    @OneToMany(mappedBy = "hero", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quest> quests = new ArrayList<>();

    public Hero(String name, String heroClass, int nivel, float experience) {
        this.name = name;
        this.heroClass = heroClass;
        this.nivel = nivel;
        this.experience = experience;
        this.gold = 0;
        this.quests = new ArrayList<>();
    }

    public Hero(String name, String heroClass, int nivel, float experience, int gold) {
        this.name = name;
        this.heroClass = heroClass;
        this.nivel = nivel;
        this.experience = experience;
        this.gold = gold;
        this.quests = new ArrayList<>();
    }

    /**
     * Actualiza el héroe sumando experiencia.
     * Cada 10 puntos de experiencia sumados, el héroe sube un nivel.
     */
    public void addExperience(float xp) {
        int oldSteps = (int) (this.experience / 10.0f);
        this.experience += xp;
        // Redondear a 1 decimal para evitar imprecisiones de coma flotante
        this.experience = Math.round(this.experience * 10.0f) / 10.0f;
        int newSteps = (int) (this.experience / 10.0f);
        int levelsGained = newSteps - oldSteps;
        if (levelsGained > 0) {
            this.nivel += levelsGained;
        }
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    // Alias y métodos de conveniencia
    public String getClazz() {
        return heroClass;
    }

    public void setClazz(String clazz) {
        this.heroClass = clazz;
    }

    public String getClase() {
        return heroClass;
    }

    public void setClase(String clase) {
        this.heroClass = clase;
    }

    public int getLevel() {
        return nivel;
    }

    public void setLevel(int level) {
        this.nivel = level;
    }

    // Helper para la barra de progreso de Thymeleaf (0 a 10)
    public float getExperienceInCurrentLevel() {
        return Math.round((this.experience % 10.0f) * 10.0f) / 10.0f;
    }

    public float getExperienceProgressPercent() {
        return (this.experience % 10.0f) * 10.0f;
    }
}

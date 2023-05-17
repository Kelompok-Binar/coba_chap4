package com.example.challenge_chapter_4.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "studio")
public class StudioEntity {
    @Id
    @Getter
    @Setter
    private char studio_name;
}

package com.example.challenge_chapter_4.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = "film")
public class FilmEntity {


    @Id
    @Getter
    @Setter
    private String film_code;
    @Getter
    @Setter
    private String film_name;
    @Getter
    @Setter
    private String tayang_atau_tidak;

    @OneToMany(mappedBy = "film_code", cascade = CascadeType.ALL)
    private List<JadwalEntity> jadwal;

    @Override
    public String toString() {
        return "FilmEntity{" +
                "film_code='" + film_code + '\'' +
                ", film_name='" + film_name + '\'' +
                ", tayang_atau_tidak='" + tayang_atau_tidak + '\'' +
                '}';
    }
}

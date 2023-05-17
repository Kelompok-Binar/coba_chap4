package com.example.challenge_chapter_4.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "seats")
public class SeatsEntity {
    @Id
    @Getter
    @Setter
    private int nomor_kursi;

}

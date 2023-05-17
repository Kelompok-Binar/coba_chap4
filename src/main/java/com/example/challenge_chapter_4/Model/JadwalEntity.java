package com.example.challenge_chapter_4.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;


@Entity
@Data
@Table(name = "jadwal")
public class JadwalEntity {
    @Id
    @Getter
    @Setter
    private int id_jadwal;
    @Getter
    @Setter
    private String film_code;
    @Getter
    @Setter
    private Date tanggal_tayang;
    @Getter
    @Setter
    private Time jam_mulai;
    @Getter
    @Setter
    private Time jam_selesai;
    @Getter
    @Setter
    private int harga_tiket;

}

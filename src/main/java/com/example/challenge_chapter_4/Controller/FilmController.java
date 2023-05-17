package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.FilmEntity;
import com.example.challenge_chapter_4.Response.FilmResponse;
import com.example.challenge_chapter_4.Response.FilmResponseGenerator;
import com.example.challenge_chapter_4.Service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/*
* Note @RequestParm nanti di webnya menggunakan ?(yang dicari)=(yang dicari) misal ?id = 4
* Note @Patth Variable nanti di webnya menggunakan /(apa yang di cari) misal /4
*
* catatan punya nurul
* - pake @DeleteMapping, endpoint nya gaperlu pake 'value' langsung ajj
*Example :
@DeleteMapping("/deleteFilm/{filmCode}") *perhatikan tanda / (slash)

- bagian @PathVariable, tambahin string paramnya, baru variabel paramnya
*Example :
delFilm(@PathVariable("filmCode") String filmCode)

- untuk delete, pke bawaan jpa,.. cukup gini,
fi.deleteById(filmCode)

- untuk responnya, aku biasa pke boolean sih (true/false) blm tau klo tau pke string😅

*
* */


@Slf4j
@RestController
@RequestMapping(value ="/Film")
public class FilmController {
    @Autowired
    FilmService fs;
    @Autowired
    FilmResponseGenerator frg;

    @GetMapping
    public FilmResponse<List<FilmEntity>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize){

        try {
            Page<FilmEntity> filmResult = fs.getAll(pageNumber, pageSize);
            List<FilmEntity> filmData = filmResult.getContent();
            long totalItems = filmResult.getTotalElements();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalItems));
            log.info("Sukses Tampil Data");
            return frg.succsesResponse(ResponseEntity.ok().headers(headers).body(filmData),"Sukses Tampil Data");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/Judul-Film/{film_name}")
    public FilmResponse<List<FilmEntity>> getByFilm_name(@PathVariable String film_name){
        try {
            List<FilmEntity> film = fs.getByTitle(film_name);
            log.info(String.valueOf(film), "Sukses Mencari Dara '" + film_name + "'");
            return frg.succsesResponse(film,"Sukses Mencari Data '" + film_name + "'");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }


    }

    @GetMapping(value = "/Tayang")
    public FilmResponse<List<FilmEntity>> getFilmTTayang(){
        try {
            List<FilmEntity> film = fs.getTayang();
            log.info(String.valueOf(film));
            return frg.succsesResponse(film,"Sukses Mencari Film Tayang");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/Jadwal/{film_name}")
        public FilmResponse<List<FilmEntity>> getJadwalFilm(@PathVariable String film_name){
        try {
            List<FilmEntity> film = fs.getByFilmJadwal(film_name);
            log.info(String.valueOf(film));
            return frg.succsesResponse(film,"Sukses Mencari Jadwal Film");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/addFilm")
    public FilmResponse<FilmEntity> addFilm(@RequestBody FilmEntity param){
        try {
            FilmEntity film = fs.addFilm(param);
            log.info(String.valueOf(film), "Sukses Add Data " + film.getFilm_code());
            return frg.succsesResponse(film,"Sukses Add Data " + film.getFilm_code()) ;
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/addMultipleFilm")
    public FilmResponse<List<FilmEntity>> addMultipleFilm(@RequestBody List<FilmEntity> param){
        try {
            List<FilmEntity> film = fs.addMultipleFilm(param);
            log.info("Sukses Add Data " + film);
            return frg.succsesResponse(film,"Sukses Add Data " + film) ;
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }
    }

    @PutMapping(value = "/updateFilm")
    public FilmResponse<FilmEntity> updateFilm(@RequestBody FilmEntity param){
        try {
            FilmEntity film = fs.updateFilm(param);
            log.info(String.valueOf(film), "Sukses Update Data " + film.getFilm_code());
            return frg.succsesResponse(film,"Sukses Update Data " + param.getFilm_code());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return frg.failedResponse(e.getMessage());
        }
    }

    // add dan update film sebenarnya caranya sama cuma biar endpointnya berbeda saya buat 2 post mapping

//    @GetMapping(value = "/deleteFilm/{film_code}")
//    public List<FilmEntity> deleteFilm(@PathVariable String film_code){
//        return fs.deleteByNameCode(film_code);
//    }// mau coba pakai ini tapi bad 500 gateway karena sql tidak bisa

    @DeleteMapping(value = "deleteFilm/{film_code}")
    public FilmResponse<FilmEntity> delFilm(@PathVariable String film_code){
        try {
            FilmEntity film = fs.delFilm(film_code);
            log.info(String.valueOf(film), "Sukses Menghapus Data " + film.getFilm_code());
            return frg.succsesResponse(film, "Sukses Menghapus Data " + film.getFilm_code());

        }
        catch (EmptyResultDataAccessException e) {
            log.warn(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found", e);
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Film", e);
        }
    }
}

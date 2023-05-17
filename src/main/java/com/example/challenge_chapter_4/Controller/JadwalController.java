package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.JadwalEntity;
import com.example.challenge_chapter_4.Response.JadwalResponse;
import com.example.challenge_chapter_4.Response.JadwalResponseGenerator;
import com.example.challenge_chapter_4.Service.JadwalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value ="/Jadwal")
public class JadwalController {
    @Autowired
    JadwalService js;
    @Autowired
    JadwalResponseGenerator jrg;

    @GetMapping
    public JadwalResponse<ResponseEntity<List<JadwalEntity>>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        try {
            Page<JadwalEntity> result = js.getAll(pageNumber, pageSize);
            List<JadwalEntity> data = result.getContent();
            long totalItems = result.getTotalElements();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalItems));
            log.info("Sukses Tampil Data");
            return jrg.succsesResponse(ResponseEntity.ok().headers(headers).body(data),"Sukses Tampil Data");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return jrg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/Jadwal-Film/{id_jadwal}")
    public JadwalResponse<JadwalEntity> getById(@PathVariable int id_jadwal){
        try {
            JadwalEntity jadwal = js.getById(id_jadwal);
            log.info(String.valueOf(jadwal),"Sukses Tampil Data " + jadwal.getId_jadwal());
            return jrg.succsesResponse(jadwal,"Sukses Tampil Data " + jadwal.getId_jadwal());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return jrg.failedResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/addJadwal")
    public JadwalResponse<JadwalEntity> addJadwal(@RequestBody JadwalEntity param){
        try {
            JadwalEntity jadwal = js.addJadwal(param);
            log.info(String.valueOf(jadwal),"Sukses add Data " + jadwal.getId_jadwal());
            return jrg.succsesResponse(jadwal,"Sukses add Data " + jadwal.getId_jadwal());
        }
        catch(Exception e){
            log.warn(String.valueOf(e));
            return jrg.failedResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/addMultipleJadwal")
    public JadwalResponse<List<JadwalEntity>> addMultipleJadwal(@RequestBody List<JadwalEntity> param){
        try {
            List<JadwalEntity> jadwal = js.addMultipleJadwal(param);
            log.info("Sukses add Data " + jadwal);
            return jrg.succsesResponse(jadwal,"Sukses add Data " + jadwal);
        }
        catch(Exception e){
            log.warn(String.valueOf(e));
            return jrg.failedResponse(e.getMessage());
        }
    }

    @PutMapping(value = "/updateJadwal")
    public JadwalResponse<JadwalEntity> updateJadwal(@RequestBody JadwalEntity param){
        try {
            JadwalEntity jadwal = js.updateJadwal(param);
            log.info(String.valueOf(jadwal), "Sukses Update Data " + jadwal.getId_jadwal());
            return jrg.succsesResponse(jadwal, "Sukses Update Data " + jadwal.getId_jadwal());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return jrg.failedResponse(e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteJadwal/{id_jadwal}")
    public JadwalResponse<JadwalEntity> deleteJadwal(@PathVariable int id_jadwal){
        try {
            JadwalEntity jadwal = js.deleteJadwal(id_jadwal);
            log.info(String.valueOf(jadwal), "Sukses Delete Data " + jadwal.getId_jadwal());
            return jrg.succsesResponse(jadwal, "Sukses Delete Data " + jadwal.getId_jadwal());
        }
        catch (EmptyResultDataAccessException e) {
            log.warn(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jadwal not found", e);
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Jadwal", e);
        }
    }

}

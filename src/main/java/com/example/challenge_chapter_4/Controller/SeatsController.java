package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.SeatsEntity;
import com.example.challenge_chapter_4.Response.SeatsResponse;
import com.example.challenge_chapter_4.Response.SeatsResponseGenerator;
import com.example.challenge_chapter_4.Service.SeatsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value ="/Seats")
public class SeatsController {
    @Autowired
    SeatsService ss;
    @Autowired
    SeatsResponseGenerator srg;

    @GetMapping
    public SeatsResponse<ResponseEntity<List<SeatsEntity>>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        try {
            Page<SeatsEntity> result = ss.getAll(pageNumber, pageSize);
            List<SeatsEntity> data = result.getContent();
            long totalItems = result.getTotalElements();
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Total-Count", String.valueOf(totalItems));
            log.info("Sukses Tampil Data");
            return srg.succsesResponse(ResponseEntity.ok().headers(headers).body(data),"Sukses Tampil Data");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return srg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "Studios/{studio}/{nomor_kursi}")
    public SeatsResponse<SeatsEntity> getByStudio(@PathVariable char studio, @PathVariable Integer nomor_kursi){
        try {
            SeatsEntity seats = ss.getByStudioSeats(studio, nomor_kursi);
            if(seats != null){
                log.info(String.valueOf(seats),"Sukses Menampilkan Data " +seats.getNomor_kursi());
                return srg.succsesResponse(seats,"Sukses Menampilkan Data " +seats.getNomor_kursi());
                //return srg.succsesResponse2;
            }
            else {
                log.info("Not Found");
                throw new RuntimeException("Not Found");
            }
        }
         catch (Exception e){
            log.warn(String.valueOf(e));
            return srg.failedResponse(e.getMessage());
        }
    }

//    @GetMapping(value = "/Studio/{studio_name}/{nomor_kursi}")
//    public List<SeatsEntity> getByStudioSeat(@PathVariable int nomor_kursi){
//        return ss.getByStudioSeat(nomor_kursi);
//    }
}

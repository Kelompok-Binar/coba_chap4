package com.example.challenge_chapter_4.Response;

import org.springframework.stereotype.Component;

@Component
public class FilmResponseGenerator <T>{
    public <T> FilmResponse <T> succsesResponse(T datas, String msg){
        FilmResponse filmResponse = new FilmResponse<>();
        filmResponse.setStatus("200");
        filmResponse.setMsg(msg);
        filmResponse.setDatas(datas);

        return filmResponse;
    }

    public <T> FilmResponse <T> failedResponse(String msg){
        FilmResponse filmResponse = new FilmResponse<>();
        filmResponse.setStatus("500");
        filmResponse.setMsg(msg);

        return  filmResponse;
    }
}

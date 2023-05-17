package com.example.challenge_chapter_4.Response;

import org.springframework.stereotype.Component;

@Component
public class SeatsResponseGenerator {
    public <T> SeatsResponse <T> succsesResponse(T datas, String msg){
        SeatsResponse seatsResponse = new SeatsResponse<>();
        seatsResponse.setStatus("200");
        seatsResponse.setMsg(msg);
        seatsResponse.setDatas(datas);

        return seatsResponse;
    }
    public <T> SeatsResponse <T> failedResponse(String msg){
        SeatsResponse seatsResponse = new SeatsResponse<>();
        seatsResponse.setStatus("500");
        seatsResponse.setMsg(msg);

        return seatsResponse;
    }
}

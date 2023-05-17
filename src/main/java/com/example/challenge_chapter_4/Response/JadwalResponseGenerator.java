package com.example.challenge_chapter_4.Response;

import org.springframework.stereotype.Component;

@Component
public class JadwalResponseGenerator {
    public <T> JadwalResponse<T> succsesResponse(T datas, String msg){
        JadwalResponse jadwalResponse = new JadwalResponse();
        jadwalResponse.setStatus("200");
        jadwalResponse.setMsg(msg);
        jadwalResponse.setDatas(datas);

        return jadwalResponse;
    }

    public <T> JadwalResponse<T> failedResponse(String msg){
        JadwalResponse jadwalResponse = new JadwalResponse();
        jadwalResponse.setStatus("500");
        jadwalResponse.setMsg(msg);

        return jadwalResponse;
    }
}

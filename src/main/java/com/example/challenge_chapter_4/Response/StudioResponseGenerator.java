package com.example.challenge_chapter_4.Response;

import org.springframework.stereotype.Component;

@Component
public class StudioResponseGenerator {
    public <T> StudioResponse <T> succsesResponse(T datas, String msg){
        StudioResponse studioResponse = new StudioResponse<>();
        studioResponse.setStatus("200");
        studioResponse.setMsg(msg);
        studioResponse.setDatas(datas);

        return studioResponse;
    }

    public <T> StudioResponse <T> failedResponse(String msg){
        StudioResponse studioResponse = new StudioResponse<>();
        studioResponse.setStatus("500");
        studioResponse.setMsg(msg);

        return studioResponse;
    }
}

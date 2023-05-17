package com.example.challenge_chapter_4.Response;

import org.springframework.stereotype.Component;

@Component
public class UserResponseGenerator {
    public <T> UserResponse <T> succsesResponse(T datas, String msg){
        UserResponse userResponse = new UserResponse<>();
        userResponse.setStatus("200");
        userResponse.setMsg(msg);
        userResponse.setDatas(datas);

        return userResponse;
    }

    public <T> UserResponse <T> failedResponse(String msg){
        UserResponse userResponse = new UserResponse<>();
        userResponse.setStatus("500");
        userResponse.setMsg(msg);

        return userResponse;
    }
}

package com.example.challenge_chapter_4.Response;

import lombok.Getter;
import lombok.Setter;

public class JadwalResponse<T>{
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private T datas;

    public JadwalResponse() {
    }

    public JadwalResponse(String status, String msg, T datas) {
        this.status = status;
        this.msg = msg;
        this.datas = datas;
    }
}

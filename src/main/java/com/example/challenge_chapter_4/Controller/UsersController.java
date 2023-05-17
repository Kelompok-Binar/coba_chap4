package com.example.challenge_chapter_4.Controller;

import com.example.challenge_chapter_4.Model.UsersEntity;

import com.example.challenge_chapter_4.Response.UserResponse;
import com.example.challenge_chapter_4.Response.UserResponseGenerator;
import com.example.challenge_chapter_4.Service.UsersService;


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
@RequestMapping(value ="/Users")
public class UsersController {
    @Autowired
    UsersService us;
    @Autowired
    UserResponseGenerator urg;

    @GetMapping()
    public UserResponse<ResponseEntity<List<UsersEntity>>> getAll(
            @RequestParam(defaultValue = "0")int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        try {
            Page<UsersEntity> userResult = us.getAll(pageNumber, pageSize);
            List<UsersEntity> userData = userResult.getContent();
//        int currentPage = userResult.getNumber();
//        int totalPages = userResult.getTotalPages();
            long totalItems = userResult.getTotalElements();

            HttpHeaders userHeaders = new HttpHeaders();
            userHeaders.add("X-Total-Count", String.valueOf(totalItems));
            log.info("Sukses Tampil Data");
            return urg.succsesResponse(ResponseEntity.ok().headers(userHeaders).body(userData),"Sukses Tampil Data");
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return urg.failedResponse(e.getMessage());
        }
    }

    @GetMapping(value = "/findUser/{id_user}") //yang ada di dalam {} disamakan dengan
    public UserResponse<UsersEntity> getById(@PathVariable int id_user){ // yang ini "id_user"
        try {
            UsersEntity user = us.getById(id_user);
            log.info(String.valueOf(user),"Sukses Mencari Data " + user.getId_user());
            return urg.succsesResponse(user,"Sukses Mencari Data " + user.getId_user());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return urg.failedResponse(e.getMessage());
        }

    }

    @PostMapping(value = "/addUsers")
    public UserResponse<UsersEntity> addUsers(@RequestBody UsersEntity param){
        try {
            UsersEntity user = us.addUsers(param);
            log.info(String.valueOf(user), "Sukses Menambahkan Data " + user.getId_user());
            return urg.succsesResponse(user, "Sukses Menambahkan Data " + user.getId_user());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return urg.failedResponse(e.getMessage());
        }

    }

    @PostMapping(value = "/addMultipleUsers")
    public UserResponse<List<UsersEntity>> addMultipleUsers(@RequestBody List<UsersEntity> param){
        try {
            List<UsersEntity> user = us.addMultipleUsers(param);
            log.info(String.valueOf(user), "Sukses Menambahkan Data " + user);
            return urg.succsesResponse(user, "Sukses Menambahkan Data " + user);
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return urg.failedResponse(e.getMessage());
        }

    }


    @PutMapping(value = "/updateUser")
    public UserResponse<UsersEntity> updateUser(@RequestBody UsersEntity param){

        try {
            UsersEntity user = us.updateUser(param);
            log.info(String.valueOf(user),"Sukses Update Data " +user.getId_user());
            return urg.succsesResponse(user,"Sukses Update Data " +user.getId_user());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return urg.failedResponse(e.getMessage());
        }

    }

    @DeleteMapping(value = "/deleteUser/{id_user}")
    public UserResponse<UsersEntity> deleteUser(@PathVariable int id_user){
        try {
            UsersEntity user = us.delUser(id_user);
            log.info("Sukses Menghapus Data " + user.getId_user());
            return urg.succsesResponse(user, "Sukses Menghapus Data " + user.getId_user());
        }
        catch (EmptyResultDataAccessException e) {
            log.warn(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete User", e);
        }
    }
}

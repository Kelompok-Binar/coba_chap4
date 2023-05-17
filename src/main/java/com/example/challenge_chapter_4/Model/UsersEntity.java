package com.example.challenge_chapter_4.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "users")
public class UsersEntity {
    @Id
    @Getter
    @Setter
    private int id_user;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id_user=" + id_user +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

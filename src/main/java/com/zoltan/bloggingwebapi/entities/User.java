package com.zoltan.bloggingwebapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zoltan.bloggingwebapi.entities.enums.UserTypes;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@ToString
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private UserTypes type;

    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private String avatar;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<BlogPost> blogPosts;

    public User(String name, String surname, String email, LocalDate birthday, String password) {
        this.type = UserTypes.AUTHOR;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public UserTypes getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getPassword() {
        return password;
    }
}

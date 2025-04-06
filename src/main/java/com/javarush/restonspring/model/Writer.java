package com.javarush.restonspring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Writer {
    private Long id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
}

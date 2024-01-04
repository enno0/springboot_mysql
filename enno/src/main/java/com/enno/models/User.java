package com.enno.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private int Enabled;
    private String FullName;
    private String UserName;
    private String Password;
    private int id;
}

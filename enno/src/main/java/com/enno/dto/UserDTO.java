package com.enno.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserDTO {

    private int Enabled = 1;
    private String FullName = "FULLNAME";
    private String UserName = "USERNAME";
    private String Password = "PASSWORD";
}

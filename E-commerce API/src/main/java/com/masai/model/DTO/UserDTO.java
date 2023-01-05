package com.masai.model.DTO;

import lombok.*;

import javax.validation.constraints.*;
import javax.xml.transform.sax.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 4, message = "size Should be atleast 4")
    private String newPassword;

}

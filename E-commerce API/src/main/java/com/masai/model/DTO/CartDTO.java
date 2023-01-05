package com.masai.model.DTO;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.*;
import java.io.*;
import java.time.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO{

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate shipDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate deliveryDate;

}
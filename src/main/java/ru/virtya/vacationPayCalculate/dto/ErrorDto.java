package ru.virtya.vacationPayCalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ErrorDto {

    private String message;

    private LocalDate timestamp;
}

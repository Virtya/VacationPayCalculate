package ru.virtya.vacationPayCalculate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationPayDto {

    private String message;

    private double vacationPay;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacationPayDto that = (VacationPayDto) o;
        return Double.compare(that.vacationPay, vacationPay) == 0 && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, vacationPay);
    }
}

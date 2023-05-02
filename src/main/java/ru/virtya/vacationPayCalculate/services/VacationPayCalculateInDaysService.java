package ru.virtya.vacationPayCalculate.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface VacationPayCalculateInDaysService {
    int getPaidDays(String vacationStartDate, String vacationEndDate);
}

package ru.virtya.vacationPayCalculate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.virtya.vacationPayCalculate.controllers.VacationPayCalculateController;
import ru.virtya.vacationPayCalculate.dto.VacationPayDto;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateInDaysService;
import ru.virtya.vacationPayCalculate.services.VacationPayCalculateService;

import java.time.format.DateTimeParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class VacationPayDtoCalculateApplicationTests {

	@Autowired
	private VacationPayCalculateController vacationPayCalculateController;

	@Autowired
	private VacationPayCalculateInDaysService vacationPayCalculateInDaysService;

	@Autowired
	private VacationPayCalculateService vacationPayCalculateService;

	@Test
	void contextLoads() {
		assertThat(vacationPayCalculateController).isNotNull();
		assertThat(vacationPayCalculateInDaysService).isNotNull();
		assertThat(vacationPayCalculateService).isNotNull();
	}

	@Test
	public void equalZeroVacationDaysShouldThrowException() {
		assertThrows(
				IllegalArgumentException.class,
				() -> vacationPayCalculateService.getCalculateVacationPay(20000, 0)
		);
	}

	@Test
	public void belowZeroVacationDaysShouldThrowException() {
		assertThrows(
				IllegalArgumentException.class,
				() -> vacationPayCalculateService.getCalculateVacationPay(20000, -10)
		);
	}

	@Test
	public void belowZeroSalaryPerYearShouldThrowException() {
		assertThrows(
				IllegalArgumentException.class,
				() -> vacationPayCalculateService.getCalculateVacationPay(-20000, 10)
		);
	}

	@Test
	public void countingTotalPayForVacationShouldReturnCorrectValue() {
		assertEquals(6825.94, vacationPayCalculateService
				.getCalculateVacationPay(20000, 10)
				.getVacationPay()
		);
	}

	@Test
	public void getPaidDaysShouldReturnCorrectValue() {
		assertEquals(4, vacationPayCalculateInDaysService
				.getPaidDays("01-05-2023", "07-05-2023")
		);
	}

	@Test
	public void getPaidDaysWithIncorrectFormatShouldReturnParseException() {
		assertThrows(DateTimeParseException.class,
				() -> vacationPayCalculateInDaysService
						.getPaidDays("01-02-2023", "01.02.23")
		);
	}

	@Test
	public void getVacationPayShouldReturnCorrectValue() {
		assertEquals(
				new VacationPayDto("Сумма отпускных в рублях", 2730.38),
				vacationPayCalculateController.getVacationPay(
						20000, 7, Optional.of("01-05-2023"),
						Optional.of("07-05-2023")
				)
		);
	}
}

package af.asr.identity.service.rest;

import com.datastax.driver.core.LocalDate;

import java.time.Clock;
import java.time.temporal.ChronoField;

public class Time {
    public static LocalDate utcNowAsStaxLocalDate()
    {
        return LocalDate.fromDaysSinceEpoch((int) utcNowInEpochDays());
    }

    private static long utcNowInEpochDays()
    {
        return java.time.LocalDate.now(Clock.systemUTC()).getLong(ChronoField.EPOCH_DAY);
    }
}
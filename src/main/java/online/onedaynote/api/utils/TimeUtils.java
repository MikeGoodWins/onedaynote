package online.onedaynote.api.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class TimeUtils {


    private TimeUtils() {
    }

    public static LocalDateTime now() {
        return LocalDateTime.now(TimeUtils.utcZone()).plusHours(3L);
    }

    public static ZoneId utcZone() {
        return ZoneId.of("UTC");
    }
}

package online.onedaynote.api.utils;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class StringUtils {


    private StringUtils() {
    }

    public static boolean isNullOrEmpty(final String s) {
        return Objects.isNull(s) || s.isEmpty();
    }

    public static boolean notNullAndNotEmpty(final String s) {
        return !isNullOrEmpty(s);
    }
}

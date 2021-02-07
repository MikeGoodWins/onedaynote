package online.onedaynote.api;

import java.util.logging.Logger;
import javax.ws.rs.core.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneDayNoteApi {

    public static void main(String[] args) {
        SpringApplication.run(OneDayNoteApi.class, args);
        Logger logger = Logger.getLogger(Application.class.getName());
        logger.info("                         ///////////////////////// O N E D A Y N O T E   R U N !!! " +
                "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ (GoodWin)");
    }
}

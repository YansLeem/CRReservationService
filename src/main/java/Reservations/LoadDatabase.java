package Reservations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class LoadDatabase {


    List<Long> vechicles1 = Arrays.asList(5L, 7L, 4L);
    List<Long> vechicles2 = Arrays.asList(1L, 2L, 3L);

    @Bean
    CommandLineRunner initDatabase(ReservationRepository repository) throws Exception{
        return args -> {
            log.info("Preloading" + repository.save(new Reservation(1L,12335L, vechicles1)));
            log.info("Preloading" + repository.save(new Reservation(2L,77777L, vechicles2)));
        };
    }
}

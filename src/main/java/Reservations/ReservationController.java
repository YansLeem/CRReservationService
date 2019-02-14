package Reservations;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
//@RequestMapping("people")
public class ReservationController {

    @Autowired

    private Logic logic;

    ReservationController(ReservationRepository repository){
        this.logic = new Logic(repository);
    };

    @GetMapping(path = "/reservations",produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<Reservation>> all(){

        return logic.all();
    }

    @PostMapping(path = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation newEmployee(@RequestBody Reservation newEmployee) {

        return logic.newEmployee(newEmployee);
    }

    @GetMapping(path = "/reservations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Reservation> one(@PathVariable Long id) {

       return logic.one(id);
    }

    @DeleteMapping("/reservations/{id}")
    void deleteReservation(@PathVariable Long id) {

        logic.deleteReservation(id);
    }

    @PutMapping(path = "reservations/{id}/car/{carid}")
    public void addCarToReservation(@PathVariable Long id, @PathVariable Long carid){
        System.out.println("ADDED" + carid + " TO " + id + "RESERVATION" );
        logic.addCarToReservation(id,carid);
    }

}

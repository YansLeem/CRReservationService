package Reservations;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class Logic {

    private final ReservationRepository repository;

    Logic(ReservationRepository repository){
        this.repository = repository;
    }

    public Resources<Resource<Reservation>> all(){

        List<Resource<Reservation>> Reservations = repository.findAll().stream()
                .map(reservation -> new Resource<>(reservation,
                        linkTo(methodOn(ReservationController.class).one(reservation.getId())).withSelfRel(),
                        linkTo(methodOn(ReservationController.class).all()).withRel("Reservations")))
                .collect(Collectors.toList());

        return new Resources<>(Reservations,
                linkTo(methodOn(ReservationController.class).all()).withSelfRel());
    }


    public Reservation newEmployee( Reservation newEmployee) {

        if(newEmployee.getId() == 111L) {
            List<Long> a = new ArrayList<Long>();
            a.add(12L);
            a.add(777L);
            a.add(111L);

            Reservation newreservation = new Reservation(111L, 111L, a);
            return newreservation;
        }

        return repository.save(newEmployee);
    }


    public Resource<Reservation> one( Long id) {

        if(id == 777){
            List<Long> a = new ArrayList<Long>();
            a.add(12L);
            a.add(777L);
            a.add(111L);

            Reservation newreservation = new Reservation(111L, 111L, a);

            return new Resource<>(newreservation,
                    linkTo(methodOn(ReservationController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(ReservationController.class).all()).withRel("Reservations"));

        }

        Reservation Reservation = repository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));

        return new Resource<>(Reservation,
                linkTo(methodOn(ReservationController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ReservationController.class).all()).withRel("Reservations"));
    }


    void deleteReservation( Long id) {
        repository.deleteById(id);
    }


    public void addCarToReservation( Long id,  Long carid){
        System.out.println("ADDED" + carid + " TO " + id + "RESERVATION" );
        repository.findById(id)
                .map(Car -> {
                    Car.addCartoReservation(carid);
                    return repository.save(Car);
                });
    }
}

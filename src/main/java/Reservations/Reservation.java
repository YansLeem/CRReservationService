package Reservations;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Reservation {
    private @Id Long id;
    private Long PersonID;
    @Column
    @ElementCollection(targetClass=Long.class)
    private List<Long> CarsIDs;

    Reservation(Long id, Long personID, List<Long> reservedCars){
        this.id = id;
        this.PersonID = personID;
        this.CarsIDs = reservedCars;
    }

    void addCartoReservation(Long newCarID){
        this.CarsIDs.add(newCarID);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonID() {
        return PersonID;
    }

    public void setPersonID(Long personID) {
        PersonID = personID;
    }

    public List<Long> getCarsIDs() {
        return CarsIDs;
    }

    public void setCarsIDs(List<Long> carsIDs) {
        CarsIDs = carsIDs;
    }
}


/*
    fetch(
  '/people',
    {
        method: 'POST',
                headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: 'Paul', lastName: 'Jackson' })
    }
).then(result => result.json().then(console.log))


   fetch(
  '/p',
    {
        method: PUT',
                headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ number: 17, taken: false })
    })
*/






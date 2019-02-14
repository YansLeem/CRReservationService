package Reservations;

public class ReservationNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public ReservationNotFoundException(Long id) {
            super("Could not find Reservation " + id);
        }
}

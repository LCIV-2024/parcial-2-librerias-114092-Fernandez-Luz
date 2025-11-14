package com.example.libreria.repository;

import com.example.libreria.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Reservas por usuario
    List<Reservation> findByUserId(Long userId);

    // Reservas por estado (ACTIVE, RETURNED, OVERDUE)
    List<Reservation> findByStatus(Reservation.ReservationStatus status);

    // Reservas vencidas: estado ACTIVE y expectedReturnDate < fecha actual
    @Query("SELECT r FROM Reservation r WHERE r.status = com.example.libreria.model.Reservation.ReservationStatus.ACTIVE AND r.expectedReturnDate < CURRENT_DATE")
    List<Reservation> findOverdueReservations();
    // TODO: Implementar los métodos de la reserva
}


package thud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import thud.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookableId(Long bookable_id);
}

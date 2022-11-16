package thud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import thud.entity.Bookable;
import thud.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Bookable> findByBookableId(Long bookableId);
}

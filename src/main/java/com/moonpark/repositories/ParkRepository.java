package com.moonpark.repositories;

import com.moonpark.domains.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {
	Optional<Park> findByLicensePlate(final String licensePlate);
}

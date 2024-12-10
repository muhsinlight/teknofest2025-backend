package com.teknofest.turizm.repository;

import com.teknofest.turizm.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("SELECT p FROM Place p WHERE " +
            "LOWER(p.city) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.region) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.address) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Place> searchByCityOrRegionOrAddress(String query);

    @Query("SELECT p FROM Place p WHERE p.isApproved = false")
    List<Place> findAllApprovedPlaces();
}

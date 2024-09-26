package com.cdk.dealerlocator.repository;

import com.cdk.dealerlocator.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DealerRepository extends JpaRepository<Dealer, Long> {

    List<Dealer> findByZipCode(String zipCode);

    @Query(value = "SELECT id, name, address, city, state, zip_code, latitude, longitude, "
            + "(3959 * acos( cos( radians(:userLat) ) * cos( radians( latitude ) ) "
            + "* cos( radians( longitude ) - radians(:userLon) ) + sin( radians(:userLat) ) "
            + "* sin( radians( latitude ) ) ) ) AS distance "
            + "FROM dealers "
            + "HAVING distance < :radius "
            + "ORDER BY distance",
            nativeQuery = true)
    List<Dealer> findNearbyDealers(@Param("userLat") double userLat,
                                   @Param("userLon") double userLon,
                                   @Param("radius") double radius);
}

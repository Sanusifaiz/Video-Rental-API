package com.learningjava.VideoRentalApi.repositories;


import com.learningjava.VideoRentalApi.entity.RentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Integer> {
}

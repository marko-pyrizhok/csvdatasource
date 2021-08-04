package edu.home.csvasdatasource.repository;

import edu.home.csvasdatasource.pojo.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentH2Repository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p")
    List<Payment> findAll(Long id, String filter);
}

package edu.home.csvasdatasource.repository;

import java.util.List;

import edu.home.csvasdatasource.pojo.Payment;

public interface PaymentRepository {
  List<Payment> findAll(Long id, String filter);

  void save(Payment payment);

  void deleteById(Long id);
}
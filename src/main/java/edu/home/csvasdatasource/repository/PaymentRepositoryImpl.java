package edu.home.csvasdatasource.repository;

import edu.home.csvasdatasource.pojo.Payment;
import edu.home.csvasdatasource.util.CSVUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static edu.home.csvasdatasource.util.CSVBeanFilters.getComplexFilter;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @Override
    public synchronized List<Payment> findAll(Long id, String filter) {
        return CSVUtils.readPayments(getComplexFilter(id, filter));
    }

    @Override
    public synchronized void save(Payment payment) {
        if (payment.getId()==null) {
            CSVUtils.createPayment(payment);
        } else {
            CSVUtils.updatePayment(payment);
        }
    }

    @Override
    public synchronized void deleteById(Long id) {
        CSVUtils.deletePayment(id);
    }
}

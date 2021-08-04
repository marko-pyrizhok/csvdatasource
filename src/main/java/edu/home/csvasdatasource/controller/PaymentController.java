package edu.home.csvasdatasource.controller;

import edu.home.csvasdatasource.model.PaymentModel;
import edu.home.csvasdatasource.pojo.Payment;
import edu.home.csvasdatasource.repository.PaymentH2Repository;
import edu.home.csvasdatasource.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
//    @Autowired
//    private PaymentH2Repository paymentRepository;

    @GetMapping
    public ResponseEntity<List<PaymentModel>> read(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "filter", required = false) String filter
    ) {

        List<PaymentModel> paymentList = paymentRepository.findAll(id,filter).stream()
                .map(getPaymentFromPaymentModelFunction())
                .collect(Collectors.toList());

        return new ResponseEntity<>(
            paymentList,
            HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<PaymentModel> create(@RequestBody PaymentModel paymentModel) {

        Payment payment = getPaymentModelFromPaymentFunction().apply(paymentModel);
        paymentRepository.save(payment);

        return new ResponseEntity<>(
            getPaymentFromPaymentModelFunction().apply(payment),
            HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<PaymentModel> update(@RequestBody PaymentModel paymentModel) {

        Payment payment = getPaymentModelFromPaymentFunction().apply(paymentModel);
        paymentRepository.save(payment);

        return new ResponseEntity<>(
            getPaymentFromPaymentModelFunction().apply(payment),
            HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        paymentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private Function<Payment, PaymentModel> getPaymentFromPaymentModelFunction() {
        return payment ->
                new PaymentModel(
                        payment.getId(),
                        payment.getAmount(),
                        payment.getCurrency(),
                        payment.getUserId(),
                        payment.getTargetBankAccountNumber()
                );
    }

    private Function<PaymentModel, Payment> getPaymentModelFromPaymentFunction() {
        return paymentModel ->
                new Payment(
                        paymentModel.getId(),
                        paymentModel.getAmount(),
                        paymentModel.getCurrency(),
                        paymentModel.getUserId(),
                        paymentModel.getTargetBankAccountNumber()
                );
    }
}

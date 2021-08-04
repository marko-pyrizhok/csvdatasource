package edu.home.csvasdatasource.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import edu.home.csvasdatasource.pojo.Payment;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static edu.home.csvasdatasource.util.CSVBeanFilters.getExcludeFilter;

public class CSVUtils {

    public static final String DATA_SOURCE = "dataDirectory/payments.csv";

    public static void createPayment(Payment payment) {
        List<Payment> paymentList = readPayments(null);
        payment.setId(generateId(paymentList));
        paymentList.add(payment);
        saveAllPayments(paymentList);
    }

    public static List<Payment> readPayments(CsvToBeanFilter filter) {
        List<Payment> paymentList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(DATA_SOURCE))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Payment.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvToBean.setFilter(filter);

            paymentList = csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    public static void updatePayment(Payment payment) {
        List<Payment> paymentList = readPayments(getExcludeFilter(null));

        for (Payment p : paymentList) {
            if (p.getId().equals(payment.getId())) {
                p.setAmount(payment.getAmount());
                p.setCurrency(payment.getCurrency());
                p.setUserId(payment.getUserId());
                p.setTargetBankAccountNumber(payment.getTargetBankAccountNumber());
            }
        }
        saveAllPayments(paymentList);
    }

    public static void deletePayment(Long id) {
        Payment filteredOutPayment = new Payment();
        filteredOutPayment.setId(id);
        List<Payment> paymentList = readPayments(getExcludeFilter(filteredOutPayment));
        saveAllPayments(paymentList);
    }

    private static long generateId(List<Payment> paymentList) {
        return paymentList.stream()
                .max((payment1, payment2) -> (int) (payment1.getId() - payment2.getId()))
                .get()
                .getId() + 1;
    }

    private static void saveAllPayments(List<Payment> paymentList) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(DATA_SOURCE))) {
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(paymentList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}

package edu.home.csvasdatasource.util;

import com.opencsv.bean.CsvToBeanFilter;
import edu.home.csvasdatasource.pojo.Payment;

public class CSVBeanFilters {

    public static CsvToBeanFilter getComplexFilter(Long id, String filter) {
        Payment paymentFilter = null;
        if (id != null || filter != null && !filter.isEmpty()) {
            paymentFilter = new Payment();
            if (id != null) {
                paymentFilter.setId(id);
            }
            if (filter != null && !filter.isEmpty()) {
                paymentFilter.setCurrency(filter);
                try {
                    paymentFilter.setAmount(Float.parseFloat(filter));
                    paymentFilter.setUserId(Long.parseLong(filter));
                    paymentFilter.setTargetBankAccountNumber(Long.parseLong(filter));
                } catch (NumberFormatException ex) {
                    // no problem, we can reject filtering by these fields
                }
            }
        }
        CsvToBeanFilter includeFilter = getIncludeFilter(paymentFilter);
        return includeFilter;
    }

    public static CsvToBeanFilter getExcludeFilter(Payment filteredOutPayment) {
        CsvToBeanFilter filter = (line) -> {
            boolean result = true;
            if (filteredOutPayment == null) {
                return true;
            }
            if (filteredOutPayment.getId() != null) {
                result = result && Long.parseLong(line[2]) != filteredOutPayment.getId();
            }
            if (filteredOutPayment.getAmount() != null) {
                result = result && Long.parseLong(line[0]) != filteredOutPayment.getAmount();
            }
            if (filteredOutPayment.getCurrency() != null) {
                result = result && line[1] != filteredOutPayment.getCurrency();
            }
            if (filteredOutPayment.getUserId() != null) {
                result = result && Long.parseLong(line[3]) != filteredOutPayment.getUserId();
            }
            if (filteredOutPayment.getTargetBankAccountNumber() != null) {
                result = result && Long.parseLong(line[4]) != filteredOutPayment.getTargetBankAccountNumber();
            }
            return result;
        };
        return filter;
    }

    public static CsvToBeanFilter getIncludeFilter(Payment paymentFilter) {
        CsvToBeanFilter filter = (line) -> {
            boolean result = false;
            if (paymentFilter == null) {
                return true;
            }
            if (paymentFilter.getCurrency() != null) {
                result = result || line[1].equals(paymentFilter.getCurrency());
            }
            try {
                if (paymentFilter.getAmount() != null) {
                    result = result || Float.parseFloat(line[0]) == paymentFilter.getAmount();
                }

                if (paymentFilter.getId() != null) {
                    result = result || Long.parseLong(line[2]) == paymentFilter.getId();
                }
                if (paymentFilter.getUserId() != null) {
                    result = result || Long.parseLong(line[3]) == paymentFilter.getUserId();
                }
                if (paymentFilter.getTargetBankAccountNumber() != null) {
                    result = result || Long.parseLong(line[4]) == paymentFilter.getTargetBankAccountNumber();
                }
            } catch (NumberFormatException ex) {
                // no problem here
            }
            return result;
        };
        return filter;
    }
}

package com.ogawalucas.contadorvendas;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalesReader {

    private final List<Sale> sales;

    public SalesReader(String salesFile) {

        final var dataStream = ClassLoader.getSystemResourceAsStream(salesFile);

        if (dataStream == null) {
            throw new IllegalStateException("File not found or is empty");
        }

        final var builder = new CsvToBeanBuilder<Sale>(new InputStreamReader(dataStream, StandardCharsets.ISO_8859_1));

        sales = builder
                .withType(Sale.class)
                .withSeparator(';')
                .build()
                .parse();
    }

    public void totalOfCompletedSales() {
        final var totalOfCompletedSales = sales.stream()
            .filter(Sale::isCompleted)
            .map(Sale::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("Total Of Completed Sales: \n\t%s", toCurrency(totalOfCompletedSales))
            .println("\n\n--------------------\n");
    }

    public void totalOfCancelledSales() {
        final var totalOfCancelledSales = sales.stream()
            .filter(Sale::isCancelled)
            .map(Sale::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("Total Of Cancelled Sales: \n\t%s", toCurrency(totalOfCancelledSales))
            .println("\n\n--------------------\n");
    }

    public void mostRecentCompletedSale() {
        final var mostRecentCompletedSaleDate = sales.stream()
            .filter(Sale::isCompleted)
            .map(Sale::getSaleDate)
            .max(LocalDate::compareTo);

        System.out.println("Most Recent Completed Sales:");
        if (mostRecentCompletedSaleDate.isPresent()) {
            final var mostRecentCompletedSales = sales.stream()
                .filter(sale -> sale.getSaleDate().equals(mostRecentCompletedSaleDate.get()))
                .map(Sale::getNumber)
                .collect(Collectors.joining(", "));
            System.out.printf("\tSales Number: %s", mostRecentCompletedSales).println();
        } else {
            System.out.println("No results found.");
        }
        System.out.println("\n--------------------\n");
    }

    public void daysBetweenFirstAndLastCancelledSale() {
        final var datesOfCancelledSales = sales.stream()
            .filter(Sale::isCancelled)
            .map(Sale::getSaleDate)
            .toList();
        final var dateOfFirstCancelledSale = datesOfCancelledSales.stream()
            .min(Comparator.naturalOrder());
        final var dateOfLastCancelledSale = datesOfCancelledSales.stream()
            .max(Comparator.naturalOrder());

        System.out.print("Days Between First And Last Cancelled Sale: \n\t");
        if (!datesOfCancelledSales.isEmpty()) {
            final var daysBetweenFirstAndLastCancelledSale =
                ChronoUnit.DAYS.between(dateOfFirstCancelledSale.get(), dateOfLastCancelledSale.get());
            System.out.print(daysBetweenFirstAndLastCancelledSale);
        } else {
            System.out.print("No results found.");
        }
        System.out.println("\n\n--------------------\n");
    }

    public void totalCompletedSalesBySeller(String sellerName) {
        final var totalCompletedSalesBySeller = sales.stream()
            .filter(sale -> sale.getSeller().equals(sellerName))
            .filter(Sale::isCompleted)
            .map(Sale::getValue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("Total Completed Sales By Seller %s: \n\t%s", sellerName, toCurrency(totalCompletedSalesBySeller))
            .println("\n\n--------------------\n");
    }

    public void countAllSalesByManager(String managerName) {
        final var countAllSalesByManager = sales.stream()
            .filter(sale -> sale.getManager().equals(managerName))
            .count();

        System.out.printf("Count All Sales By Manager %s: \n\t%s", managerName, countAllSalesByManager)
            .println("\n\n--------------------\n");
    }

    public void totalSalesByStatusAndMonth(Sale.Status status, Month... months) {
        final var totalSalesByStatusAndMonth = sales.stream()
            .filter(sale -> sale.getStatus().equals(status))
            .filter(sale -> Stream.of(months).anyMatch(month -> month.equals(sale.getSaleDate().getMonth())))
            .count();
        final var monthsString = Stream.of(months)
            .map(Month::name)
            .collect(Collectors.joining(", "));

        System.out.printf("Total Sales By Status %s And Months %s: \n\t%s", status, monthsString, totalSalesByStatusAndMonth)
            .println("\n\n--------------------\n");
    }

    public void countCompletedSalesByDepartment() {
        final var countCompletedSalesByDepartment = sales.stream()
            .collect(Collectors.groupingBy(Sale::getDepartment, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (departament1, departament2) -> departament1,
                LinkedHashMap::new
            ));

        System.out.println("Count Completed Sales By Department:");
        countCompletedSalesByDepartment
            .forEach((departament, countCompletedSales) -> System.out.println("\t"+ departament + ": " + countCompletedSales));
        System.out.println("\n--------------------\n");
    }

    public void countCompletedSalesByPaymentMethodAndGroupingByYear() {
        final var countCompletedSalesByPaymentMethodAndGroupingByYear = sales.stream()
            .collect(Collectors.groupingBy(Sale::getSaleYear, Collectors.groupingBy(Sale::getPaymentMethod, Collectors.counting())))
            .entrySet().stream()
            .sorted(Map.Entry.<Integer, Map<String, Long>>comparingByKey().reversed())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (year1, year2) -> year1,
                LinkedHashMap::new
            ));

        System.out.println("Count Completed Sales By Payment Method And Grouping By Year:");
        countCompletedSalesByPaymentMethodAndGroupingByYear
            .forEach((years, countPaymentsMethods) -> {
                System.out.println("\t" + years + ": ");
                countPaymentsMethods
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(entrySet1 -> System.out.println("\t\t" + entrySet1.getKey() + ": " + entrySet1.getValue()));
            });
        System.out.println("\n--------------------\n");
    }

    public void top3BestSellers() {
        final var top3BestSellers = sales.stream()
            .collect(Collectors.groupingBy(Sale::getSeller, Collectors.reducing(BigDecimal.ZERO, Sale::getValue, BigDecimal::add)))
            .entrySet().stream()
            .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
            .limit(3)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (seller1, seller2) -> seller1,
                LinkedHashMap::new
            ));

        System.out.println("Top3 Best Sellers:");
        top3BestSellers
            .forEach((seller, totalSalesValue) -> System.out.println("\t" + seller + ": " + toCurrency(totalSalesValue)));
        System.out.println("\n--------------------\n");
    }

    /*
     * Use esse metodo para converter objetos BigDecimal para uma represetancao de moeda
     */
    private String toCurrency(BigDecimal value) {
        return NumberFormat.getInstance().format(value);
    }
}

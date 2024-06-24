package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Formater {

    private final static Locale locale = new Locale("pt", "BR");

    public static BigDecimal toBigDecimal(String salarioText) {
       return new BigDecimal(salarioText.replace(",", "."));
    }

    public static LocalDate toDate(String dataText) {
       return LocalDate.parse(dataText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String toStringBRL(BigDecimal salario) {
        return NumberFormat.getCurrencyInstance(locale).format(salario);
    }
}

package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Formater {
    public static String fromMoedaBRL(BigDecimal salario) {
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(salario);
    }

    public static String fromDataBRL(LocalDate data) {
        DateTimeFormatter  dtf = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();
        return data.format(dtf);
    }
}

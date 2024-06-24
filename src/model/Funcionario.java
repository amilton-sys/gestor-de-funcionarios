package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private Funcao funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, Funcao funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public void adicionarAumento(BigDecimal aumento) {
        BigDecimal valorAumento = getSalario().multiply(aumento);
        this.salario = this.salario.add(valorAumento);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public int pegaIdade(){
        LocalDate dataNascimento = getDataNascimento();
        LocalDate hoje = LocalDate.now();
        return Period.between(dataNascimento, hoje).getYears();
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();
        Locale locale = new Locale("pt", "BR");
        String salario = NumberFormat.getCurrencyInstance(locale).format(getSalario());
        return String.format("""
                Nome: %s
                Data de nascimento: %s
                Salario: %s
                Funcao: %s
                """, super.getNome(), super.getDataNascimento().format(dtf), salario, getFuncao());
    }

    public void setSalario(BigDecimal add) {
        this.salario = this.salario.add(add);
    }
}

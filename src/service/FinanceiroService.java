package service;

import model.Funcionario;
import utils.Formater;

import java.math.BigDecimal;
import java.util.List;

public class FinanceiroService {
    private BigDecimal aumento = new BigDecimal("0,1");
    private BigDecimal salarioMinimo = new BigDecimal("1212.00");

    public void adicionarAumento(Funcionario funcionario) {
        BigDecimal valorAumento = funcionario.getSalario().multiply(aumento);
        funcionario.setSalario(funcionario.getSalario().add(valorAumento));
    }

    public void imprimeQuantidadeSalariosMinimos(List<Funcionario> funcionarios) {
        funcionarios.forEach(funcionario -> {
            int quantidadeSalarios = funcionario.getSalario().divideToIntegralValue(salarioMinimo).intValue();
            System.out.println(funcionario.getNome());
            System.out.println("Quantidade de salarios minimo: " + quantidadeSalarios);
        });
    }

    public void imprimeTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal salarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            salarios = salarios.add(funcionario.getSalario());
        }
        String totalSalarios = Formater.fromMoedaBRL(salarios);
        System.out.println(totalSalarios);
    }

    public void aumentoSalario(List<Funcionario> funcionarios) {
        funcionarios.forEach(this::adicionarAumento);
    }
}

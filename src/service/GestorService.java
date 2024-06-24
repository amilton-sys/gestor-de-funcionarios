package service;

import exception.FuncionarioExistenteException;
import exception.FuncionarioNaoExistente;
import model.Funcao;
import model.Funcionario;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class GestorService {
    private List<Funcionario> funcionarios;
    private BigDecimal aumento;
    private Map<Funcao, List<Funcionario>> funcionariosMap;

    public GestorService() {
        this.funcionarios = new ArrayList<>();
        this.aumento = new BigDecimal("0.1");
        this.funcionariosMap = new HashMap<>();
    }

    public void imprimeQuantidadeSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(funcionario -> {
            BigDecimal quantidade = funcionario.getSalario().divideToIntegralValue(salarioMinimo);
            System.out.println(funcionario.getNome());
            System.out.println("Quantidade de salarios minimos: " + quantidade);
            System.out.println("--------------------------");
        });
    }

    public void imprimeTotalSalarios() {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        Locale locale = new Locale("pt", "BR");
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        String total = NumberFormat.getCurrencyInstance(locale).format(totalSalarios);
        System.out.println(total);
    }

    public void imprimeFuncionarioOrdemAlfabetica() {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);
    }

    public void imprimeFuncionariosMaiorIdade() {
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparingInt(Funcionario::pegaIdade));
        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + ", Idade: " + maisVelho.pegaIdade());
    }

    public void imprimeFuncionariosAniversariantes() {
        System.out.println("Aniversariantes:");
        LocalDate today = LocalDate.now();
        funcionarios.forEach(funcionario -> {
            if (funcionario.getDataNascimento().getMonth().equals(Month.OCTOBER) || funcionario.getDataNascimento().getMonth().equals(Month.DECEMBER)) {
                int years = Period.between(funcionario.getDataNascimento(), today).getYears();
                System.out.println(funcionario.getNome());
                System.out.println(years);
            }
        });
    }

    public Map<Funcao, List<Funcionario>> agruparFuncionarioPorFuncao() {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public void imprimeFuncionariosPorFuncao() {
        Map<Funcao, List<Funcionario>> porFuncao = agruparFuncionarioPorFuncao();
        porFuncao.forEach((funcao, funcionarios) -> {
            System.out.println(funcao);
            funcionarios.forEach(System.out::println);
        });
    }

    public void aumentoSalario() {
        funcionarios.forEach(funcionario -> {
            funcionario.adicionarAumento(aumento);
        });
    }

    public void adicionarMuitosFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios.addAll(funcionarios);
    }

    public void adicionarUmFuncionario(Funcionario funcionario) {
        if (funcionarios.contains(funcionario)) {
            throw new FuncionarioExistenteException("Este funcionario já foi adicionado.");
        }
        this.funcionarios.add(funcionario);
    }

    public void removerUmFuncionario(String nome) {
        if (funcionarios.stream().noneMatch(funcionario -> funcionario.getNome().equals(nome))) {
            throw new FuncionarioNaoExistente("Funcionário não encontrado.");
        }
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

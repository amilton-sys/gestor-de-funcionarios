package service;

import exception.FuncionarioExistenteException;
import exception.FuncionarioNaoExistente;
import model.Funcao;
import model.Funcionario;
import validations.ValidaAniversariantes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class GestorService {
    private List<Funcionario> funcionarios;
    private FinanceiroService financeiroService;

    public GestorService() {
        this.funcionarios = new ArrayList<>();
        this.financeiroService = new FinanceiroService();
        this.importarFuncionarios();
    }

    public void aplicarAumento() {
        funcionarios.forEach(funcionario -> {
            financeiroService.adicionarAumento(funcionario);
        });
    }

    public void imprimeQuantidadeSalariosMinimos() {
        financeiroService.imprimeQuantidadeSalariosMinimos(funcionarios);
    }

    public void imprimirTotalSalariosFuncionarios() {
        financeiroService.imprimeTotalSalarios(funcionarios);
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
        funcionarios.forEach(ValidaAniversariantes::validar);
    }

    public Map<Funcao, List<Funcionario>> agruparFuncionarioPorFuncao() {
        return funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    public void imprimeFuncionariosPorFuncao() {
        Map<Funcao, List<Funcionario>> porFuncao = agruparFuncionarioPorFuncao();

        porFuncao.forEach((funcao, funcionarios) -> {
            System.out.println("--------------------------");
            System.out.println(funcao);
            funcionarios.forEach(funcionario -> {
                System.out.println(funcionario.getNome());
            });
        });

    }

    private void importarFuncionarios() {
        funcionarios.addAll(List.of(new Funcionario("Maria", LocalDate.of(2000, Month.OCTOBER, 18), new BigDecimal("2009.44"), Funcao.OPERADOR),
                new Funcionario("João", LocalDate.of(1990, Month.MAY, 12), new BigDecimal("2284.38"), Funcao.OPERADOR),
                new Funcionario("Caio", LocalDate.of(1961, Month.MAY, 2), new BigDecimal("9836.14"), Funcao.COORDENADOR),
                new Funcionario("Miguel", LocalDate.of(1988, Month.OCTOBER, 14), new BigDecimal("19119.88"), Funcao.DIRETOR),
                new Funcionario("Alice", LocalDate.of(1995, Month.JANUARY, 5), new BigDecimal("2234.68"), Funcao.RECEPCIONISTA),
                new Funcionario("Heitor", LocalDate.of(1999, Month.NOVEMBER, 19), new BigDecimal("1582.72"), Funcao.OPERADOR),
                new Funcionario("Arthur", LocalDate.of(1993, Month.FEBRUARY, 28), new BigDecimal("4071.84"), Funcao.CONTADOR),
                new Funcionario("Laura", LocalDate.of(1994, Month.JULY, 8), new BigDecimal("3017.45"), Funcao.GERENTE),
                new Funcionario("Heloísa", LocalDate.of(2003, Month.MAY, 24), new BigDecimal("1606.85"), Funcao.ELETRICISTA),
                new Funcionario("Helena", LocalDate.of(1996, Month.SEPTEMBER, 2), new BigDecimal("1606.85"), Funcao.GERENTE)));
    }

    public void adicionarUmFuncionario(Funcionario funcionario) {
        if (funcionarios.contains(funcionario)) {
            throw new FuncionarioExistenteException("Este funcionario já foi adicionado.");
        }
        this.funcionarios.add(funcionario);
    }

    public void removerUmFuncionario(String nome) {
        boolean exists = funcionarios.stream().noneMatch(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
        if (exists) {
            throw new FuncionarioNaoExistente("Funcionário não encontrado.");
        }
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public void imprimeTodosFuncionarios() {
        funcionarios.forEach(funcionario -> {
            System.out.println(funcionario.toString());
        });
    }

}

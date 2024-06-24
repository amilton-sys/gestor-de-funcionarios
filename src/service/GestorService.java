package service;

import exception.FuncionarioExistenteException;
import exception.FuncionarioNaoExistente;
import model.Funcao;
import model.Funcionario;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class GestorService {
    private List<Funcionario> funcionarios;

    public GestorService() {
        this.funcionarios = new ArrayList<>();
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

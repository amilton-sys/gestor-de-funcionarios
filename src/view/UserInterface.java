package view;

import executor.*;
import service.GestorService;
import utils.Formater;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final CommandExecutor commandExecutor;
    private final GestorService gestorService;

    public UserInterface(GestorService gestorService) {
        this.commandExecutor = new CommandExecutor();
        this.gestorService = gestorService;
        Locale locale = new Locale("pt", "BR");
        this.scanner = new Scanner(System.in).useLocale(locale);
    }

    private void executeCommand(Command command) {
        commandExecutor.executeCommand(command);
    }

    public void start() {
        processarOpcao();
    }


    private int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    private void processarOpcao() {
        boolean sair = true;
        while (sair) {
            menu();
            int opcao = lerOpcao();
            switch (opcao) {
                case 1 -> executeCommand(new MostrarTodosFormatadosCommand(this, gestorService));
                case 2 -> executeCommand(new AdicionaFuncionarioCommand(this, gestorService));
                case 3 -> executeCommand(new RemoverFuncionarioCommand(this, gestorService));
                case 4 -> executeCommand(new AdicionarAumentoCommand(this, gestorService));
                case 5 -> executeCommand(new MostrarAgrupadosPorFuncaoCommand(this, gestorService));
                case 6 -> executeCommand(new MostrarAniversariantesCommand(this, gestorService));
                case 7 -> executeCommand(new MostrarMaiorIdadeCommand(this, gestorService));
                case 8 -> executeCommand(new MostrarEmOdermAlfabeticaCommand(this, gestorService));
                case 9 -> executeCommand(new MostrarTotalSalarioCommand(this, gestorService));
                case 10 -> executeCommand(new MostrarSalariosMinimosCommand(this, gestorService));
                case 11 -> sair = false;
            }
        }
    }

    public String askForString(String message) {
        System.out.println(message);
        return scanner.next();
    }

    public String askForStringTemplate() {
        System.out.println("Quer sair da visuzalização? (S)");
        return scanner.next();
    }

    public LocalDate askForDate(String message) {
        System.out.println(message);
        String dateInput = scanner.next();
        return Formater.toDate(dateInput);
    }

    public BigDecimal askForBigDecimal(String message) {
        System.out.println(message);
        String salaryInput = scanner.next();
        return Formater.toBigDecimal(salaryInput);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public static void menu() {
        System.out.println("""
                -----------------------------------------------
                1 ->  Listar todos os funcionários
                2 ->  Adicionar funcionário
                3 ->  Remover funcionário
                4 ->  Adicionar aumento de 10% aos funcionarios
                5 ->  Listar funcionários agrupados por função"
                6 ->  Listar funcionários aniversáriantes
                7 ->  Listar funcionários com maior idade
                8 ->  Listar funcionários em ordem alfabetica
                9 ->  Listar total dos salários dos funcionários
                10 -> Listar quantos salários mínimos ganha cada funcionário.
                11 -> Sair
                ------------------------------------------------
                """);
    }
}

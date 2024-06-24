package executor;

import model.Funcao;
import model.Funcionario;
import service.GestorService;
import view.UserInterface;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AdicionaFuncionarioCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public AdicionaFuncionarioCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }

    @Override
    public void execute() {
        String nome = ui.askForString("Digite o nome:");
        LocalDate dataNascimento = ui.askForDate("Digite sua data de nascimento no formato dd/mm/yyyy:");
        BigDecimal salario = ui.askForBigDecimal("Digite o salário:");
        String funcaoString = ui.askForString("Digite a sua função:");
        Funcao funcaoFormatada = Funcao.getFuncao(funcaoString);
        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcaoFormatada);
        gestorService.adicionarUmFuncionario(funcionario);
        gestorService.imprimeTodosFuncionarios();
    }
}

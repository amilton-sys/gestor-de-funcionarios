package executor;

import exception.FuncionarioNaoExistente;
import service.GestorService;
import view.UserInterface;

public class RemoverFuncionarioCommand implements Command{
    private final UserInterface ui;
    private final GestorService gestorService;

    public RemoverFuncionarioCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }
    @Override
    public void execute() {
        try{
            String nome = ui.askForString("Digite o nome do funcionário:");
            gestorService.removerUmFuncionario(nome);
            gestorService.imprimeTodosFuncionarios();
        }catch (FuncionarioNaoExistente e){
            System.out.println(e.getMessage());
        }
    }
}

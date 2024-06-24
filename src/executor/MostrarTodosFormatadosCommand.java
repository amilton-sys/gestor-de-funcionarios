package executor;

import service.GestorService;
import view.UserInterface;


public class MostrarTodosFormatadosCommand  implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public MostrarTodosFormatadosCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }
    @Override
    public void execute() {
        String command;
        do  {
            gestorService.imprimeTodosFuncionarios();
            command = ui.askForString("Quer sair da visuzalização? (S)");
        }while (!command.equalsIgnoreCase("S"));
    }
}

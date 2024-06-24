package executor;

import service.GestorService;
import view.UserInterface;

public class MostrarMaiorIdadeCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public MostrarMaiorIdadeCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }

    @Override
    public void execute() {
        String command;
        do {
            gestorService.imprimeFuncionariosMaiorIdade();
            command = ui.askForStringTemplate();
        } while (!command.equalsIgnoreCase("S"));
    }
}

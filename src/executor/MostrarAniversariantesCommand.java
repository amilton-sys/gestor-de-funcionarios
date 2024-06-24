package executor;

import service.GestorService;
import view.UserInterface;


public class MostrarAniversariantesCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public MostrarAniversariantesCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }

    @Override
    public void execute() {
        String command;
        do {
            gestorService.imprimeFuncionariosAniversariantes();
            command = ui.askForStringTemplate();
        } while (!command.equalsIgnoreCase("S"));
    }
}

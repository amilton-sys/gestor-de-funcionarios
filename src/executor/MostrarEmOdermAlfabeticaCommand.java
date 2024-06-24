package executor;

import service.GestorService;
import view.UserInterface;

public class MostrarEmOdermAlfabeticaCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public MostrarEmOdermAlfabeticaCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }
    @Override
    public void execute() {
        String command;
        do {
            gestorService.imprimeFuncionarioOrdemAlfabetica();
            command = ui.askForStringTemplate();
        } while (!command.equalsIgnoreCase("S"));
    }
}

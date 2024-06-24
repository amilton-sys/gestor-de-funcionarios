package executor;

import service.GestorService;
import view.UserInterface;


public class AdicionarAumentoCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public AdicionarAumentoCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }

    @Override
    public void execute() {
        String command;
        do {
            gestorService.aplicarAumento();
            ui.showMessage("Aumento aplicado a todos os funcionários.");
            gestorService.imprimeTodosFuncionarios();
            command = ui.askForStringTemplate();
        } while (!command.equalsIgnoreCase("S"));
    }
}

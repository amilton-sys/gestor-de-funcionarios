package executor;


import service.GestorService;
import view.UserInterface;

public class MostrarTotalSalarioCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public MostrarTotalSalarioCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }

    @Override
    public void execute() {
        String command;
        do {
            gestorService.imprimirTotalSalariosFuncionarios();
            command = ui.askForStringTemplate();
        } while (!command.equalsIgnoreCase("S"));
    }
}

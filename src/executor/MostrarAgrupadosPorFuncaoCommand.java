package executor;

import service.GestorService;
import view.UserInterface;


public class MostrarAgrupadosPorFuncaoCommand implements Command {
    private final UserInterface ui;
    private final GestorService gestorService;

    public MostrarAgrupadosPorFuncaoCommand(UserInterface ui, GestorService gestorService) {
        this.ui = ui;
        this.gestorService = gestorService;
    }
    @Override
    public void execute() {
        String command;
        do  {
            gestorService.imprimeFuncionariosPorFuncao();
            command = ui.askForStringTemplate();
        }while (!command.equalsIgnoreCase("S"));
    }
}

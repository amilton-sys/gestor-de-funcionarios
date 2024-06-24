import service.GestorService;
import view.UserInterface;

public class GestorConsoleApplication {
    public static void main(String[] args) {
        GestorService service = new GestorService();
        UserInterface ui = new UserInterface(service);
        ui.start();
    }

}
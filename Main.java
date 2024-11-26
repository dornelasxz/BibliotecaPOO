import controller.BibliotecaController;
import model.Biblioteca;
import view.BibliotecaView;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        BibliotecaView view = new BibliotecaView();
        new BibliotecaController(biblioteca, view);
    }
}
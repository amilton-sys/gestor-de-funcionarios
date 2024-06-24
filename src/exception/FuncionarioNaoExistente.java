package exception;

public class FuncionarioNaoExistente extends RuntimeException {
    public FuncionarioNaoExistente(String message) {
        super(message);
    }
}

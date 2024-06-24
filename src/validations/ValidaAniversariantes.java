package validations;

import model.Funcionario;

import java.time.Month;

public class ValidaAniversariantes {
    public static void validar(Funcionario funcionario) {
        var aniversariantes = funcionario.getDataNascimento().getMonth().equals(Month.OCTOBER) || funcionario.getDataNascimento().getMonth().equals(Month.DECEMBER);
        if (aniversariantes) {
            System.out.println(funcionario.getNome());
            System.out.println(funcionario.pegaIdade());
        }
    }
}

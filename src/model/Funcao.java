package model;

public enum Funcao {
    OPERADOR("Operador"),
    COORDENADOR("Coordenador"),
    DIRETOR("Diretor"),
    RECEPCIONISTA("Recepcionista"),
    CONTADOR("Contador"),
    GERENTE("Gerente"),
    ELETRICISTA("Eletricista");

    private String funcao;

    Funcao(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public static Funcao getFuncao(String funcao) {
        for (Funcao value : values()) {
            if (value.getFuncao().equalsIgnoreCase(funcao)) {
                return  value;
            }
        }
        return null;
    }
}

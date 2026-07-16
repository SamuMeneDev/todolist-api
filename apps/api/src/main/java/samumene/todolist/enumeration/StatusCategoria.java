package samumene.todolist.enumeration;

/**
 * Valores possíveis de Status de uma categoria
 */
public enum StatusCategoria {
    ATIVA,
    DESATIVA;
    /**
     * String usada para validação com Expressões Regulares (RegExp).
     */
    public static final String statusCategoriaRegExp = "ATIVA|DESATIVA";
}

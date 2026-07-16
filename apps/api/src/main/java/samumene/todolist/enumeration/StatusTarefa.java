package samumene.todolist.enumeration;

/**
 * Valores possíveis de Status de uma tarefa.
 */
public enum StatusTarefa {
    PENDENTE,
    CONCLUIDA;
    /**
     * String usada para validação com Expressões Regulares (RegExp).
     */
    public static final String statusTarefaRegExp = "PENDENTE|CONCLUIDA";
}

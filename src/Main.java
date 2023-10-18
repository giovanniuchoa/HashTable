public class Main {
    public static void main(String[] args) {
        TabelaHash<Integer, Integer> tabela = new TabelaHash<>();

        // Inserção de elementos
        tabela.inserir(1, 25);
        tabela.inserir(2, 30);
        tabela.imprimirTabela();


        tabela.inserir(3, 22);
        tabela.inserir(9, 30);
         tabela.imprimirTabela();

        
    }
    
}

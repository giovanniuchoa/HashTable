import java.util.ArrayList;

public class TabelaHash<C, V> {
    private  double fatorCarga = 0.75;
    private  int capacidade = 4;

    private ArrayList<Valores<C, V>>[] tabela;
    private int tamanho;

    public TabelaHash() {
        tabela = new ArrayList[capacidade];
        tamanho = 0;
    }

    private int calcularHash(C chave) {

        if (chave instanceof Integer) {
            return ((Integer) chave) % tabela.length;
            
        } else if (chave instanceof String) {
            int hash = 0;
            char[] chave2 = ((String)chave).toCharArray();
            for (char c : chave2) {
                hash = (31 * hash + c) % tabela.length;
            }
            return hash;
        }
        System.out.println("Chave não é int ou string");
        System.exit(0);
        return 0; 
    }

    public void inserir(C chave, V valor) {
        int indice = calcularHash(chave);
        if (tabela[indice] == null) {
            tabela[indice] = new ArrayList<>();
        }
        for (Valores<C, V> Valores : tabela[indice]) {
            if (Valores.getChave().equals(chave)) {
                Valores.setValor(valor);
                return;
            }
        }
        tabela[indice].add(new Valores<>(chave, valor));
        tamanho++;

        if ((1.0 * tamanho) / tabela.length >= fatorCarga) {
            redimensionarTabela();
        }
    }

    public V buscar(C chave) {
        long startTime = System.currentTimeMillis(); // Registra o tempo no início da função
        
        int indice = calcularHash(chave);
        if (tabela[indice] != null) {
            for (Valores<C, V> Valores : tabela[indice]) {
                if (Valores.getChave().equals(chave)) {
                    long endTime = System.currentTimeMillis(); // Registra o tempo no final da função
                    long executionTime = endTime - startTime; // Calcula o tempo de execução
                    System.out.println("Tempo de execução: " + executionTime + " milissegundos");
                    return Valores.getValor();
                }
            }
        }
        long endTime = System.currentTimeMillis(); // Registra o tempo no final da função
        long executionTime = endTime - startTime; // Calcula o tempo de execução
        System.out.println("Chave não encontrada. Tempo de execução: " + executionTime + " milissegundos");
        return null;
    }

    public V remover(C chave) {
        int indice = calcularHash(chave);
        if (tabela[indice] != null) {
            for (Valores<C, V> Valores : tabela[indice]) {
                if (Valores.getChave().equals(chave)) {
                    tabela[indice].remove(Valores);
                    tamanho--;
                    return Valores.getValor();
                }
            }
        }
        return null;
    }

    private void redimensionarTabela() {
        int novaCapacidade = tabela.length * 2;
        ArrayList<Valores<C, V>>[] novaTabela = new ArrayList[novaCapacidade];

        for (ArrayList<Valores<C, V>> valor : tabela) {
            if (valor != null) {
                for (Valores<C, V> Valores : valor) {
                    int indice = calcularHash(Valores.getChave());
                    if (novaTabela[indice] == null) {
                        novaTabela[indice] = new ArrayList<>();
                    }
                    novaTabela[indice].add(Valores);
                }
            }
        }
        tabela = novaTabela;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void imprimirTabela() {
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                for (Valores<C, V> entrada : tabela[i]) {
                    System.out.print(i + ": " + "Chave: " + entrada.getChave() + " -  Valor: " + entrada.getValor()+"," );
                }
                System.out.println();
            }else{
                System.out.println(i+" vazio");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }
    
    
   
}

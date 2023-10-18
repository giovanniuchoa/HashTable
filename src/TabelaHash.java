import java.util.ArrayList;

public class TabelaHash<C, V> {
    private double fatorCarga = 0.75;
    private int capacidade = 4;

    private ArrayList<Valores<C, V>>[] tabela;
    private int tamanho;

    public TabelaHash() {
        tabela = new ArrayList[capacidade];

        for(int i = 0 ;i<tabela.length;i++){
            tabela[i] = new ArrayList<>();
        }
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
        for (Valores<C, V> entrada : tabela[indice]) {
            if (entrada.getChave().equals(chave)) {
                entrada.setValor(valor);
                return;
            }
        }
        tabela[indice].add(new Valores<>(chave, valor));
        tamanho++;
        System.out.println(chave + " Inserido");

        if ((1.0 * tamanho) / tabela.length >= fatorCarga) {
            redimensionarTabela();
        }
    }

    public V buscar(C chave) {
        long startTime = System.currentTimeMillis();
        int indice = calcularHash(chave);
        if (tabela[indice] != null) {
            for (Valores<C, V> entrada : tabela[indice]) {
                if (entrada.getChave().equals(chave)) {
                    long endTime = System.currentTimeMillis();
                    long executionTime = endTime - startTime;
                    System.out.println(chave + " Encontrada. Tempo de execução: " + executionTime + " milissegundos");
                    return entrada.getValor();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Chave não encontrada. Tempo de execução: " + executionTime + " milissegundos");
        return null;
    }

    public V remover(C chave) {
        int indice = calcularHash(chave);
        if (tabela[indice] != null) {
            for (Valores<C, V> entrada : tabela[indice]) {
                if (entrada.getChave().equals(chave)) {
                    tabela[indice].remove(entrada);
                    tamanho--;
                    return entrada.getValor();
                }
            }
        }
        return null;
    }

    private void redimensionarTabela() {
        int novaCapacidade = tabela.length * 2;
        ArrayList<Valores<C, V>>[] novaTabela = new ArrayList[novaCapacidade];

        for (ArrayList<Valores<C, V>> balde : tabela) {
            if (balde != null) {
                for (Valores<C, V> entrada : balde) {
                    int indice = calcularHash(entrada.getChave());
                    if (novaTabela[indice] == null) {
                        novaTabela[indice] = new ArrayList<>();
                    }
                    novaTabela[indice].add(entrada);
                }
            }
        }
        tabela = novaTabela;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void imprimirTabela() {
        System.out.println("-------------------Print Table-----------------------------");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                for (Valores<C, V> entrada : tabela[i]) {
                    System.out.print(i + ": Chave: " + entrada.getChave() + " - Valor: " + entrada.getValor() + ", ");
                }
                System.out.println();
            } else {
                System.out.println(i + " vazio");
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }
}

public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> hashTable = new HashTable<>(16, 0.75);

        // Inserção
        hashTable.put(1, "Joao");
        System.out.println("Insere (1) - " + hashTable.get(1));
        hashTable.put(2, "Giovanni");
        System.out.println("Insere (2) - " + hashTable.get(2));
        hashTable.put(3, "Terezin");
        System.out.println("Insere (3) - " + hashTable.get(3));

        // Busca
        System.out.println("Busca (2): " + hashTable.get(2)); 

        // Remoção
        System.out.println("Removido (1): " + hashTable.remove(1)); 

        // Teste de eficiência
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            hashTable.put(i, "Value" + i);
        }
        long endTime = System.nanoTime();
        System.out.println("Tempo de inserção dos elementos: " + (endTime - startTime) / 1e9 + " segundos");
    }
}

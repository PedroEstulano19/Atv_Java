import java.util.*;

public class Atv2 {

    private static Map<String, Map<String, Integer>> grafo = new HashMap<>();

    public static void main(String[] args) {

        configurarGrafo();

        Map<String, Integer> distancias = calcularDijkstra("A");

        distancias.forEach((vertice, distancia) -> System.out.println("Distância de A para " + vertice + ": " + distancia));
    }

    private static void configurarGrafo() {
       
        grafo.put("A", Map.of("B", 2, "C", 4));
        grafo.put("B", Map.of("A", 2, "C", 1, "D", 7));
        grafo.put("C", Map.of("A", 4, "B", 1, "D", 3));
        grafo.put("D", Map.of("B", 7, "C", 3));
    }

    private static Map<String, Integer> calcularDijkstra(String origem) {
        Map<String, Integer> distancias = new HashMap<>();
        PriorityQueue<Vertice> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(vertice -> vertice.distancia));
        Set<String> visitados = new HashSet<>();

        for (String vertice : grafo.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        filaPrioridade.add(new Vertice(origem, 0));

        while (!filaPrioridade.isEmpty()) {
            Vertice verticeAtual = filaPrioridade.poll();
            String verticeAtualNome = verticeAtual.nome;

            if (!visitados.contains(verticeAtualNome)) {
                visitados.add(verticeAtualNome);
                Map<String, Integer> vizinhos = grafo.get(verticeAtualNome);

                for (Map.Entry<String, Integer> entrada : vizinhos.entrySet()) {
                    String vizinho = entrada.getKey();
                    int pesoAresta = entrada.getValue();

                    if (!visitados.contains(vizinho)) {
                        int novaDistancia = distancias.get(verticeAtualNome) + pesoAresta;
                        if (novaDistancia < distancias.get(vizinho)) {
                            distancias.put(vizinho, novaDistancia);
                            filaPrioridade.add(new Vertice(vizinho, novaDistancia));
                        }
                    }
                }
            }
        }

        return distancias;
    }

    static class Vertice {
        String nome;
        int distancia;

        Vertice(String nome, int distancia) {
            this.nome = nome;
            this.distancia = distancia;
        }
    }
}

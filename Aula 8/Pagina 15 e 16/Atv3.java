import java.util.*;

public class Atv3 {
    
    static class Grafo {
        private final int vertices;
        private final List<List<Integer>> adjList;

        public Grafo(int vertices) {
            this.vertices = vertices;
            this.adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void adicionarAresta(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        public int[] calcularGraus() {
            int[] graus = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                graus[i] = adjList.get(i).size();
            }
            return graus;
        }

        public boolean possuiCicloDeComprimento3() {
            for (int i = 0; i < vertices; i++) {
                for (int j : adjList.get(i)) {
                    for (int k : adjList.get(j)) {
                        if (k != i && adjList.get(k).contains(i)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    public static boolean saoIsomorfos(Grafo g1, Grafo g2) {
        // Comparar graus dos vértices
        int[] grausG1 = g1.calcularGraus();
        int[] grausG2 = g2.calcularGraus();

        Arrays.sort(grausG1);
        Arrays.sort(grausG2);

        if (!Arrays.equals(grausG1, grausG2)) {
            return false; // Graus dos vértices são diferentes
        }

        // Comparar presença de ciclos de comprimento 3
        boolean ciclo3G1 = g1.possuiCicloDeComprimento3();
        boolean ciclo3G2 = g2.possuiCicloDeComprimento3();

        return ciclo3G1 == ciclo3G2; // Devem ter a mesma estrutura de ciclo
    }

    public static void main(String[] args) {
        // Grafo 1.11
        Grafo grafo1 = new Grafo(4);
        grafo1.adicionarAresta(0, 1);
        grafo1.adicionarAresta(0, 2);
        grafo1.adicionarAresta(0, 3);
        grafo1.adicionarAresta(1, 2);
        grafo1.adicionarAresta(1, 3);
        grafo1.adicionarAresta(2, 3);

        // Grafo 1.12
        Grafo grafo2 = new Grafo(4);
        grafo2.adicionarAresta(0, 1);
        grafo2.adicionarAresta(1, 2);
        grafo2.adicionarAresta(2, 3);
        grafo2.adicionarAresta(3, 0);

        // Verificar se são isomorfos
        if (saoIsomorfos(grafo1, grafo2)) {
            System.out.println("Os grafos 1.11 e 1.12 são isomorfos.");
        } else {
            System.out.println("Os grafos 1.11 e 1.12 NÃO são isomorfos.");
        }
    }
}

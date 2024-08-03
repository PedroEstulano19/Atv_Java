import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Atv1{

    private static Map<Integer, Set<Integer>> grafo1 = new HashMap<>();
    private static Map<Integer, Set<Integer>> grafo2 = new HashMap<>();

    private static Map<Integer, Integer> correspondencia = new HashMap<>();

    public static void main(String[] args) {
        inicializarGrafos();
        inicializarCorrespondencia();

        if (verificarIsomorfismo()) {
            System.out.println("A correspondência proposta preserva o isomorfismo.");
        } else {
            System.out.println("A correspondência proposta NÃO preserva o isomorfismo.");
        }
    }

    private static void inicializarGrafos() {
        grafo1.put(1, Set.of(2, 3));
        grafo1.put(2, Set.of(1, 4));
        grafo1.put(3, Set.of(1, 4));
        grafo1.put(4, Set.of(2, 3));

        grafo2.put(1, Set.of(2, 3));
        grafo2.put(2, Set.of(1, 4));
        grafo2.put(3, Set.of(1, 4));
        grafo2.put(4, Set.of(2, 3));
    }

    private static void inicializarCorrespondencia() {
        correspondencia.put(1, 1);
        correspondencia.put(2, 2);
        correspondencia.put(3, 3);
        correspondencia.put(4, 4);
    }

    private static boolean verificarIsomorfismo() {
        for (Integer verticeGrafo1 : grafo1.keySet()) {
            Integer verticeCorrespondente = correspondencia.get(verticeGrafo1);
            if (verticeCorrespondente == null) {
                return false;
            }
            Set<Integer> vizinhosGrafo1 = grafo1.get(verticeGrafo1);
            Set<Integer> vizinhosGrafo2 = grafo2.get(verticeCorrespondente);
            
            Set<Integer> vizinhosMapeados = new HashSet<>();
            for (Integer vizinho : vizinhosGrafo1) {
                Integer vizinhoMapeado = correspondencia.get(vizinho);
                if (vizinhoMapeado != null) {
                    vizinhosMapeados.add(vizinhoMapeado);
                }
            }

            if (!vizinhosMapeados.equals(vizinhosGrafo2)) {
                return false;
            }
        }
        return true;
    }
}

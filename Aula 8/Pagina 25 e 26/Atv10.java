import java.util.ArrayList;
import java.util.List;

public class Atv10 {

    public static void main(String[] args) {
        int[][] matrizAdj = {
            {0, 1, 0, 1, 1},
            {1, 0, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 1, 0}
        };

        List<List<Integer>> listaAdj = converterParaListaAdjacencia(matrizAdj);

        System.out.println("Representação do grafo (Lista de Adjacência):");
        exibirListaAdjacencia(listaAdj);
    }

    private static List<List<Integer>> converterParaListaAdjacencia(int[][] matriz) {
        List<List<Integer>> lista = new ArrayList<>();
        
        for (int i = 0; i < matriz.length; i++) {
            List<Integer> adjacentes = new ArrayList<>();
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    adjacentes.add(j);
                }
            }
            lista.add(adjacentes);
        }
        return lista;
    }

    private static void exibirListaAdjacencia(List<List<Integer>> listaAdj) {
        for (int i = 0; i < listaAdj.size(); i++) {
            System.out.println("Vértice " + i + " -> " + listaAdj.get(i));
        }
    }
}

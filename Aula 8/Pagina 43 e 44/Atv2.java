import java.util.Arrays;

public class Atv2 {

    public static void main(String[] args) {
        int[][] matrizDistancias = {
            {0, 10, 0, 30, 100},
            {10, 0, 50, 0, 0},
            {0, 50, 0, 20, 10},
            {30, 0, 20, 0, 60},
            {100, 0, 10, 60, 0}
        };

        Atv2 instancia = new Atv2();
        instancia.calcularDistancias(matrizDistancias, 0);
    }

    public void calcularDistancias(int[][] matriz, int origem) {
        int numVertices = matriz.length;
        int[] distancias = new int[numVertices];
        boolean[] verticesVisitados = new boolean[numVertices];

        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[origem] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int verticeAtual = encontrarVerticeMinDistancia(distancias, verticesVisitados);
            verticesVisitados[verticeAtual] = true;

            for (int adjacente = 0; adjacente < numVertices; adjacente++) {
                if (!verticesVisitados[adjacente] && matriz[verticeAtual][adjacente] != 0
                        && distancias[verticeAtual] != Integer.MAX_VALUE
                        && distancias[verticeAtual] + matriz[verticeAtual][adjacente] < distancias[adjacente]) {
                    distancias[adjacente] = distancias[verticeAtual] + matriz[verticeAtual][adjacente];
                }
            }
        }

        exibirResultados(distancias);
    }

    public int encontrarVerticeMinDistancia(int[] distancias, boolean[] visitados) {
        int distanciaMinima = Integer.MAX_VALUE;
        int indiceMinimo = -1;

        for (int i = 0; i < distancias.length; i++) {
            if (!visitados[i] && distancias[i] < distanciaMinima) {
                distanciaMinima = distancias[i];
                indiceMinimo = i;
            }
        }

        return indiceMinimo;
    }

    public void exibirResultados(int[] distancias) {
        System.out.println("Localidade \t Distância da Mercearia");
        for (int i = 0; i < distancias.length; i++) {
            System.out.println(i + " \t\t " + distancias[i]);
        }
    }
}

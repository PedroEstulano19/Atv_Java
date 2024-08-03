import java.util.Arrays;

public class Atv1 {

    public static void main(String[] args) {
        int[][] k3 = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };
        
        int[][] k7 = {
            {0, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0}
        };
        
        int[][] complementoK3 = calcularComplemento(k3);
        int[][] complementoK7 = calcularComplemento(k7);
        
        System.out.println("Complemento de K3:");
        exibirMatriz(complementoK3);
        
        System.out.println("Complemento de K7:");
        exibirMatriz(complementoK7);
    }

    private static int[][] calcularComplemento(int[][] matriz) {
        int tamanho = matriz.length;
        int[][] matrizComplementar = new int[tamanho][tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i != j) {
                    matrizComplementar[i][j] = 1 - matriz[i][j];
                }
            }
        }
        return matrizComplementar;
    }

    private static void exibirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            System.out.println(Arrays.toString(linha));
        }
    }
}

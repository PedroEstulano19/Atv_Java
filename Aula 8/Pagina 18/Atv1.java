public class Atv1 {

    public static int calcularArestas(int vertices) {
        return vertices * (vertices - 1) / 2;
    }

    public static void main(String[] args) {
        int arestasK7 = calcularArestas(7);
        int arestasK12 = calcularArestas(12);

        System.out.println("Número de arestas em K7: " + arestasK7);
        System.out.println("Número de arestas em K12: " + arestasK12);

        int numVertices = 5; 
        int arestasKn = calcularArestas(numVertices);
        System.out.println("Número de arestas em K" + numVertices + ": " + arestasKn);
    }
}

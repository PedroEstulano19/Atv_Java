import java.util.Random;

public class AtividadeArvore {

    static class NoArvore {
        int chave;
        NoArvore esquerda, direita;

        NoArvore(int chave) {
            this.chave = chave;
            this.esquerda = null;
            this.direita = null;
        }
    }

    public static NoArvore rotacaoDireita(NoArvore no) {
        if (no == null || no.esquerda == null) return no;

        NoArvore esquerda = no.esquerda;
        no.esquerda = esquerda.direita;
        esquerda.direita = no;
        return esquerda;
    }

    public static NoArvore transformarParaLista(NoArvore no) {
        if (no == null) return null;

        NoArvore raiz = no;
        while (raiz.esquerda != null) {
            raiz = rotacaoDireita(raiz);
        }
        if (raiz.direita != null) {
            raiz.direita = transformarParaLista(raiz.direita);
        }
        return raiz;
    }

    public static NoArvore listaParaArvoreBalanceada(NoArvore no, int numNos) {
        if (numNos == 0 || no == null) return null;

        NoArvore raiz = no;
        for (int i = 0; i < numNos / 2; i++) {
            if (raiz == null) break;
            raiz = rotacaoDireita(raiz);
        }
        if (raiz != null) {
            raiz.esquerda = listaParaArvoreBalanceada(raiz.esquerda, numNos / 2);
            raiz.direita = listaParaArvoreBalanceada(raiz.direita, numNos - numNos / 2 - 1);
        }
        return raiz;
    }

    public static NoArvore balancearArvoreComDSW(NoArvore raiz) {
        raiz = transformarParaLista(raiz);
        int numNos = contarNos(raiz);
        return listaParaArvoreBalanceada(raiz, numNos);
    }

    public static int contarNos(NoArvore no) {
        if (no == null) return 0;
        return 1 + contarNos(no.esquerda) + contarNos(no.direita);
    }

    public static NoArvore inserirNaArvore(NoArvore raiz, int chave) {
        if (raiz == null) return new NoArvore(chave);
        if (chave < raiz.chave) raiz.esquerda = inserirNaArvore(raiz.esquerda, chave);
        else raiz.direita = inserirNaArvore(raiz.direita, chave);
        return raiz;
    }

    public static void imprimirEmOrdem(NoArvore no) {
        if (no != null) {
            imprimirEmOrdem(no.esquerda);
            System.out.print(no.chave + " ");
            imprimirEmOrdem(no.direita);
        }
    }

    public static NoArvore criarArvoreAleatoria(int numNos) {
        NoArvore raiz = null;
        Random rand = new Random();
        for (int i = 0; i < numNos; i++) {
            int chave = rand.nextInt(101);
            raiz = inserirNaArvore(raiz, chave);
        }
        return raiz;
    }

    public static void main(String[] args) {
        NoArvore raiz;

        // 1. Criar uma árvore com 100 números aleatórios entre 0 e 100
        raiz = criarArvoreAleatoria(100);

        System.out.println("Árvore antes do balanceamento:");
        imprimirEmOrdem(raiz);
        System.out.println();

        // 2. Adicionar 20 números à árvore
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int chave = rand.nextInt(101);
            raiz = inserirNaArvore(raiz, chave);
        }

        System.out.println("Árvore após a adição de 20 números:");
        imprimirEmOrdem(raiz);
        System.out.println();

        // 3. Balancear a árvore usando o algoritmo DSW
        raiz = balancearArvoreComDSW(raiz);

        System.out.println("Árvore após o balanceamento:");
        imprimirEmOrdem(raiz);
    }
}

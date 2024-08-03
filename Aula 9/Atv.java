import java.io.*;
import java.util.*;

public class Atv {

    public static void main(String[] args) {
        try {
            int[] numbers = loadNumbers("dados_500mil.txt");

            int[] heapSortedNumbers = Arrays.copyOf(numbers, numbers.length);
            long heapSortStart = System.nanoTime();
            performHeapSort(heapSortedNumbers);
            long heapSortEnd = System.nanoTime();
            System.out.println("Heap Sort tempo de execução: " + (heapSortEnd - heapSortStart) + " nanoseconds");

            long bstStart = System.nanoTime();
            BinaryTree bst = new BinaryTree();
            for (int number : numbers) {
                bst.add(number);
            }
            List<Integer> bstInOrder = bst.inOrderTraversal();
            long bstEnd = System.nanoTime();
            System.out.println("Arvore Binaria tempo de execução: " + (bstEnd - bstStart) + " nanoseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] loadNumbers(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Integer> numberList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                try {
                    numberList.add(Integer.parseInt(line));
                } catch (NumberFormatException ex) {
                    System.err.println("Invalid line skipped: " + line);
                }
            }
        }
        reader.close();
        return numberList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void performHeapSort(int[] array) {
        int length = array.length;

        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(array, length, i);
        }

        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int length, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < length && array[left] > array[largest]) {
            largest = left;
        }

        if (right < length && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != index) {
            int swap = array[index];
            array[index] = array[largest];
            array[largest] = swap;

            heapify(array, length, largest);
        }
    }

    static class BinaryTree {
        class TreeNode {
            int data;
            TreeNode left, right;

            public TreeNode(int value) {
                data = value;
                left = right = null;
            }
        }

        TreeNode root;

        void add(int value) {
            root = addRecursive(root, value);
        }

        private TreeNode addRecursive(TreeNode node, int value) {
            if (node == null) {
                return new TreeNode(value);
            }

            if (value < node.data) {
                node.left = addRecursive(node.left, value);
            } else if (value > node.data) {
                node.right = addRecursive(node.right, value);
            }

            return node;
        }

        List<Integer> inOrderTraversal() {
            List<Integer> result = new ArrayList<>();
            inOrderRecursive(root, result);
            return result;
        }

        private void inOrderRecursive(TreeNode node, List<Integer> result) {
            if (node != null) {
                inOrderRecursive(node.left, result);
                result.add(node.data);
                inOrderRecursive(node.right, result);
            }
        }
    }
}

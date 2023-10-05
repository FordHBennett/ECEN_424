import java.util.Arrays;
import java.util.Random;

public class MatrixMultiplier {
    static final int MATRIX_SIZE = 20;
    static final int MAX_RANDOM_VALUE = 10;
    static final int NUM_THREADS = 5;

    static int[][] matrixProd = new int[MATRIX_SIZE][MATRIX_SIZE];
    static int[][] matrix1 = new int[MATRIX_SIZE][MATRIX_SIZE];
    static int[][] matrix2 = new int[MATRIX_SIZE][MATRIX_SIZE];

    static class Multithreader implements Runnable {
        private int threadId;

        public Multithreader(int threadId) {
            this.threadId = threadId;
        }

        public void run() {
            System.out.println(Thread.currentThread());

            int startRow = threadId * (MATRIX_SIZE / NUM_THREADS);
            int endRow = (threadId + 1) * (MATRIX_SIZE / NUM_THREADS);

            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < MATRIX_SIZE; j++) {
                    for (int k = 0; k < MATRIX_SIZE; k++) {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    public static void initializeMatrix(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = random.nextInt(MAX_RANDOM_VALUE);
            }
        }
    }

    public static void main(String[] args) {
        initializeMatrix(matrix1);
        initializeMatrix(matrix2);

        System.out.println(Arrays.deepToString(matrix1));
        System.out.println(Arrays.deepToString(matrix2));

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(new Multithreader(i), "Thread - T" + (i + 1));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.deepToString(matrixProd));
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                System.out.print(matrixProd[i][j] + " ");
            }
            System.out.println();
        }
    }
}

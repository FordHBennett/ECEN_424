import java.util.Arrays;
import java.util.Random;

public class MatrixMultiplier {
    // Initialize the matrix size and the maximum random value constants
    static final int MATRIX_SIZE = 20;
    static final int MAX_RANDOM_VALUE = 10;
    static final int NUM_THREADS = 5;

    // Initialize the matrices and the result matrix
    static int[][] matrixProd = new int[MATRIX_SIZE][MATRIX_SIZE];
    static int[][] matrix1 = new int[MATRIX_SIZE][MATRIX_SIZE];
    static int[][] matrix2 = new int[MATRIX_SIZE][MATRIX_SIZE];

    static class Multithreader implements Runnable {
        private int threadId;

        // Constructor
        public Multithreader(int threadId)
        {
            this.threadId = threadId;
        }

        public void run() {
            // Print thread information for debugging
            System.out.println(Thread.currentThread());

            // Calculate the start and end rows for the thread
            int startRow = threadId * (MATRIX_SIZE / NUM_THREADS);
            int endRow = (threadId + 1) * (MATRIX_SIZE / NUM_THREADS);

            //Multiply the matrices
            for (int i = startRow; i < endRow; i++)
            {
                for (int j = 0; j < MATRIX_SIZE; j++)
                {
                    int sum = 0;
                    for (int k = 0; k < MATRIX_SIZE; k++)
                    {
                        sum += matrix1[i][k] * matrix2[k][j];
                    }
                    matrixProd[i][j] = sum; // Update the result matrix
                }
            }
        }
    }

/**
 * The function initializes a given matrix with random integer values.
 *
 * @param matrix The parameter "matrix" is a two-dimensional array of integers.
 */
    public static void initializeMatrix(int[][] matrix) {
        Random random = new Random();
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                matrix[i][j] = random.nextInt(MAX_RANDOM_VALUE);
            }
        }
    }

    public static void main(String[] args) {
        // Initialize the matrices
        initializeMatrix(matrix1);
        initializeMatrix(matrix2);

        // Print the matrices for debugging
        System.out.println("Matrix 1:");
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println("Matrix 2:");
        System.out.println(Arrays.deepToString(matrix2));

        // Create and start the threads
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++)
        {
            threads[i] = new Thread(new Multithreader(i), "Thread - T" + (i + 1));
            threads[i].start();
        }

        // Wait for the threads to finish
        try
        {
            for (Thread thread : threads)
            {
                thread.join();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // Print the result matrix
        System.out.println("Result Matrix:");
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                System.out.print(matrixProd[i][j] + " ");
            }
            System.out.println();
        }
    }
}

import java.util.Arrays;
import java.util.Random;

public class MatrixMultiplier {
    //Initialize the matrix size and the max random value
    static final int MATRIX_SIZE = 20;
    static final int MAX_RANDOM_VALUE = 10;
    static final int NUM_THREADS = 5;

    //Initialize the matrices
    static int[][] matrixProd = new int[MATRIX_SIZE][MATRIX_SIZE];
    static int[][] matrix1 = new int[MATRIX_SIZE][MATRIX_SIZE];
    static int[][] matrix2 = new int[MATRIX_SIZE][MATRIX_SIZE];


/**
 * The Multithreader class is a Java class that implements the Runnable interface and performs matrix
 * multiplication in parallel using multiple threads.
 */
    static class Multithreader implements Runnable
    {
        private int threadId;

        //Constructor
        public Multithreader(int threadId)
        {
            this.threadId = threadId;
        }

        //Run method
        public void run() {
            //Print the current thread
            System.out.println(Thread.currentThread());

            //Calculate the start and end rows for the current thread
            int startRow = threadId * (MATRIX_SIZE / NUM_THREADS);
            int endRow = (threadId + 1) * (MATRIX_SIZE / NUM_THREADS);

            //Perform matrix multiplication
            for (int i = startRow; i < endRow; i++)
            {
                for (int j = 0; j < MATRIX_SIZE; j++)
                {
                    for (int k = 0; k < MATRIX_SIZE; k++)
                    {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }


/**
 * The function initializes a matrix with random values.
 *
 * @param matrix The parameter "matrix" is a two-dimensional array of integers.
 */
    public static void initializeMatrix(int[][] matrix) {
        //Initialize the matrix with random values
        Random random = new Random();

        //Iterate through the matrix and assign random values
        for (int i = 0; i < MATRIX_SIZE; i++)
        {
            for (int j = 0; j < MATRIX_SIZE; j++)
            {
                matrix[i][j] = random.nextInt(MAX_RANDOM_VALUE);
            }
        }
    }

    public static void main(String[] args) {
        //Initialize the matrices
        initializeMatrix(matrix1);
        initializeMatrix(matrix2);

        //Print the matrices
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println(Arrays.deepToString(matrix2));

        //Create the threads
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++)
        {
            //Create the thread
            threads[i] = new Thread(new Multithreader(i), "Thread - T" + (i + 1));
            threads[i].start();
        }

        //Join the threads
        try {
            for (Thread thread : threads)
            {
                thread.join();
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //Print the product matrix
        System.out.println(Arrays.deepToString(matrixProd));

        //Print the product matrix
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

import java.util. *;

public class Matrix_multiplier {
    static int width = 20;
    static int length = 20;
    static int[][] matrixProd = new int[length][width];
    static int[][] matrix1 = new int[length][width];
    static int[][] matrix2 = new int[length][width];
    static int divisions = 5;
    static int divSize = length/divisions;
    static class Multithreader1 implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread());
            for (int i = 0; i < divSize; i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < width; k++) {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    static class Multithreader2 implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread());
            for (int i = divSize; i < (2*divSize); i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < width; k++) {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    static class Multithreader3 implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread());
            for (int i = 2*divSize; i < (3*divSize); i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < width; k++) {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    static class Multithreader4 implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread());
            for (int i = 3*divSize; i < (4*divSize); i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < width; k++) {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    static class Multithreader5 implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread());
            for (int i = 4*divSize; i < (5*divSize); i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < width; k++) {
                        matrixProd[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Random rNum = new Random();
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1.length; j++) {
                matrix1[i][j] = rNum.nextInt(10);

            }
        }
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                matrix2[i][j] = rNum.nextInt(10);

            }
        }
        System.out.println(Arrays.deepToString(matrix1));
        System.out.println(Arrays.deepToString(matrix2));

        Thread t1 = new Thread(new Multithreader1(), "Thread - T1");
        Thread t2 = new Thread(new Multithreader2(), "Thread - T2");
        Thread t3 = new Thread(new Multithreader3(), "Thread - T3");
        Thread t4 = new Thread(new Multithreader4(), "Thread - T4");
        Thread t5 = new Thread(new Multithreader5(), "Thread - T5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
        }
        try {
            t3.join();
        } catch (InterruptedException e) {
        }
        try {
            t4.join();
        } catch (InterruptedException e) {
        }
        try {
            t5.join();
        } catch (InterruptedException e) {
        }

        System.out.println(Arrays.deepToString(matrixProd));
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrixProd[i][j] + " ");
            }
            System.out.println();
        }
    }
}

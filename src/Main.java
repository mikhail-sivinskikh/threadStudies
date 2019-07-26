public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        new Thread(new thread1()).run();
    }

    static class thread1 extends Thread {
        @Override
        public void run() {
            method1();
            method2();

        }
    }

    public static void method1() {
        long a = System.currentTimeMillis();
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }

    public static void method2() {
        long a = System.currentTimeMillis();
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(arr, 0, arr1, 0, HALF);
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(arr, HALF, arr2, 0, HALF);
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);
        t1.start();
        t2.start();
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }
}

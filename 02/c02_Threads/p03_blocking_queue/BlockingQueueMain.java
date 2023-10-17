package p03_blocking_queue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

final class BlockingQueueMain {
    private static final int FILE_QUEUE_SIZE = 10; // OGRANICENJE STRUKTURE
    private static final int THREADS_NUM = 5; // OGRANICENJE BROJA NITI

    public static void main(String[] args) {
        // NAPRAVITI BLOKIRAJUCI RED

        /*
        Path base = Paths.get("/home/natasa/Desktop/Fakultet/4_god/7_sem/rm/02/c02_Threads/p02_bank");
        BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
        FileTreeWalkerRunnable ftw = new FileTreeWalkerRunnable(base, queue);
        */

        // Scan user input for starting directory and keyword to search for
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter base dir:");
        String dir = sc.nextLine();
        System.out.println("Enter keyword:");
        String keyword = sc.nextLine();
        sc.close();

        // We are implementing a variant of the popular design pattern:
        // https://en.wikipedia.org/wiki/Thread_pool

        // We will create a file tree walker which will fill our task
        // queue with paths to files that should be searched for the
        // given keyword. The worker threads will (in parallel, while
        // the directory traversal is taking place) take files and
        // search them.

        // Blocking queue is a collection which is concurrrent and
        // blocking (if you want to take an element from the queue
        // and it happens to be empty, you can opt to fail and
        // continue your execution or wait for other threads to
        // add elements in the queue so that you can take them).
        BlockingQueue<Path> fileQueue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        // Directory traversal thread
        FileTreeWalkerRunnable ftw = new FileTreeWalkerRunnable(Paths.get(dir), fileQueue);
        new Thread(ftw).start();

        // Worker threads
        /* STARTUJEMO NII */
        for (int i = 0; i < THREADS_NUM; i++)
            new Thread(new SearchFileRunnable(fileQueue, keyword)).start();
    }

}

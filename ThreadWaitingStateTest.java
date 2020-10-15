package draft;


public class ThreadWaitingStateTest extends Thread {

    public void run() {

        try {

            synchronized (this) {

                wait();

            }

        } catch (InterruptedException e) {

            System.err.print("ошибка потока");

        }

    }

    public static void main(String[] args) {

        try {

            Thread thread = new ThreadWaitingStateTest();

// NEW – поток создан, но ещё не запущен

            System.out.println("0: " + thread.getState());

            thread.start();

// RUNNABLE – поток запущен

            System.out.println("1: " + thread.getState());


            synchronized (thread) {

                Thread.sleep(1000);

// BLOCKED – because thread attempting to acquire a lock

                System.out.println("2: " + thread.getState());

            }

            Thread.sleep(1000);

// WAITING – метод wait() внутри synchronized

// останавил поток и освободил блокировку

            System.out.println("3: " + thread.getState());

            thread.interrupt();

            if (thread.isInterrupted())
                System.out.println("Поток thread завершает свой поток " + thread.getState());

            System.out.println("5: " + thread.getState());

        } catch (InterruptedException e) {

            System.err.print("ошибка потока");

        }

    }

}


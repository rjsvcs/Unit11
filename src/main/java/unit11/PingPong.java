package unit11;

public class PingPong implements Runnable {
    public static final Object LOCK = new Object();

    private final String pingOrPong;

    public PingPong(boolean ping) {
        this.pingOrPong = ping ? "Ping!" : "Pong!";
    }

    @Override
    public void run() {
        while (true) {
            synchronized (LOCK) {
                System.out.println(pingOrPong);
                try {
                    Thread.sleep(500);
                    // LOCK.notify();
                    // LOCK.wait();
                } catch (InterruptedException e) {}
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        synchronized (LOCK) {
            PingPong ping = new PingPong(true);
            Thread pingThread = new Thread(ping);
            pingThread.start();

            LOCK.wait();

            PingPong pong = new PingPong(false);
            Thread pongThread = new Thread(pong);
            pongThread.start();
        }
    }


}

public class MyThread extends Thread{
    public MyThread(String name){
        super(name);

    }
    @Override
    public void run(){
        /*
        System.out.println("Thread is Running");

        for(int i=0;i<5;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i);
        }
        System.out.println("Its done");
         */

        for (int i = 0; i < 5; i++) {
            String a = " ";
            for(int j=0;j<1000;j++){
                a += 'a';
            }
            System.out.println(Thread.currentThread().getName() + " -  Priority: " + Thread.currentThread().getPriority() + " - i -"+ i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public static void main(String[] args) throws InterruptedException {
        /*
        MyThread t1 = new MyThread();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        System.out.println((Thread.currentThread().getState()));
        Thread.sleep(100);
        System.out.println(t1.getState());
        t1.join(); //Wait for t1 to get finished
        System.out.println(t1.getState());


        MyThread t1 = new MyThread();
        t1.start();
        t1.join();
        System.out.println("Still in main but end");
 */
        MyThread l = new MyThread("Low priority");
        MyThread m = new MyThread("Medium priority");
        MyThread h = new MyThread("High priority");

        l.setPriority(Thread.MIN_PRIORITY);
        m.setPriority(Thread.NORM_PRIORITY);
        h.setPriority(Thread.MAX_PRIORITY);
        l.start();
        m.start();
        h.start();
        l.interrupt(); //Stop on the whatever method it is

        //DAEMON Threads -  Runs in background. JVM doesn't wait for daemon threads

    }
}

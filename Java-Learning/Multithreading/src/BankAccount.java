import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance = 100;
    private final Lock lock = new ReentrantLock();



    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() + " Attempting to withdraw "+ amount);
        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(balance >=  amount){
                    System.out.println(Thread.currentThread().getName() + " proceeding with wirthdrawl ");
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }finally {
                        lock.unlock();
                    }
                    balance -=  amount;
                    System.out.println("Completed withdrawl");
                }
                else{
                    System.out.println(Thread.currentThread().getName() + "  insuffice balance ");

                }
            }
            else{
                System.out.println("Couldn't acquire lock");
            }


        }catch (Exception e){
            Thread.currentThread().interrupt();
        }

    }
}

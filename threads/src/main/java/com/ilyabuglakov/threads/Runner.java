package com.ilyabuglakov.threads;

import com.ilyabuglakov.threads.person.Person;
import com.ilyabuglakov.threads.person.RunnablePerson;

public class Runner {
    public static void main(String[] args) {
        RunnablePerson alice = new RunnablePerson(new Person("Alice"));
        RunnablePerson bob = new RunnablePerson(new Person("bob"));
        Thread t1 = new Thread(alice);
        Thread t2 = new Thread(bob);

        t2.start();
        System.out.println(t2.getState());
        t1.start();
    }
}

class SimpleThread extends Thread {
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.print(e);
        }
        System.out.println("end of SimpleThread");
    }
}
class ExceptionMainDemo {
    public static void main(String[ ] args) {
        new SimpleThread().start();
        System.out.println("end of main with exception");
        throw new RuntimeException();
    }
}

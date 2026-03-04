public class Main {

    public static void main(String[] args) {
        // Use CountingDuckFactory so every quack is tracked
        AbstractDuckFactory duckFactory = new CountingDuckFactory();
        simulate(duckFactory);
    }

    // ── STEP 1 + 4: Create individual ducks via factory
    static void simulate(AbstractDuckFactory duckFactory) {
        Quackable mallardDuck   = duckFactory.createMallardDuck();
        Quackable redheadDuck   = duckFactory.createRedheadDuck();
        Quackable duckCall      = duckFactory.createDuckCall();
        Quackable rubberDuck    = duckFactory.createRubberDuck();

        // ── STEP 2: Goose adapted to Quackable
        Quackable gooseDuck     = duckFactory.createGooseDuck();

        System.out.println("\n===== Duck Simulator: With Observer =====\n");

        // ── STEP 5: Build flocks using Composite pattern
        Flock flockOfDucks = new Flock();
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(duckCall);
        flockOfDucks.add(rubberDuck);
        flockOfDucks.add(gooseDuck);

        // A sub-flock of mallard ducks
        Flock flockOfMallards = new Flock();
        Quackable mallardOne   = duckFactory.createMallardDuck();
        Quackable mallardTwo   = duckFactory.createMallardDuck();
        Quackable mallardThree = duckFactory.createMallardDuck();
        Quackable mallardFour  = duckFactory.createMallardDuck();
        flockOfMallards.add(mallardOne);
        flockOfMallards.add(mallardTwo);
        flockOfMallards.add(mallardThree);
        flockOfMallards.add(mallardFour);

        // A flock can contain other flocks (Composite!)
        flockOfDucks.add(flockOfMallards);

        // ── STEP 6: Register observer on entire flock
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);

        // ── Make the whole flock quack
        System.out.println("--- Whole flock quacking ---");
        simulate(flockOfDucks);

        System.out.println("\n--- Mallard sub-flock quacking ---");
        simulate(flockOfMallards);

        // ── STEP 3: Report total quacks counted by decorator
        System.out.println("\n===== Quack Count =====");
        System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times.");

        // ── STEP 6: Track a single duck individually 
        System.out.println("\n===== Tracking individual duck =====");
        Quackologist individualWatcher = new Quackologist();
        mallardDuck.registerObserver(individualWatcher);
        System.out.println("Watching: " + mallardDuck);
        simulate(mallardDuck);
    }

    // Polymorphic simulate — works for any Quackable (duck OR flock)
    static void simulate(Quackable duck) {
        duck.quack();
    }
}
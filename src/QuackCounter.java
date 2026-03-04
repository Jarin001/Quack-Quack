public class QuackCounter implements Quackable {
    Quackable duck;                  // The wrapped duck
    static int numberOfQuacks = 0;  // Shared counter across ALL decorated ducks

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();       // Delegate to the wrapped duck
        numberOfQuacks++;   // Then increment the count (new behaviour!)
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }

    // Delegate observer methods to the wrapped duck
    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        duck.notifyObservers();
    }

    @Override
    public String toString() {
        return duck.toString();
    }
}
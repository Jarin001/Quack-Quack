import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Flock implements Quackable {
    List<Quackable> quackers = new ArrayList<>();

    // Add a duck (or another flock!) to this flock
    public void add(Quackable quacker) {
        quackers.add(quacker);
    }

    // Quack delegates to every member, using an iterator
    @Override
    public void quack() {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable quacker = iterator.next();
            quacker.quack();
        }
    }

    // Propagate observer registration to all members
    @Override
    public void registerObserver(Observer observer) {
        Iterator<Quackable> iterator = quackers.iterator();
        while (iterator.hasNext()) {
            Quackable duck = iterator.next();
            duck.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
        // Individual ducks notify on their own when they quack
    }

    @Override
    public String toString() {
        return "Flock of Ducks";
    }
}

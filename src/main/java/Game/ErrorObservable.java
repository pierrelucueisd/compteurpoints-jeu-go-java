package Game;

import java.util.HashSet;
import java.util.Set;

public class ErrorObservable {

    protected final Set<ErrorObserver> observers;
    private static ErrorObservable singleton = null;

    public ErrorObservable() {
        this.observers = new HashSet<>();
    }

    public static ErrorObservable getSingleton() {
        if(singleton == null)
            singleton = new ErrorObservable();
        return singleton;
    }

    public void attach(ErrorObserver o) {
        observers.add(o);
    }

    public void detach(ErrorObserver o) {
        observers.remove(o);
    }

    public void notifyObservers(ErrorType err) {
        Set<ErrorObserver> targets = new HashSet<>(observers);
        targets.parallelStream().forEach(t -> t.update(err));
    }
}

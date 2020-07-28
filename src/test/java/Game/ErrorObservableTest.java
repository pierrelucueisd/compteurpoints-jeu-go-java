package Game;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ErrorObservableTest {
    ErrorObservable observable = new ErrorObservable();
    List<ErrorObserverMock> observers = Stream.of(new ErrorObserverMock(), new ErrorObserverMock()).
            collect(Collectors.toList());

    @Test
    void attach() {
        observers.forEach(o -> observable.attach(o));
        assertEquals(observable.observers.size(), 2);
    }

    @Test
    void detach() {
        observers.forEach(o -> observable.detach(o));
        assertEquals(observable.observers.size(), 0);
    }

    @Test
    void notifyObservers() {
        observers.forEach(o -> observable.attach(o));
        observable.notifyObservers(ErrorType.Ko);
        observers.forEach(o -> assertEquals(o.currentError, ErrorType.Ko));
    }
}
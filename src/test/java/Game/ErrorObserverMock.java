package Game;

public class ErrorObserverMock implements ErrorObserver {
    public ErrorType currentError;

    @Override
    public void update(ErrorType err) {
        currentError = err;
    }
}

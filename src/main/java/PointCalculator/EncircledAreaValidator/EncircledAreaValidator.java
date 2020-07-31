package PointCalculator.EncircledAreaValidator;
import PointCalculator.EncircledAreaInterface;

import java.util.function.Predicate;

public abstract class EncircledAreaValidator implements Predicate<EncircledAreaInterface> {
    @Override
    public Predicate<EncircledAreaInterface> and(Predicate<? super EncircledAreaInterface> other) {
        return (t) -> test(t) && other.test(t);
    }

    @Override
    public Predicate<EncircledAreaInterface> negate() {
        return (t) -> !test(t);
    }

    @Override
    public Predicate<EncircledAreaInterface> or(Predicate<? super EncircledAreaInterface> other) {
        return (t) -> test(t) || other.test(t);
    }
}

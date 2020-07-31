package PointCalculator.EncircledAreaValidator;
import PointCalculator.EncircledArea;

import java.util.function.Predicate;

public abstract class EncircledAreaValidator implements Predicate<EncircledArea> {
    @Override
    public Predicate<EncircledArea> and(Predicate<? super EncircledArea> other) {
        return (t) -> test(t) && other.test(t);
    }

    @Override
    public Predicate<EncircledArea> negate() {
        return (t) -> !test(t);
    }

    @Override
    public Predicate<EncircledArea> or(Predicate<? super EncircledArea> other) {
        return (t) -> test(t) || other.test(t);
    }
}

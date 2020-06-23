import java.util.Optional;

public interface Deserializer<T> {

    Optional<T> deserialize(String s);

    boolean validate(T t);
}

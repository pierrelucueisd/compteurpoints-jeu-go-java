public class Result {
    private final ResultType type;
    private final boolean success;

    public Result(ResultType type, boolean success) {
        this.type = type;
        this.success = success;
    }

    public ResultType getType() {
        return type;
    }

    public boolean failed() {
        return !success;
    }
}

package be.renaud11232.bluemapspawn;

public abstract class Wrapper<T> {
    protected final T wrapped;

    public Wrapper(T wrapped) {
        this.wrapped = wrapped;
    }
}

package model;

@FunctionalInterface
public interface Action<T> {
    public void apply(T p);
}

package comparables;
public interface Comparable<T> {
  boolean less(T c);
  boolean equal(T c);
}

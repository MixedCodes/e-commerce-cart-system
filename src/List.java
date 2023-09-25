
public interface List<E> {
	void add(E data);
    Node<E> remove(int index);
    boolean remove(E data);
	int size();
	boolean isEmpty();
}

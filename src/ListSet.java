import java.util.Set;

class ListSet<V> extends UnimplementedSet<V> {
	protected SingleLinkedList<V> impl = new SingleLinkedList<V>();

	public boolean add(V v) {
		boolean res = !contains(v);
		if (res) {
			impl.add(v);
		}
		return res;
	}

	public int size() {
		return impl.size();
	}

	public boolean isEmpty() {
		return impl.isEmpty();
	}

	// we have been prepending instead of appending so we need to reverse order !
	public <W> W[] toArray(W[] array) {
		return impl.toArray(array);
	}

	public boolean contains(Object o) {
		return impl.contains(o);
	}

	public String toString() {
		return impl.toString();
	}
}
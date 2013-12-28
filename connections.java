 
import java.io.Serializable;

/*

 Micah Forstein



 */
public class connections<E> implements CM307List<E>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node<E> head = null;
	private int size;
	private E data;
	private int index;

	private static class Node<E> {
		private E data;
		private Node<E> next;

		private Node(E Data) {
			data = Data;
			next = null;
		}

		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
		}
	}

	private void addAfter(Node<E> node, E item) {
		node.next = new Node<E>(item, node.next);
		size++;
	}

	private void addFirst(E item) {
		head = new Node<E>(item, head);
		size++;
	}

	@Override
	public void add(E item) {
		int index = size();
		if (index == 0) {
			addFirst(item);
		} else {
			Node<E> node = getNode(index - 1);
			addAfter(node, item);
		}
	}

	private E removeAfter(Node<E> node) {
		if (node.next == null) {
			return null;
		}

		Node<E> temp = node.next;
		node.next = temp.next;
		size--;
		return temp.data;
	}

	private E removeFirst() {

		Node<E> temp = head;
		if (temp == null)
			return null;
		head = temp.next;
		size--;
		return temp.data;
	}

	private Node<E> getNode(int index) // protects from null pointer exceptions
	{
		Node<E> node = head;
		for (int i = 0; i < index && node != null; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			// throw new IndexOutOfBoundsException(Integer.toString(index));
			return null;
		}
		Node<E> node = getNode(index);

		return node.data;
	}

	public E set(int index, E newValue) {
		if (index < 0 || index >= size) {
			// throw new IndexOutOfBoundsException(Integer.toString(index));
			return null;
		}
		Node<E> node = getNode(index);

		E result = node.data;
		node.data = newValue;
		return result;
	}

	@Override
	public boolean insert(int index, E item) {
		if (index < 0 || index > size) {
			return false;
		}
		if (index == 0) {
			addFirst(item);
		} else {
			Node<E> node = getNode(index - 1);
			addAfter(node, item);
		}
		return true;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			// throw new IndexOutOfBoundsException(Integer.toString(index));
			return null;
		}
		if (index == 0) {
			return removeFirst();
		} else {

			Node<E> node = getNode(index - 1);
			return removeAfter(node);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("List has " + size()
				+ " Elements \n" + "[");
		Node<E> p = head;
		if (p != null) {
			setIndex(size());
			while (p.next != null) {
				// p = getNode(index-1);
				sb.append(p.data.toString());
				if (p.next != null)
					sb.append(",");
				p = p.next;
			}
			sb.append(p.data.toString());
		}
		sb.append("]\n");
		return sb.toString();
	}

	/**
	 * @return the data
	 */
	public E getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}

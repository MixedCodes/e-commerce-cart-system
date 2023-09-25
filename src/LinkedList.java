
public class LinkedList<E> implements List<E> {
	private Node<E> front;
	
	public LinkedList() {this.front = null;}
	public LinkedList(Node<E> node) {this.front = node;}
	
	public void add(E data) {
		Node<E> node = new Node<E>(data);
		
		if (isEmpty()) {
			this.front = node;
		} else {
			Node<E> last = this.front;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(node);
		}
	}
	
	public void add(E data, int index) {
		Node<E> node = new Node<E>(data);
		
		if (index > this.size() || index < 0) {
			throw new IndexOutOfBoundsException(index);
		}
		
		if (index == 0) {
			node.setNext(this.front);
			this.front = node;
		} else if (index < this.size()) {
			node.setNext(this.getElement(index));
			Node<E> previous = this.getElement(index - 1);
			previous.setNext(node);
		} else if (index > 0) {
			Node<E> previous = this.getElement(index - 1);
			previous.setNext(node);
		}
	}
	
	public void add(Node<E> node, int index) {
		if (index > this.size() || index < 0) {
			throw new IndexOutOfBoundsException(index);
		}
		
		if (index == 0) {
			node.setNext(this.front);
			this.front = node;
		} else if (index < this.size()) {
			node.setNext(this.getElement(index));
			Node<E> previous = this.getElement(index - 1);
			previous.setNext(node);
		} else if (index > 0) {
			Node<E> previous = this.getElement(index - 1);
			previous.setNext(node);
		}
	}
	
	public Node<E> remove(int index) {
		Node<E> removed = null;
		
		if (index > this.size() - 1 || index < 0) {
			throw new IndexOutOfBoundsException(index);
		}
		
		if (index == 0) {
			this.front = this.front.getNext();
		} else if (index > 0) {
//			Node<E> previous = this.front;		
//			for (int i = 0; i < index - 2; i++) {
//				previous = previous.getNext();
//			}
			Node<E> previous = getElement(index - 1);
			removed = previous.getNext();
			previous.setNext(previous.getNext().getNext());
		}
		
		return removed;
	}
	
	public boolean remove(E data) {
		boolean removed = false;
		
		if (!isEmpty()) {
			Node<E> node = this.front;
			while (node.getNext() != null) {
				if (node.getNext().getData().equals(data)) {
					node.setNext(node.getNext().getNext());
					
					removed = true;
				}
				node = node.getNext();
			}
		}
		
		return removed;
	}
	
	public void changeOrder(int firstIndex, int secondIndex) {
		if (firstIndex == secondIndex) {return;}
		if (firstIndex > this.size() - 1 || firstIndex < 0) {
			throw new IndexOutOfBoundsException("The first index " + firstIndex + " is out of bound");
		}
		if (secondIndex > this.size() - 1 || secondIndex < 0) {
			throw new IndexOutOfBoundsException("The second index " + secondIndex + " is out of bound");
		}
		
		int min = Math.min(firstIndex, secondIndex);
	    int max = Math.max(firstIndex, secondIndex);

	    this.add(this.remove(min), max);
	    this.add(this.remove(max - 1), min);
		
	}
	
	public int size() {
		int count = 0;
		
		if (!isEmpty()) {
			count = 1;
			for (Node<E> node = this.front; node != null; node = node.getNext()) {
				count++;
			}
		}
		
		return count;
	}
	
	public boolean isEmpty() {
		return this.front == null;
	}
	
	public Node<E> getFront() {
		return this.front;
	}
	
	public Node<E> getElement(int index) {
		Node<E> node = null;
		
		if (index > this.size() - 1 || index < 0) {
			throw new IndexOutOfBoundsException(index);
		}
		
		node = this.front;
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}
		
		return node;	
	}
	
	public String toString()
    {
        StringBuilder string = new StringBuilder("[");
        for (Node<E> node = this.front; node != null; node = node.getNext()) {
            string.append(node.getData());
            if (node.getNext() != null) {
                string.append(", ");
            }
        }
        
        string.append("]");
        return string.toString();
    }
	
}

public class LinkedList<E> implements List<E> {
	private Node<E> front;
	
	public LinkedList() {this.front = null;}
	public LinkedList(Node<E> node) {this.front = node;}
	
	
	public Node<E> getFront() {
		return this.front;
	}
	
	public Node<E> getLast() {
		Node<E> last = this.front;
		
		for (Node<E> node = this.front; node != null; node = node.getNext()) {
			last = node;
		}
		
		return last;
	}
	
	public E get(int index) {
		return this.getNode(index).getData();
	}
	
	public Node<E> getNode(int index) {
		this.checkIndex(index);
		
		Node<E> node = null;
		node = this.front;
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}
				
		return node;	
	}
	
	public boolean add(E data) {
		boolean added = false;
				
		Node<E> node = new Node<E>(data);
		
		if (isEmpty()) {
			this.front = node;
			added = true;
		} else {
			Node<E> last = this.getLast();
			last.setNext(node);
			added = true;
		}
		
		return added;
	}
	
	public boolean add(E data, int index) {		
		Node<E> node = new Node<E>(data);
		return(this.add(node, index));
	}
	
	public boolean add(Node<E> node, int index) {
		boolean added = false;
		if (index < 0 || index >= this.size() + 2) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
		}
        
		if (index == 0) {
			node.setNext(this.front);
			this.front = node;
			added = true;
		} else if (index < this.size()) {
			node.setNext(this.getNode(index));
			this.getNode(index - 1).setNext(node);
		} else if (index >= this.size()) {
			node.setNext(null);
			this.getNode(index - 1).setNext(node);
			added = true;
		}
		return added;
	}
	
	public Node<E> remove(int index) {
		Node<E> removed = null;
		this.checkIndex(index);
		
		if (index == 0) {
			removed = this.front;
			this.front = this.front.getNext();
		} else if (index > 0) {
			Node<E> previous = this.getNode(index - 1);
			removed = previous.getNext();
			previous.setNext(previous.getNext().getNext());
		}
		
		return removed;
	}
	
	public boolean remove(E data) {
		boolean removed = false;
		
		int index = this.indexOf(data);
		if (index != -1) {
			this.remove(index);
			removed = true;
		}
		
		return removed;
	}
	
	public boolean changeOrder(int firstIndex, int secondIndex) {
		boolean changed = false;
		if (firstIndex == secondIndex) {
			changed = false;
			return changed;
		}
		this.checkIndex(firstIndex);
		this.checkIndex(secondIndex);
		
		int min = Math.min(firstIndex, secondIndex);
	    int max = Math.max(firstIndex, secondIndex);
	    
	    this.add(this.remove(min), max);
	    this.add(this.remove(max - 1), min);
	    changed = true;
	    
	    return changed;
	}
	
	public int size() {
		int count = 0;
		
		if (!isEmpty()) {
 			for (Node<E> node = this.front; node != null; node = node.getNext()) {
				count++;
			}
		}
		
		return count;
	}
	
	public boolean isEmpty() {return this.front == null;}
	
	public void clear() {this.front = null;}
	
	public int indexOf(E data) {
		int index = 0;
		
		if (data == null) {
			for (Node<E> node = this.front; node != null; node = node.getNext()) {
	            if (node.getData() == null) {
	            	return index;
	            }
	            index++;
	        }
        } else {
        	for (Node<E> node = this.front; node != null; node = node.getNext()) {
                if (node.getData().equals(data)) {
                	return index;
                }
                index++;
            }
        }
		
		return -1;
	}
	
	public boolean contains(E data) {
        return this.indexOf(data) != -1;
    }
	
	private void checkIndex(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds. Index: " + index + ", Size: " + this.size());
        }
    }
	
	@Override
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

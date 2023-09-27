
public class ArrayList<E> implements List<E> {
	private static final int DEFAULT_CAPACITY  = 10;
	private Object[] elements;
	private int size;
	
	public ArrayList(int capacity) {
		if (capacity < 0) {throw new IllegalArgumentException("Invalid initial capacity: " + capacity);}
		this.elements = new Object[capacity];
		this.size = 0;
	}
	
	public ArrayList(Object[] elements) {
		this.size = elements.length;
		this.elements = new Object[this.size];
		for (int i = 0; i < this.size; i++) {
			this.elements[i] = elements[i];
		}
	}
	
	public ArrayList() {this(DEFAULT_CAPACITY);}
	
	public void add(Object element) {
		this.ensureCapacity(this.size + 1);
		this.elements[this.size++] = element;
	}

	public void remove(int index) {
        checkIndex(index);
        
        int size = this.size - index - 1;
        if (size > 0) {
            System.arraycopy(elements, index + 1, elements, index, size);
        }
        elements[--this.size] = null;
    }
	
	public void remove(Object element) {
		int index = this.indexOf(element);
		if (index != -1) {
			this.remove(index);
		}
	}
	
	public void set(int index, Object element) {
        checkIndex(index);
        elements[index] = element;
    }
	
	private int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
	
    public int size()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }
    
	public void ensureCapacity(int minCapacity) {
		if (minCapacity > this.elements.length) {
            int newCapacity = this.elements.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            
            Object newElements[] = new Object[newCapacity];
            for (int i = 0; i < this.size; i++) {
            	newElements[i] = this.elements[i];
            }
            
            this.elements = newElements;
        }		
	}
	
	private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }
}

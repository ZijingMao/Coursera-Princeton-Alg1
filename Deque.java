import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node front;
	private Node end;
	private int size;

	public Deque(){                           // construct an empty deque
		front = new Node();
		end = new Node();
	}

	public boolean isEmpty(){                 // is the deque empty?
		return size == 0;
	}
	public int size(){                        // return the number of items on the deque
		return size;
	}
	public void addFirst(Item item){          // insert the item at the front
		if(item == null){
			throw new java.lang.NullPointerException();
		}else{
			if(isEmpty()){
				front = new Node(item);
				end = front;
				size++;
			} else{
				front.prev = new Node(item);
				front.prev.next = front;
				front = front.prev;
				size++;
			}
		}
	}
	public void addLast(Item item){           // insert the item at the end
		if(item == null){
			throw new java.lang.NullPointerException();
		} else {
			if(isEmpty()){
				end = new Node(item);
				front = end;
				size++;
			}else{
				end.next = new Node(item);
				end.next.prev = end;
				end = end.next;
				size++;
			}
		}
	}
	public Item removeFirst(){                // delete and return the item at the front
		if(isEmpty()){
			throw new java.util.NoSuchElementException();
		}

		Item item = front.data;
		front = front.next;

		if(front == null){
			end = null;
		} else {
			front.prev = null;
		}
		size--;
		return item;
	}
	public Item removeLast(){                 // delete and return the item at the end
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		} 

		Item item = end.data;
		end = end.prev;
		if(end == null){
			front = null;
		} else {
			end.next = null;
		}
		size--;
		return item;
	}
	public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
		return new DequeIterator<Item>();
	}

	private class DequeIterator<E> implements Iterator<E>{

		private Node current;
		
		public DequeIterator() {
			this.current = front;
		}
		
		@Override
		public boolean hasNext() {
			if(front.next == null && front.prev == null && front.data == null) {
				return false;
			}
			return current != null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			Item item = current.data;
			current = current.next;
			return (E)item;
		}

		@Override
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

	}

	private class Node {
		private Node prev;
		private Node next;
		private Item data;

		public Node(Item data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		public Node() {
			this.data = null;
			this.prev = null;
			this.next = null;
		}
	}

	public static void main(String[] args){   // unit testing
        Deque<Integer> deq2 = new Deque<Integer>();

        Iterator<Integer> itr3 = deq2.iterator();
        System.out.println(itr3.hasNext());
        
        System.out.println("deq2: " + deq2.toString());
        System.out.println("size: " + deq2.size());

        deq2.addFirst(1);
        deq2.addFirst(2);
        deq2.addFirst(3);
        deq2.addFirst(4);
        deq2.addFirst(5);


        System.out.println("deq2: " + deq2.toString());

        deq2.removeLast();
        System.out.println("deq2: " + deq2.toString());

        deq2.removeFirst();
        deq2.removeFirst();
        System.out.println("deq2: " + deq2.toString());
        System.out.println("size: " + deq2.size());

        deq2.removeLast();
        deq2.removeLast();
        System.out.println("deq2: " + deq2.toString());

        deq2.addFirst(1);
        deq2.addLast(2);
        System.out.println("deq2: " + deq2.toString());

        deq2.addFirst(3);
        deq2.addLast(4);
        System.out.println("deq2: " + deq2.toString());

        System.out.println("size: " + deq2.size());
        
        Iterator<Integer> itr = deq2.iterator();
        Iterator<Integer> itr2 = deq2.iterator();
        //System.out.println(itr.);
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr.next());
        System.out.println(itr2.next());
        System.out.println(itr2.next());
        System.out.println(itr2.next());
        System.out.println(itr2.next());
	}

}

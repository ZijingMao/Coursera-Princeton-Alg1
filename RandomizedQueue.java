import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int size;
	private Item[] rQ;
	private int init;

	@SuppressWarnings("unchecked")
	public RandomizedQueue(){                 // construct an empty randomized queue
		init = 10;
		rQ = (Item []) new Object[init];
	}
	public boolean isEmpty(){                 // is the queue empty?
		return size == 0;
	}
	public int size(){                        // return the number of items on the queue
		return size;
	}
	private void resize(int capacity){
		if(capacity < size)	return;
		@SuppressWarnings("unchecked")
		Item[] tmp = (Item[]) new Object[capacity];
		for(int i = 0; i < size; i++){
			tmp[i] = rQ[i];
		}
		rQ = tmp;
	}
	public void enqueue(Item item){           // add the item
		if(item == null){
			throw new java.lang.NullPointerException();
		} else{
			if(size == rQ.length){
				init = init * 2;
				resize(init);
			}
			rQ[size] = item;
			size++;
		}
	}
	public Item dequeue(){                    // delete and return a random item
		if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
		int index = StdRandom.uniform(size());
		swap(index, size-1);
		Item r = rQ[size-1];
		rQ[size-1] = null;
		size--;
		if(size < rQ.length/4){
			resize(rQ.length/2);
		}
		return r;
	}
	private void swap(int index, int i) {
		Item tmp = rQ[index];
		rQ[index] = rQ[i];
		rQ[i] = tmp;
	}
	public Item sample(){                     // return (but do not delete){ a random item
		if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int index = StdRandom.uniform(size());
        return rQ[index];
	}
	public Iterator<Item> iterator(){         // return an independent iterator over items in random order
		return new RandomizedQueueIterator<Item>();
	}
	
	private class RandomizedQueueIterator<E> implements Iterator<E> {
		private Item[] itemIt;
        private int itemsLeft;
        
        @SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
        	itemIt = (Item[]) new Object[size];
            this.constructIterator();       //best practice?
            this.itemsLeft = size;
        }
        
        private void constructIterator() {
            int j = 0;
            for (int i = 0; i < init; i++) {
                if (j == size) {   break;  }   //not sure if added check reqd
                if (rQ[i] != null) {
                    this.itemIt[j++] = rQ[i];
                }
            }
        }
        
        public boolean hasNext() {
        	return this.itemsLeft != 0;  
        }
        @SuppressWarnings("unchecked")
		public E next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int rand = StdRandom.uniform(this.itemIt.length);

            while (itemIt[rand] == null) {
                rand = StdRandom.uniform(this.itemIt.length);
            }

            Item elem = itemIt[rand];
            itemIt[rand] = null;
            this.itemsLeft--;
            return (E)elem;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
	
	public static void main(String[] args){   // unit testing
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(0);
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);

        System.out.println("items: " + rq.size);

        System.out.println(rq.toString());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());

        //System.out.println(rq.to`String());
        System.out.println("items: " + rq.size);

        Iterator<Integer> it1 = rq.iterator();
        Iterator<Integer> it2 = rq.iterator();

        while (it1.hasNext()) {
            System.out.print(it1.next());
        }
        System.out.println("\n");
        while (it2.hasNext()) {
            System.out.print(it2.next());
        }
	}
}

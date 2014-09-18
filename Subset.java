
public class Subset {
	
	public static void main(String[] args){
		 int k = Integer.valueOf(args[0]);

        RandomizedQueue<String> rQ = new RandomizedQueue<String>();
        String str = StdIn.readString();

        rQ.enqueue(str);

        while (!StdIn.isEmpty()) {
            str = StdIn.readString();
            rQ.enqueue(str);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rQ.dequeue());
        }

	}

}

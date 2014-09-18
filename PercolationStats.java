import java.util.Random;

public class PercolationStats {
	private double [] attemps;

	private static int N;
	private static int T;

	public PercolationStats(int N, int T) { 
		if (T <= 0)	throw new IllegalArgumentException();

		int gridSize = N * N;
		
		attemps = new double [T];
		
		for (int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			
			Random rnd = new Random();
			int[] grids = new int[gridSize];
			for (int j = 0; j < gridSize; j++) {
				grids[j] = j;
			}
			for (int j = 0; j < gridSize; j++) {
	            int x = rnd.nextInt(gridSize);
	            int tmp = grids[j];
	            grids[j] = grids[gridSize - x - 1];
	            grids[gridSize - x - 1] = tmp;
	        }
			
			int steps = 0;
			while (!p.percolates()) {
				int row = grids[steps] / N + 1;
				int column = grids[steps++] % N + 1;
				if (!p.isOpen(row, column)) {
					p.open(row, column);
				}
			}
			attemps[i] = (double)steps / (N * N);
		}
	}
	
	public double mean() { 
		return StdStats.mean(attemps);
	}

	public double stddev() { 
		return StdStats.stddev(attemps);
	}

	public double confidenceLo() { 
		return mean()-((1.96*stddev())/Math.sqrt(attemps.length)); 
	}

	public double confidenceHi() { 
		return mean()+((1.96*stddev())/Math.sqrt(attemps.length)); 
	}

	public static void main(String[] args) { 
		N = Integer.parseInt(args[0]);
		T = Integer.parseInt(args[1]);
		PercolationStats ps=new PercolationStats(N,T); 
		StdOut.print("mean = "+ps.mean()+"\n");
		StdOut.print("std dev = "+ps.stddev()+"\n");
		StdOut.print("95% confidence interval = "+ps.confidenceLo()+", "+ps.confidenceHi());
	}
}


public class Percolation {
	private static final int OPEN = 1;
	
	private int N;
	private int [] grids;
	private WeightedQuickUnionUF cellStorage;
	private WeightedQuickUnionUF Backwash;

	public Percolation(int N) { 
		if (N <= 0 )	throw new IllegalArgumentException ("Illegal parameter value.");  
		this.N = N;
		this.grids = new int[N * N + 2];
		this.cellStorage = new WeightedQuickUnionUF((N * N) + 2);
		Backwash = new WeightedQuickUnionUF((N * N) + 2);

		grids[N * N] = OPEN;
		grids[N * N + 1] = OPEN;
	}

	public void open(int i, int j) { 
		checkRange(i, j);
		int cell = getCellIndex(i, j);
		
		if (!isOpen(i, j)) {
			grids[cell] = OPEN;
		}
		
		if (i != 1 && isOpen(i - 1, j)) {
			union(getCellIndex(i - 1, j), cell);
			Backwash.union(getCellIndex(i - 1, j), cell);
		} else if (i == 1) {
			union(cell, N * N);
			Backwash.union(cell, N * N);
		}
		
		if (i != N && isOpen(i + 1, j)) {
			union(getCellIndex(i + 1, j), cell);
			Backwash.union(getCellIndex(i + 1, j), cell);
		} else if (i == N) {
			Backwash.union(cell, N * N + 1);
		}
		
		if (j != 1 && isOpen(i, j - 1)) {
			union(getCellIndex(i, j - 1), cell);
			Backwash.union(getCellIndex(i, j - 1), cell);
		}
		
		if (j != N && isOpen(i, j + 1)) {
			union(getCellIndex(i, j + 1), cell);
			Backwash.union(getCellIndex(i, j + 1), cell);
		}
	}

	public boolean isOpen(int i, int j) { 
		checkRange(i, j);
		return grids[getCellIndex(i, j)] == OPEN;
	}

	public boolean isFull(int i, int j) { 
		checkRange(i, j);
		int cell = getCellIndex(i, j);
		
		if (!isOpen(i, j))	return false;
		else if (cellStorage.connected(N * N, cell))	return true;
		else return false;
	}

	public boolean percolates() { 
		return Backwash.connected(N * N, N * N + 1);
	}

	private void checkRange(int i, int j) {
		if (i <= 0 || i > N || j <= 0 || j > N)  
			throw new IndexOutOfBoundsException("Illegal parameter value.");  
	}
	
	private int getCellIndex(int i, int j){
		return N*(i-1)+j-1;
	}
	
	private void union(int a, int b){
		cellStorage.union(a,b);
	}
}

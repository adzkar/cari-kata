public class WordMatrix {
	public int[][] solution;
	public char[][] result;
	int path = 1;

	// Init
	public WordMatrix(int N) {
		solution = new int[N][N];
		result = new char[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solution[i][j] = 0;
				result[i][j] = '_';
			}
		}
	}

	public boolean searchWord(char[][] matrix, String word) {
		int N = matrix.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (search(matrix, word, i, j, 0, N)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean search(char[][] matrix, String word, int row, int col,
			int index, int N) {

		// cek pernah dikunjungi
		if (solution[row][col] != 0 || word.charAt(index) != matrix[row][col])
			return false;

		// word is found
		if (index == word.length() - 1) {
			solution[row][col] = path++;
			result[row][col] = matrix[row][col];
			return true;
		}

		// mark the current cell as 1
		solution[row][col] = path++;
		result[row][col] = matrix[row][col];		
		// check if cell is already used

		// go down
		if (row + 1 < N && search(matrix, word, row + 1, col, index + 1, N))
			return true;

		// go up
		if (row - 1 >= 0 && search(matrix, word, row - 1, col, index + 1, N))																
			return true;
		
		// go right
		if (col + 1 < N && search(matrix, word, row, col + 1, index + 1, N))			
			return true;

		// go left
		if (col - 1 >= 0 && search(matrix, word, row, col - 1, index + 1, N))
			return true;
		
		// go diagonally up right
		if (row - 1 >= 0 && col + 1 < N
				&& search(matrix, word, row - 1, col + 1, index + 1, N))
			return true;
		
		// go diagonally up left
		if (row - 1 >= 0 && col - 1 >= 0
				&& search(matrix, word, row - 1, col - 1, index + 1, N))
			return true;
	
		// go diagonally down left
		if (row + 1 < N && col - 1 >= 0
				&& search(matrix, word, row + 1, col - 1, index + 1, N))
			return true;
	
		// go diagonally down right
		if (row + 1 < N && col + 1 < N
				&& search(matrix, word, row + 1, col + 1, index + 1, N))
			return true;

		// No Solution
		solution[row][col] = 0;
		result[row][col] = '_';
		path--;
		return false;
	}

	public void print() {
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length; j++) {
				System.out.print(" " + solution[i][j]);
			}
			System.out.println();
		}
	}

	public void printBoard() {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				System.out.print(" " + result[i][j]);
			}
			System.out.println();
		}
	}

	public void printMatrix(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		char[][] board = { 
				{ 'a', 'z', 'x', 'c', 'd' },
				{ 'a', 'h', 'n', 'z', 'x' }, 
				{ 'h', 'a', 'o', 'i', 'a' },
				{ 'o', 'l', 'o', 'r', 'm' }, 
				{ 'a', 'g', 'r', 'i', 't' } };
		WordMatrix w = new WordMatrix(board.length);
		
		String s = "dasar";
		if (w.searchWord(board, s)) {
			System.out.printf("Search Words: %s \n", s);
			System.out.printf("Matrix: \n");
			w.printMatrix(board);
			System.out.printf("\n Route: \n");
			w.print();
			System.out.printf("\n Result: \n");
			w.printBoard();
		} else {
			System.out.println("NO PATH FOUND");
		}

	}

}
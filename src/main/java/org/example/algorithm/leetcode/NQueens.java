import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Changhee Choi
 * @since 19/05/2021
 */
class NQueens {
	private List<List<String>> answer;

	public List<List<String>> solveNQueens(int n) {
		answer = new ArrayList<>(n);
		char[][] board = new char[n][n];

		for(int i=0; i<n; i++) {
			Arrays.fill(board[i], '.');
		}

		solve(0, 0, n, board);
		return answer;
	}

	private void solve(int row, int cnt, int n, char[][] board) {
		if (cnt == n) {
			List<String> list = new ArrayList<>();
			for (char[] line : board) {
				list.add(new String(line));
			}
			answer.add(list);
			return;
		}

		for (int j = 0; j < n; j++) {
			if (checkQueenPosition(row, j, n, board)) {
				board[row][j] = 'Q';
				solve(row + 1, cnt + 1, n, board);
				board[row][j] = '.';
			}
		}
	}

	private boolean checkQueenPosition(int x, int y, int n, char[][] board) {
		for (int i = 0; i < n; i++) {
			if (x - i >= 0 && board[x - i][y] == 'Q') {
				return false;
			}
			if (x + i < n && board[x + i][y] == 'Q') {
				return false;
			}
			if (y - i >= 0 && board[x][y - i] == 'Q') {
				return false;
			}
			if (y + i < n && board[x][y + i] == 'Q') {
				return false;
			}
			if ((x - i >= 0 && y - i >= 0) && board[x - i][y - i] == 'Q') {
				return false;
			}
			if ((x + i < n && y + i < n) && board[x + i][y + i] == 'Q') {
				return false;
			}
			if ((x + i < n && y - i >= 0) && board[x + i][y - i] == 'Q') {
				return false;
			}
			if ((x - i >= 0 && y + i < n) && board[x - i][y + i] == 'Q') {
				return false;
			}
		}
		return true;
	}
}

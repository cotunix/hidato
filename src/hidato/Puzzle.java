package hidato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {

	int size;
	ArrayList<Integer[]> unfilled = new ArrayList<Integer[]>();
	Square[][] puzzle;
	Scanner in;
	private static int[][] directions = new int[][] { { -1, -1 }, { -1, 0 },
			{ -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };

	public Puzzle() {
		try {
			in = new Scanner(new File("Hidato.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		size = in.nextInt();

		puzzle = new Square[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				puzzle[i][j] = new Square(in.nextInt());
				if (puzzle[i][j].getNum() == 0)
					unfilled.add(new Integer[] { i, j });

			}
		}
		setUpValidNum();

	}

	public void setUpValidNum() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size * size; k++) {
					puzzle[i][j].validNumbers.add(k);
				}
			}
		}
	}

	public void updateSquares() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		ArrayList<Integer> remove = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				remove.clear();
				temp.clear();
				for (int[] d : directions) {
					try {
						if (temp.contains(puzzle[i + d[0]][j + d[1]].getNum() + 1))
							temp2.add(puzzle[i + d[0]][j + d[1]].getNum() + 1);
						else
							temp.add(puzzle[i + d[0]][j + d[1]].getNum() + 1);
						if (temp.contains(puzzle[i + d[0]][j + d[1]].getNum() - 1))
							temp2.add(puzzle[i + d[0]][j + d[1]].getNum() - 1);
						else
							temp.add(puzzle[i + d[0]][j + d[1]].getNum() - 1);

					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				if (!temp.contains(new Integer(0))) {
					for (Integer v : puzzle[i][j].validNumbers) {
						if (!temp2.contains(v)) {
							remove.add(v);
						}
					}
					puzzle[i][j].removeNums(remove);
				}
			}
		}
	}

	public boolean forwardCheck() {
		for (int i = Backtracking.fill; i < unfilled.size(); i++) {
			if (puzzle[unfilled.get(i)[0]][unfilled.get(i)[1]].validNumbers
					.size() == 0)
				return false;
		}
		return true;
	}

	public void removeInvalidNums(ArrayList<Integer> invalidNum) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				puzzle[i][j].removeNums(invalidNum);
			}
		}
	}

	public void removeInvalidNum(int invalidNum) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				puzzle[i][j].removeNum(invalidNum);
			}
		}
	}

	public void addValidNum(int validNum) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				puzzle[i][j].validNumbers.add(validNum);
			}
		}
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ret += puzzle[i][j].getNum() + " ";
			}
			ret += "\n";
		}

		return ret + "\n";
	}

}

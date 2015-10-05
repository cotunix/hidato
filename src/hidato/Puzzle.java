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
	ArrayList<Integer> validNum = new ArrayList<Integer>();
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
		validNum.clear();
		for (int i = 1; i < (size * size) + 1; i++){
			validNum.add(i);
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (puzzle[i][j].getNum() != 0)
					validNum.remove(puzzle[i][j].getNum());
			}
		}
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
				this.validNum.add(validNum);
			}
		}
	}

	public boolean checkAround(int i, int j) {
		setUpValidNum();
		int count = 0;
		int temp = puzzle[i][j].getNum();
		for (int[] d : directions) {
			try {
				if (puzzle[i + d[0]][j + d[1]].getNum() + 1 == temp)
					count++;
				else if (puzzle[i + d[0]][j + d[1]].getNum() - 1 == temp)
					count++;
				else if (puzzle[i + d[0]][j + d[1]].getNum() == 0 && validNum.contains(temp + 1))
					count++;
				else if (puzzle[i + d[0]][j + d[1]].getNum() == 0 && validNum.contains(temp - 1))
					count++;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return (count >= 2);
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

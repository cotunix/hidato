package hidato;

import java.util.*;

public class Backtracking {

	ArrayList<Integer> invalidNum = new ArrayList<Integer>();
	public Puzzle puzzle;
	public static int fill;

	public Backtracking() {

		puzzle = new Puzzle();
		setupInvalidNum();
		puzzle.removeInvalidNums(invalidNum);
		puzzle.updateSquares();
		hidato(puzzle.unfilled.get(0)[0], puzzle.unfilled.get(0)[1]);
	}

	public void setupInvalidNum() {
		invalidNum.add(0);
		for (int i = 0; i < puzzle.size; i++) {
			for (int j = 0; j < puzzle.size; j++) {
				if (puzzle.puzzle[i][j].getNum() != 0) {
					invalidNum.add((Integer) puzzle.puzzle[i][j].getNum());
				}
			}
		}
	}

	public void hidato(int i, int j) {
		puzzle.updateSquares();
		fill++;
		if (!puzzle.forwardCheck()){
			System.out.println(i + " " + j);
			fill--;
			return;
			
		}
		
		//if (puzzle.unfilled.isEmpty()){
		//	System.out.print(puzzle);
		//	System.exit(0);
		//}
			
		
		for (Integer v : puzzle.puzzle[i][j].validNumbers) {
			puzzle.updateSquares();
			puzzle.puzzle[i][j].setNum(v);
			puzzle.removeInvalidNum(v);			
			System.out.print(puzzle);
			hidato(puzzle.unfilled.get(fill)[0], puzzle.unfilled.get(fill)[1]);
			puzzle.addValidNum(v);
			
		}
		fill--;
		
	}

	public static void main(String args[]) {

		new Backtracking();
	}
}

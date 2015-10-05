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
		puzzle.setUpValidNum();
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
		
		puzzle.setUpValidNum();
		fill++;
		
			
		ArrayList<Integer> temp = new ArrayList<Integer>(puzzle.validNum);
		for (Integer v = 0; v < temp.size(); v ++){
			
			
			puzzle.puzzle[i][j].setNum(temp.get(v));
			if (puzzle.checkAround(i,j)){
					
				//System.out.print(puzzle);
				if (fill == puzzle.unfilled.size()){
					System.out.println(puzzle.size);
					System.out.print(puzzle);
					System.exit(0);
				}
					hidato(puzzle.unfilled.get(fill)[0], puzzle.unfilled.get(fill)[1]);
				
				
				
			}
			
			
			
		}
		puzzle.puzzle[i][j].setNum(0);
		fill--;
		
	}

	public static void main(String args[]) {

		new Backtracking();
	}
}

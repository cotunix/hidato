package hidato;

import java.util.ArrayList;

public class Square {

	private Integer num;
	public ArrayList<Integer> validNumbers;
	
	public Square(int num) {
		this.num = num;
		validNumbers = new ArrayList<Integer>();
	}
	
	public void removeNums(ArrayList<Integer> num) {
		for (Integer i : num){
			validNumbers.remove(i);
		}
	}
	
	public void removeNum(Integer i) {
		validNumbers.remove(i);
	}
	
	public Integer getNum(){
		return num;
	}
	
	public void setNum(int newNum){
		num = newNum;
		validNumbers = new ArrayList<Integer>();
		validNumbers.add(num);
	}
}

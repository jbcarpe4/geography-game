package main;

public class Question {
	
	private String state;
	
	private String ans1;
	
	private String ans2;
	
	private String ans3;
	
	private String ans4;
	
	private String correct;
	
	public Question(String state, String ans1, String ans2, String ans3, String ans4,
					String correct) {
		setState(state);
		setAns1(ans1);
		setAns2(ans2);
		setAns3(ans3);
		setAns4(ans4);
		setCorrect(correct);
	}

	public String getState() {
		return state;
	}

	private void setState(String state) {
		this.state = state;
	}

	public String getAns1() {
		return ans1;
	}

	private void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	private void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getAns3() {
		return ans3;
	}

	private void setAns3(String ans3) {
		this.ans3 = ans3;
	}

	public String getAns4() {
		return ans4;
	}

	private void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	
	public String getCorrect() {
		return correct;
	}
	
	private void setCorrect(String correct) {
		this.correct = correct;
	}

}

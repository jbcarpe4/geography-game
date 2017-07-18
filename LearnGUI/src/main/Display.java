package main;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Display extends JPanel implements ActionListener {
	
	private JButton playUSA;
	private JButton playEurope;
	private String quizChoice;
	
	private JLabel answer;
	private JLabel question;
	private ButtonGroup group;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private JRadioButton radio3;
	private JRadioButton radio4;
	private JButton next;
	private ArrayList<Question> questions;
	private int index;
	private int numCorrect;
	private JLabel tracker;
	private JLabel picture;
	private JLabel correctAnswer;
	
	private static final String US_GEOGRAPHY = "input/us_geography";
	
	private static final String EUROPEAN_GEOGRAPHY = "input/european_geography";
	
	public Display() {	
		index = 0;
		numCorrect = 0;
		questions = new ArrayList<Question>();
		
		//Question
		question = new JLabel();
		question.setHorizontalAlignment(JLabel.CENTER);
		question.setFont(new Font("Georgia", Font.BOLD, 20));
		//Answer
		answer = new JLabel();
		answer.setHorizontalAlignment(JLabel.CENTER);
		answer.setFont(new Font("Georgia", Font.BOLD, 20));
		//Correct Answer
		correctAnswer = new JLabel();
		correctAnswer.setHorizontalAlignment(JLabel.CENTER);
		correctAnswer.setFont(new Font("Georgia", Font.BOLD, 14));
		//Tracker
		tracker = new JLabel();
		tracker.setHorizontalAlignment(JLabel.CENTER);
		tracker.setFont(new Font("Georgia", Font.BOLD, 13));
		//Picture
		picture = new JLabel();
		
		//Multiple Choice Buttons
		radio1 = new JRadioButton();
		radio1.setVisible(false);
		radio1.setFocusPainted(false);
		radio2 = new JRadioButton();
		radio2.setVisible(false);
		radio3 = new JRadioButton();
		radio3.setVisible(false);
		radio4 = new JRadioButton();
		radio4.setVisible(false);
		
		radio1.addActionListener(this);
		radio2.addActionListener(this);
		radio3.addActionListener(this);
		radio4.addActionListener(this);
		
		group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		group.add(radio3);
		group.add(radio4);
		
		//Next
		next = new JButton("Next question");
		next.addActionListener(this);
		next.setVisible(false);
		//USA Choice
		playUSA = new JButton("USA State Capitals Quiz");
		playUSA.setHorizontalAlignment(JButton.CENTER);
		playUSA.addActionListener(this);
		playUSA.setVisible(true);
		playUSA.setFocusPainted(false);
		//Europe Choice
		playEurope = new JButton("European Countries' Capitals Quiz");
		playEurope.setHorizontalAlignment(JButton.CENTER);
		playEurope.addActionListener(this);
		playEurope.setVisible(true);

		setPreferredSize(new Dimension(600, 400));
		setLayout(null);
		
		add(answer);
		add(question);
		add(radio1);
		add(radio2);
		add(radio3);
		add(radio4);
		add(next);
		add(playUSA);
		add(playEurope);
		add(tracker);
		add(picture);
		add(correctAnswer);

		radio1.setBounds(50, 100, 200, 50);
		radio2.setBounds(50, 150, 200, 50);
		radio3.setBounds(50, 200, 200, 50);
		radio4.setBounds(50, 250, 200, 50);
		answer.setBounds(100, 325, 400, 50);
		question.setBounds(50, 25, 500, 50);
		next.setBounds(450, 325, 125, 50);
		playUSA.setBounds(100, 75, 400, 100);
		playEurope.setBounds(100, 200, 400, 100);
		tracker.setBounds(25, 325, 100, 50);
		picture.setBounds(250, 100, 300, 200);
		correctAnswer.setBounds(100, 350, 400, 50);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playUSA) {
			quizChoice = "us";
			chooseQuiz(quizChoice);
		}
		else if (e.getSource() == playEurope) {
			quizChoice = "eu";
			chooseQuiz(quizChoice);
		}
		else if (e.getSource() == next) {
			index++;
			if (index >= questions.size()) complete();
			else setup();
		}
		else {
			Question q = questions.get(index);
			if (e.getActionCommand().equals(q.getCorrect())) {
				numCorrect++;
				answer.setText("Correct!");
			}
			else {
				answer.setText("Incorrect!");
				correctAnswer.setText("The answer is " + q.getCorrect());
			}
			radio1.setEnabled(false);
			radio2.setEnabled(false);
			radio3.setEnabled(false);
			radio4.setEnabled(false);
			next.setVisible(true);
			tracker.setText("Score: " + numCorrect + "/" + (index + 1));
		}		
	}
	
	private void chooseQuiz(String quiz) {
		if (quiz.equals("us")) GeographyIO.readFile(US_GEOGRAPHY, questions);
		else if (quiz.equals("eu")) GeographyIO.readFile(EUROPEAN_GEOGRAPHY, questions);
		Random rand = new Random();
		for (int i = questions.size() - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			Question temp = questions.get(index);
			questions.set(index, questions.get(i));
			questions.set(i, temp);
		}
		playUSA.setVisible(false);
		playEurope.setVisible(false);
		radio1.setVisible(true);
		radio2.setVisible(true);
		radio3.setVisible(true);
		radio4.setVisible(true);
		setup();
	}

	private void setup() {
		Question q = questions.get(index);
		
		question.setText("What is the capital of " + q.getState() + "?");
		radio1.setText(q.getAns1());
		radio1.setActionCommand(q.getAns1());
		radio2.setText(q.getAns2());
		radio2.setActionCommand(q.getAns2());
		radio3.setText(q.getAns3());
		radio3.setActionCommand(q.getAns3());
		radio4.setText(q.getAns4());
		radio4.setActionCommand(q.getAns4());
		radio1.setEnabled(true);
		radio2.setEnabled(true);
		radio3.setEnabled(true);
		radio4.setEnabled(true);
		
		String imagePath = "images/" + quizChoice + "/" + q.getState().toLowerCase() + ".jpg";
		picture.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage()
			   .getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_DEFAULT)));
		
		answer.setText("");
		correctAnswer.setText("");

		next.setVisible(false);
		group.clearSelection();
	}

	private void complete() {
		removeAll();
		repaint();
		JLabel fin = new JLabel("Thanks for playing!");
		JLabel score = new JLabel("You got " + numCorrect + " out of " + index + " correct!");
		add(fin);
		add(score);
		fin.setBounds(25, 150, 550, 100);
		fin.setHorizontalAlignment(JLabel.CENTER);
		fin.setFont(new Font("Calibri", Font.BOLD, 50));
		score.setBounds(25, 250, 550, 100);
		score.setHorizontalAlignment(JLabel.CENTER);
		score.setFont(new Font("Calibri", Font.ITALIC, 20));
	}

}

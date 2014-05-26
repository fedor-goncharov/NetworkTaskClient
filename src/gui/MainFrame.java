package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Game;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	Game game;
	
	JPanel panel, myAnswerPanel, aPanel[], sPanel[], scorePanel, topPanel, sqPanel;
	JPanel bottomPanel, answersPanel, amaPanel;
	Container container;
	JTextArea questionArea, stateArea;
	JLabel label, imageLabel;
	JTextField answers[], names[], scores[];
	JTextField myAnswer;
	JButton submitButton;
	JRadioButton rbuttons[];
	ButtonGroup group;

	public MainFrame(Game g) {
		super("Wits & Wagers by Lena & Fedor");
		
		game = g;
		
        setBounds(10, 10, 1000, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        container.add(panel);
        
        initTopPanel();
        initBottomPanel();
        
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(topPanel);
        panel.add(Box.createRigidArea(new Dimension(5, 5)));
        panel.add(bottomPanel);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
	}
	
	void initTopPanel()
	{
		topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        initSqPanel();
        initScoreArea();
        
        topPanel.add(Box.createRigidArea(new Dimension(30, 30)));
        topPanel.add(sqPanel);
        topPanel.add(Box.createRigidArea(new Dimension(30, 30)));
        topPanel.add(scorePanel);
        topPanel.add(Box.createRigidArea(new Dimension(30, 30)));
	}
	
	void initBottomPanel()
	{
		bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        
        imageLabel = new JLabel();
		setImage(0);
        initAmaPanel();
        
        bottomPanel.add(imageLabel);
        bottomPanel.add(amaPanel);
	}
	
	void initAmaPanel()
	{
		amaPanel = new JPanel();
        amaPanel.setLayout(new BoxLayout(amaPanel, BoxLayout.Y_AXIS));
        
		initAnswersPanel();
		initMyAnswerPanel();
		
		amaPanel.add(answersPanel);
        amaPanel.add(myAnswerPanel);
	}
	
	void initSqPanel()
	{
		sqPanel = new JPanel();
        sqPanel.setLayout(new BoxLayout(sqPanel, BoxLayout.Y_AXIS));
        
        stateArea = new JTextArea();
        stateArea.setMaximumSize( new Dimension( 700, 70 ) );
        stateArea.setEditable(false);
        stateArea.setFont(new Font("Verdana", Font.BOLD, 14));
        stateArea.setLineWrap(true);
        stateArea.setWrapStyleWord(true);
        stateArea.setForeground(Color.DARK_GRAY);
        sqPanel.add(stateArea);
        
        sqPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        
        questionArea = new JTextArea();
        questionArea.setMaximumSize(new Dimension(700,120));
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("Verdana", Font.BOLD, 16));
        sqPanel.add(questionArea);
	}
	
	void initMyAnswerPanel()
	{
		label = new JLabel("My Answer : ", JLabel.LEFT);
		myAnswer = new JTextField();
		myAnswer.setMaximumSize( new Dimension( 150, 30 ) );
        submitButton = new JButton("Submit"); 
        submitButton.addActionListener(game.listener);
        
		myAnswerPanel = new JPanel();
        myAnswerPanel.setAlignmentY(JPanel.RIGHT_ALIGNMENT);
        myAnswerPanel.setLayout(new BoxLayout(myAnswerPanel, BoxLayout.X_AXIS));
        myAnswerPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        myAnswerPanel.add(label);
        myAnswerPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        myAnswerPanel.add(myAnswer);
        myAnswerPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        myAnswerPanel.add(submitButton);
        myAnswerPanel.add(Box.createRigidArea(new Dimension(5, 5)));
	}
	
	void initAnswersPanel()
	{
		answersPanel = new JPanel();
        answersPanel.setLayout(new BoxLayout(answersPanel, BoxLayout.Y_AXIS));
        answersPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		answers = new JTextField[Game.maxPlayers];
		rbuttons = new JRadioButton[Game.maxPlayers];
		group = new ButtonGroup(); 
		aPanel = new JPanel[Game.maxPlayers];
		answersPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		for (int i = 0; i<Game.maxPlayers; i++)
		{
			answers[i] = new JTextField();
			answers[i].setEditable(false);
			answers[i].setMaximumSize( new Dimension( 100, 50 ) );
			
			rbuttons[i] = new JRadioButton();
			group.add(rbuttons[i]);
			
			aPanel[i] = new JPanel();
			aPanel[i].setLayout(new BoxLayout(aPanel[i], BoxLayout.X_AXIS));
			aPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			aPanel[i].add(answers[i]);
			aPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			aPanel[i].add(rbuttons[i]);
			aPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			answersPanel.add(aPanel[i]);
			answersPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		}
	}
	
	void initScoreArea()
	{
		sPanel = new JPanel[Game.maxPlayers];
		scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
		names = new JTextField[Game.maxPlayers];
		scores = new JTextField[Game.maxPlayers];
		for (int i = 0; i<Game.maxPlayers; i++)
		{
			names[i] = new JTextField();
			names[i].setEditable(false);
			names[i].setMaximumSize( new Dimension( 300, 30 ) );
			
			scores[i] = new JTextField();
			scores[i].setEditable(false);
			scores[i].setMaximumSize( new Dimension( 100, 30 ) );

			sPanel[i] = new JPanel();
			sPanel[i].setLayout(new BoxLayout(sPanel[i], BoxLayout.X_AXIS));
			sPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			sPanel[i].add(names[i]);
			sPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			sPanel[i].add(scores[i]);
			sPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			scorePanel.add(sPanel[i]);
			scorePanel.add(Box.createRigidArea(new Dimension(5, 5)));
		}
	}

	void freeze()
	{
		stateArea.setText("");
		stateArea.setText("Waiting for other players ...");
	}

	public void newRound(int questionId, String questionString) {
		for (int i = 0; i< game.names.size(); i++)
			answers[i].setText("");
		myAnswer.setText("");
		
		questionArea.setText("");
		questionArea.setText(questionString);
		setImage(questionId);
		
		String s ="";
		if (game.nRounds >1 && game.isRoundWinner)
			s = "Вы угадали! " + "Верный ответ на вопрос : " + Integer.toString(game.rightAnswer) + ". Следующий вопрос.\n";
		else if (game.nRounds >1)
			s = "Вы не угадали. "+ "Верный ответ на вопрос : " + Integer.toString(game.rightAnswer) + ". Следующий вопрос.\n";
		stateArea.setText(s + "Напечатайте в поле <My Answer> свой вариант ответа (целое число) и нажмите <Submit>");
	}

	public void showAnswers(List<Integer> answersList) {
		stateArea.setText("Выберите ответ, напечатайте в поле <My Answer> свою ставку (целое число, не превышающее ваше количество очков) и нажмите <Submit>");
		myAnswer.setText("");
		
		for (int i =0; i<Game.maxPlayers; i++)
		{
			rbuttons[i].setEnabled(false);
		}
		for (int i =0; i< answersList.size() && i<Game.maxPlayers; i++)
		{
			answers[i].setText(answersList.get(i).toString());
			rbuttons[i].setEnabled(true);
		}
	}
	
	public void updateScoreTable()
	{
		for (int i =0; i<game.names.size(); i++)
		{
			int score = game.scores.get(i);
			scores[i].setText(Integer.toString(score));
		}
	}
	
	public void firstFillin()
	{
		for (int i =game.names.size(); i<Game.maxPlayers; i++)
		{
			rbuttons[i].setEnabled(false);
		}
		for (int i =0; i<game.names.size(); i++)
		{
			String name = game.names.get(i);
			if (name.equals(game.myName))
			{
				game.myId = i;
				names[i].setFont(new Font("Verdana", Font.BOLD, 12));
				scores[i].setFont(new Font("Verdana", Font.BOLD, 12));
			}
			names[i].setText(name);
			int score = game.scores.get(i);
			scores[i].setText(Integer.toString(score));
		}
	}
	
	private void setImage(int id)
	{
		String imageName = Integer.toString(id+1) + ".jpg";
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + imageName));
        icon.getImage().flush();
        imageLabel.setIcon( icon );
	}
}

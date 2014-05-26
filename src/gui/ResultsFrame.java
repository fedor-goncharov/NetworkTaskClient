package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Game;

@SuppressWarnings("serial")
public class ResultsFrame extends JFrame {
	JPanel panel, sPanel[];
	Container container;
	JTextField names[];
	JTextField scores[];
	JTextArea info;
	
	Game game;
	
	public ResultsFrame(Game g)
	{
		super("Wits & Wagers by Lena & Fedor");
		
		game = g;
		
        panel = new JPanel();
        setBounds(10, 10, 1000, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        container.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        initScoreArea();
	}
	
	void initScoreArea()
	{
		info = new JTextArea();
		info.setEditable(false);
		info.setMaximumSize( new Dimension( 600, 60 ) );
		info.setFont(new Font("Verdana", Font.BOLD, 16));
		panel.add(Box.createRigidArea(new Dimension(5, 5)));
		panel.add(info);
		panel.add(Box.createRigidArea(new Dimension(5, 5)));
		
		sPanel = new JPanel[Game.maxPlayers];
		names = new JTextField[Game.maxPlayers];
		scores = new JTextField[Game.maxPlayers];
		for (int i = 0; i<Game.maxPlayers; i++)
		{
			names[i] = new JTextField();
			names[i].setEditable(false);
			names[i].setMaximumSize( new Dimension( 300, 30 ) );
			names[i].setFont(new Font("Verdana", Font.BOLD, 14));
			
			scores[i] = new JTextField();
			scores[i].setEditable(false);
			scores[i].setMaximumSize( new Dimension( 100, 30 ) );
			scores[i].setFont(new Font("Verdana", Font.BOLD, 14));
			
			sPanel[i] = new JPanel();
			sPanel[i].setLayout(new BoxLayout(sPanel[i], BoxLayout.X_AXIS));
			sPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			sPanel[i].add(names[i]);
			sPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			sPanel[i].add(scores[i]);
			sPanel[i].add(Box.createRigidArea(new Dimension(5, 5)));
			panel.add(sPanel[i]);
			panel.add(Box.createRigidArea(new Dimension(5, 5)));
		}
	}

	public void showGameResults() {
		repaint();
		updateScoreTable();
		int maxScore = game.scores.get(0);
		for (int i=1; i< game.names.size(); i++)
		{
			if (game.scores.get(i) > maxScore)
			{
				maxScore = game.scores.get(i);
			}
		}
		
		names[game.myId].setFont(new Font("Verdana", Font.BOLD, 18));
		scores[game.myId].setFont(new Font("Verdana", Font.BOLD, 18));
		
		String answer = "Ответ на последний вопрос игры : ";
		String s = "";
		if (maxScore == game.scores.get(game.myId))
			s = "\n Игра окончена. Вы победили! ";
		else 
			s = "\n Игра окончена. Вы проиграли. ";
		info.setText(answer + Integer.toString(game.rightAnswer) + s);
	}
	
	public void updateScoreTable()
	{
		for (int i =0; i<game.names.size(); i++)
		{
			int score = game.scores.get(i);
			scores[i].setText(Integer.toString(score));
			names[i].setText(game.names.get(i));
		}
	}
}

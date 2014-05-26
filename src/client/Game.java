package client;

import gui.*;

import java.awt.EventQueue;
import java.util.*;

public class Game {
	public static final int maxPlayers = 10;
	public static final int maxRounds = 10;
	
	public State state = State.START_GAME;
	public int nRounds = 0, myId=-1;
	public List<String> names = new ArrayList<String>();
	public List<Integer> answers = new ArrayList<Integer>();
	public List<Integer> scores = new ArrayList<Integer>();
	public int rightAnswer;
	public boolean isRoundWinner = false;
	public String myName;
	
	public Network network = new Network();
	
	public SubmitListener listener = new SubmitListener(this);
	public StartFrame sframe = new StartFrame(this);
	public MainFrame mframe = new MainFrame(this);
	public ResultsFrame rframe = new ResultsFrame(this);
	public ConfigFrame cf = new ConfigFrame(this);
	
	void start()
	{
		state = State.START_GAME;
		cf.setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game = new Game();
					game.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

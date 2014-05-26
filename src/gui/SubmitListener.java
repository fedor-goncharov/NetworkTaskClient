package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.Game;
import client.State;


public class SubmitListener implements ActionListener {
	Game game;
	
	public SubmitListener(Game g)
	{
		game = g;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (game.state) 
		{
		case START_GAME : 
			startGame();
			break;
		case WRITE_ANSWER :
			writeAnswer();
			break;
		case MAKE_STAKE :
			makeStake();
			break;
		default:
			break;
		}
			
		
	}

	private void showGameResults() {
		game.rframe.showGameResults();
		game.mframe.setVisible(false);
		game.rframe.setVisible(true);
		game.rframe.requestFocus();
		
		game.network.finalize();
		//end of game	
	}

	private void startRound()
	{
		game.nRounds++;
		int questionId = game.network.readInt();
		String questionString = game.network.readString();
		
		game.mframe.updateScoreTable();
		game.state = State.WRITE_ANSWER;
		game.mframe.newRound(questionId, questionString);
	}
	
	private void startFirstRound()
	{
		game.nRounds++;
		int questionId = game.network.readInt();
		String questionString = game.network.readString();
		int initialScore = game.network.readInt();
		game.names = game.network.readStringList();
		for (int i=0; i<game.names.size() && i<Game.maxPlayers; i++)
		{
			game.scores.add(initialScore);
		}
		
		game.mframe.firstFillin();
		game.state = State.WRITE_ANSWER;
		game.mframe.newRound(questionId, questionString);
	}

	private void makeStake() 
	{
		try {
			int myAnswer = -1;
			for (int i=0; i<game.answers.size(); i++)
			{
				if (game.mframe.rbuttons[i].isSelected())
				{
					myAnswer = game.answers.get(i);
					break;
				}
			}
			if (myAnswer<0)
				return;
			String stake = game.mframe.myAnswer.getText();
			int num = Integer.parseInt(stake);
			if (num <0 || num > game.scores.get(game.myId))
				return;
			game.network.writeInt(myAnswer);
			game.network.writeInt(num);
			game.mframe.freeze();
			
			//waiting for scores and the right answer from server
			game.isRoundWinner = game.network.readBool();
			game.scores = game.network.readIntList();
			game.rightAnswer = game.network.readInt();
			
			if (game.nRounds == Game.maxRounds)
				showGameResults();
			else
				startRound();
		} catch (Exception e) { return; }
	}

	private void writeAnswer() 
	{
		try {
			String myAnswer = game.mframe.myAnswer.getText();
			int num = Integer.parseInt(myAnswer);
			game.network.writeInt(num);
			game.mframe.freeze();
		} catch (Exception e) { return; }

		//waiting for possible answers from server
		game.answers = game.network.readIntList();
		game.state = State.MAKE_STAKE;
		game.mframe.showAnswers(game.answers);
	}

	private void startGame() 
	{
		game.myName = game.sframe.textField.getText();
		if (game.myName==null || game.myName.equals("")) return;
		game.sframe.setVisible(false);
		game.mframe.setVisible(true);
		
		game.network.writeString(game.myName);
		
		startFirstRound();
	}

}

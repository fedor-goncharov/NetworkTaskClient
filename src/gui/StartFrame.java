package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.Game;

@SuppressWarnings("serial")
public class StartFrame extends JFrame {
	Game game;
	
	JPanel namePanel, panel;
	Container container;
	JLabel label;
	JTextArea rulesArea;
	public JTextField textField;
	JButton button;
	
	public StartFrame(Game g) {
        super("Wits & Wagers by Lena & Fedor");
        
        game = g;
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setBounds(10, 10, 1000, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        container.add(panel);
        
        initNamePanel();
        rulesArea = new JTextArea();
        rulesArea.setMaximumSize(new Dimension(800,500));
        rulesArea.setEditable(false);
        rulesArea.append(getRulesString());
        rulesArea.setAlignmentX(CENTER_ALIGNMENT);
        rulesArea.setFont(new Font("Verdana", Font.BOLD, 14));
        rulesArea.setLineWrap(true);
        rulesArea.setWrapStyleWord(true);
        
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
        panel.add(namePanel);
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
        panel.add(rulesArea);
    }
	
	private String getRulesString() {
		return "Правила игры Wits & Wagers\n\n"
				+ "1) На экране появляется вопрос, ответ на который - положительное целое число\n\n"
				+ "2) В течение 30 секунд Вам необходимо написать свой ответ\n\n"
				+ "3) На экране появятся ответы других игроков (в том числе и Ваш собственный). "
				+ "Вам нужно выбрать ответ, по вашему мнению, наиболее близкий к правильному,"
				+ "но не превышающий его. И затем "
				+ "сделать ставку. Ставка - это число ваших очков, которое вы ставите на то,  "
				+ "что выбранный вами вариант верный. В случае выигрыша ставка удваивается, в противном случае вы лишаетесь очков. \n"
				+ "Пример : вы ставите 7 очков на ответ\n"
				+ "\t\t вы выигрываете, сумма ваших очков увеличивается на 7\n"
				+ "\t\t вы проигрываете, сумма ваших очков умньшается на 7\n\n"
				+ "и начинается новый рануд\n\n"
				+ "В игре 10 раундов, изначально у каждого игрока по 10 очков\n"
				+ "Желаем удачи!";
	}

	void initNamePanel()
	{
		label = new JLabel("Name : ", JLabel.LEFT);
		textField = new JTextField();
		textField.setMaximumSize( new Dimension( 200, 24 ) );
        button = new JButton("Start");
        button.addActionListener(game.listener);
        
        namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(label);
		namePanel.add(textField);
		namePanel.add(button);
	}

}

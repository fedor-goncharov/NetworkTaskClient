package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Game;

@SuppressWarnings("serial")
public class ConfigFrame extends JFrame {
	Game game;
	
	JPanel panel;
	Container container;
	public JTextField serverIp, serverPort;
	JButton button;
	
	public ConfigFrame(Game g) {
        super("Wits & Wagers by Lena & Fedor");
        
        game = g;
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        setBounds(300, 150, 400, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = getContentPane();
        container.add(panel);
        
        serverIp = new JTextField();
        serverIp.setMaximumSize( new Dimension( 200, 24 ) );
        serverIp.setText("server ip");
        serverPort = new JTextField();
        serverPort.setMaximumSize( new Dimension( 200, 24 ) );
        serverPort.setText("server port");
        button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                try {
                	String ip = serverIp.getText();
                	String port = serverPort.getText();
                	
                	if (ip.equals("") || port.equals(""))
                		return;
                	
                	game.network.hostName = ip;
                	game.network.portNumber = Integer.parseInt(port);
                	game.network.init();
                	
                	game.cf.setVisible(false);
                	game.sframe.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
        
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
        panel.add(serverIp);
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
        panel.add(serverPort);
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(20, 20)));
    }

}

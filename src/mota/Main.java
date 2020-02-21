package mota;

import static mota.GameCenter.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.lang.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static mota.Fight.*;
import static mota.FlyingFortress.*;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyListener.*;
import static mota.MapData.*;
import static mota.SendMessage.*;

import javax.swing.*;
/**
 * @author MikuSakula
 * 
 *
 */
public class Main {	
	
	public static InterAction Railgun = new InterAction();
	public static void main(String[] args) {
		gamePanel = new GameCenter();
		gamePanel.setPreferredSize(new Dimension(GAME_PIX_72*18,GAME_PIX_72*13));
		
		gamePanel.add(flyFortressLPane);
		gamePanel.add(battleLPane);
		gamePanel.add(timeLabel);
		gamePanel.add(msgLPane);
		keyboardListen();	
		
		gameFrame.setContentPane(gamePanel);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void keyboardListen(){
		gameFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(!GameCenter.inConversation) {
					switch(e.getKeyCode()) {
					case VK_DOWN:
						if(Misaka.canDown()) {
							Misaka.setToword(1);
							//move
							Railgun.setInterAction(LevelMap[currentFloor][Misaka.getPosY()+1][Misaka.getPosX()],
									Misaka.getPosX(), Misaka.getPosY()+1);
						//	System.out.println(Misaka.getToword());
							GameCenter.gameFrame.repaint();
						}
						break;
					case VK_UP:
						if(Misaka.canUp()) {
							Misaka.setToword(3);
						//	System.out.println(currentFloor);

							Railgun.setInterAction(LevelMap[currentFloor][Misaka.getPosY()-1][Misaka.getPosX()],
									Misaka.getPosX(), Misaka.getPosY()-1);
						//	System.out.println(Misaka.getToword());

							GameCenter.gameFrame.repaint();
						}
						break;
					case VK_RIGHT:
						if(Misaka.canRight()) {
							Misaka.setToword(2);
							// move
							Railgun.setInterAction(LevelMap[currentFloor][Misaka.getPosY()][Misaka.getPosX()+1],
									Misaka.getPosX()+1, Misaka.getPosY());
						//	System.out.println(Misaka.getToword());

							GameCenter.gameFrame.repaint();
						}
						break;
					case VK_LEFT:
						if(Misaka.canLeft()) {
							Misaka.setToword(0);
							// move
							Railgun.setInterAction(LevelMap[currentFloor][Misaka.getPosY()][Misaka.getPosX()-1],
									Misaka.getPosX()-1, Misaka.getPosY());
						//	System.out.println(Misaka.getToword());

							GameCenter.gameFrame.repaint();							
						}
						break;
					case VK_J:
				        inConversation = true;
				 //       System.out.println(inConversation);
				        if(Misaka.isCanUseFlyingFortress()) 
				        	displayJump();
				     //   inConversation = false;
						break;
					case VK_L:
						break;
					}
					LevelMap[18][8][5] = 0;
					LevelMap[18][9][5] = 0;
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {		}
		});
		
	
	}
}

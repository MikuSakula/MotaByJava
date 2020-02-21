package mota;
import java.io.*;
import java.util.*;
import java.lang.*;
import static mota.Character.*;
import static mota.MapData.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.Timer;

public class GameCenter extends JPanel{
	
	public static final int GAME_PIX_54 = 54;
    public static final int GAME_PIX_72 = 72;
    
	public static JFrame gameFrame;
	public static JPanel gamePanel;
	
	
//	public static JLabel gameLabel;
	public static JLabel timeLabel;
	
	public static Character Misaka = new Character();
    public static HashMap<Integer, BufferedImage> imgSource = Map.imagesMap0;     // 用于帧数切换

	public static int gameMin = 0;
    public static double gameSec = 0;

    public static boolean inConversation = false;   // 允许键盘交互
    public static boolean talked = false;
    public static int currentFloor = 0;     // 当前楼层
    public static int maxFloor = 23;         // 最大楼层
    
    
    
	public GameCenter() {
		setLayout(null);
		gameFrame = new JFrame("Mota v1.12 created by Java  ——  MikuSakula");
	
		timeLabel = new JLabel();
	    timeLabel.setBounds(80, 800, 350, 100);
	    timeLabel.setForeground(Color.WHITE);

	    timeLabel.setFont(new Font("Serif", 0, 25));
		
        new Timer(250,new ActionListener(){
    		boolean change = true;

        	public void actionPerformed(ActionEvent e) {
        		GameCenter.gameSec+=0.25;
        		if(gameSec == 60) {
        			gameSec = 0;
        			gameMin++;
        		}
        		GameCenter.timeLabel.setText(" Game Time："+gameMin +" 分 "+(int)gameSec + " 秒");
        		if(change) {
        			change = false;
        			imgSource = Map.imagesMap0;
        		}else {
        			change = true;
        			imgSource = Map.imagesMap1;
        		}
        		repaint();	
        		
        	}
        }).start();
	
	}
	
    @Override

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // 绘制游戏主背景
        g2.drawImage(Map.gameBgImg, 0, 0, null);

        // 绘制 地图数据
        for (int x = 0; x < 11; x++) {
            for (int y = 0; y < 11; y++) {
                int id = LevelMap[currentFloor][x][y];
                BufferedImage a = imgSource.get(id);
                g2.drawImage(a, GAME_PIX_72 * y + GAME_PIX_72 * 6, GAME_PIX_72 * x + GAME_PIX_72, null);
            }
        }

      //   绘制 玩家
        g2.drawImage(Misaka.draw(), (Misaka.getPosX() + 6) * GAME_PIX_72, (Misaka.getPosY() + 1) * GAME_PIX_72, null);

        // 绘制 左侧面板的玩家数据
        g2.setFont(new Font("Arial", 0, 30));
        g2.setColor(Color.WHITE);
        g2.drawString(Misaka.getNowLevel() + "", 230, 150);
        g2.drawString(Misaka.getHitPoint() + "", 220, 215);
        g2.drawString(Misaka.getAttackPower() + "", 220, 270);
        g2.drawString(Misaka.getDefensivePower() + "", 220, 325);
        g2.drawString(Misaka.getCoin() + "", 220, 385);
        g2.drawString(Misaka.getExperience() + "", 220, 440);

        // 绘制 各种钥匙数目
        g2.drawString(Misaka.getYellowKey() + "", 240, 530);
        g2.drawString(Misaka.getBlueKey() + "", 240, 605);
        g2.drawString(Misaka.getRedKey() + "", 240, 680);

        // 绘制 当前楼层
        g2.drawString(currentFloor + "", 200, 750);
    }
	
}


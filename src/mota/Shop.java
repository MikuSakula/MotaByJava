package mota;


import javax.swing.ImageIcon;

import static mota.GameCenter.GAME_PIX_72;
import static mota.Utils.imgIco;
import static mota.Utils.*;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static mota.GameCenter.*;

import mota.GameCenter;

public class Shop {
	 private static String[] choice = new String[4];
	 public static void shop(int id) {
	        switch (id) {
	            case 0:     // 第 3 层商店
	                choice = new String[]{"▶增加 800 点生命（25 金币）", "▷增加 4 点攻击（25 金币）", "▷增加 4 点防御（25 金币）", "▷离开商店"};
	                imgIco.setIcon(new ImageIcon(Map.imagesMap0.get(22)));
	                break;
	            case 1:     // 第 5 层 神秘老人
	                choice = new String[]{"▶提升一级（需要 100 点）", "▷增加攻击5（需要 30 点） ", "▷增加防御5（需要 30 点）", "▷离开商店"};
	                imgIco.setIcon(new ImageIcon(Map.imagesMap0.get(26)));
	                break;
	            case 2:     // 第 5 层 商人
	                choice = new String[]{"▶购买 1 把黄钥匙（$ 10）", "▷购买 1 把蓝钥匙（$ 50）", "▷购买 1 把红钥匙（$ 100）", "▷离开商店"};
	                imgIco.setIcon(new ImageIcon(Map.imagesMap0.get(27)));
	                break;
	            case 3:     // 第 11 层 商店
	                choice = new String[]{"▶增加 4000 点生命（100 金币）", "▷增加 20 点攻击（100 金币）", "▷增加 20 点防御（100 金币）", "▷离开商店"};
	                imgIco.setIcon(new ImageIcon(Map.imagesMap0.get(22)));
	                break;
	            case 4:     // 第 12 层 商人
	                choice = new String[]{"▶卖出 1 把黄钥匙（$ 7）", "▷卖出 1 把黄钥匙（$ 35）", "▷卖出 1 把黄钥匙（$ 70）", "▷离开商店"};
	                imgIco.setIcon(new ImageIcon(Map.imagesMap0.get(27)));
	                break;
	            case 5:     // 第 13 层 神秘老人
	                choice = new String[]{"▶提升三级（需要 270 点）", "▷增加攻击 17（需要 95 点）", "▷增加防御 17（需要 95 点）", "▷离开商店"};
	                imgIco.setIcon(new ImageIcon(Map.imagesMap0.get(26)));
	                break;
	        }
	        Insets insets = dialogLPane.getInsets();
	        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);
	        text.setBounds(100 + insets.left, 20 + insets.top, 650 - 50, 250);
	        text.setText("<W>/<S>选择，<SPACE>购买 \n\n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3]);
	        text.setFont(new Font("Serif", 0, 25));
	        dialogBgImg.setBounds(0, 0, 550, 250);
	        dialogLPane.setBounds(550, 230, 550, 250);
	        dialogLPane.add(imgIco, 2, 0);
	        dialogLPane.add(text, 3, 0);
	        
	        GameCenter.gamePanel.add(dialogLPane);
	        GameCenter.gamePanel.repaint();
	        
	        GameCenter.gameFrame.addKeyListener(new KeyListener() {
	            int selection = 0;
	            String message = "<W>/<S>选择，<SPACE>购买 \n\n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];

	            @Override
	            public void keyTyped(KeyEvent e) {	            }

	            @Override
	            public void keyPressed(KeyEvent e) {
	            	
	            	 if (selection != 3 && e.getKeyCode() == e.VK_S) {
	                     choice[selection] = choice[selection].replaceAll("▶", "▷");
	                     selection = selection + 1;
	                     choice[selection] = choice[selection].replaceAll("▷", "▶");
	                     message = "<W>/<S>选择，<SPACE>购买 \n\n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
	                     text.setText(message);
	                     GameCenter.gameFrame.repaint();
	                 }
	                 if (selection != 0 && e.getKeyCode() == e.VK_W) {
	                     choice[selection] = choice[selection].replaceAll("▶", "▷");
	                     selection = selection - 1;
	                     choice[selection] = choice[selection].replaceAll("▷", "▶");
	                     message = "<W>/<S>选择，<SPACE>购买 \n\n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
	                     text.setText(message);
	                     GameCenter.gameFrame.repaint();
	                 }
	                 if(e.getKeyCode() == e.VK_SPACE) {
	                	 switch (id) {
	                        case 0:     // 对应 3 楼的商店选项
	                            switch (selection) {
	                                case 0:
	                                    if (Misaka.getCoin() >= 25) {
	                                        Misaka.setCoin(- 25);
	                                        Misaka.setHitPoint(800);
	                                    }
	                                    break;
	                                case 1:
	                                    if (Misaka.getCoin() >= 25) {
	                                        Misaka.setCoin(- 25);
	                                        Misaka.setAttackPower(4);
	                                    }
	                                    break;
	                                case 2:
	                                    if (Misaka.getCoin() >= 25) {
	                                        Misaka.setCoin(- 25);
	                                        Misaka.setDefensivePower(4);
	                                    }
	                                    break;
	                                case 3:
	                                    dialogLPane.remove(imgIco);
	                                    dialogLPane.remove(text);
	                                    gamePanel.remove(dialogLPane);
	                                    gameFrame.repaint();
	                                    inConversation = false;
	                                    gameFrame.removeKeyListener(this);
	                                    break;
	                            }
	                            break;
	                        case 1:     // 对应 5 楼的老人选项
	                            switch (selection) {
	                                case 0:
	                                    if (Misaka.getExperience() >= 100) {
	                                        Misaka.setNowLevel(1);
	                                        Misaka.setExperience(100);	                                        
	                                    }
	                                    break;
	                                case 1:
	                                    if (Misaka.getExperience() >= 30) {
	                                        Misaka.setExperience( 30);
	                                        Misaka.setAttackPower(5);
	                                    }
	                                    break;
	                                case 2:
	                                    if (Misaka.getExperience() >= 30) {
	                                        Misaka.setExperience(30);
	                                        Misaka.setDefensivePower(5);
	                                    }
	                                    break;
	                                case 3:
	                                    dialogLPane.remove(imgIco);
	                                    dialogLPane.remove(text);
	                                    gamePanel.remove(dialogLPane);
	                                    gameFrame.repaint();
	                                    inConversation = false;
	                                    gameFrame.removeKeyListener(this);
	                                    break;
	                            }
	                            break;
	                        case 2:     // 对应 5 楼的商人选项
	                            switch (selection) {
	                                case 0:
	                                    if (Misaka.getCoin() >= 10) {
	                                        Misaka.setCoin(-10);
	                                        Misaka.setYellowKey(1);
	                                    }
	                                    break;
	                                case 1:
	                                    if (Misaka.getCoin() >= 50) {
	                                        Misaka.setCoin( -50);
	                                        Misaka.setBlueKey(1);
	                                    }
	                                    break;
	                                case 2:
	                                    if (Misaka.getCoin() >= 100) {
	                                        Misaka.setCoin(-100);
	                                        Misaka.setRedKey(1);
	                                    }
	                                    break;
	                                case 3:
	                                    dialogLPane.remove(imgIco);
	                                    dialogLPane.remove(text);
	                                    gamePanel.remove(dialogLPane);
	                                    gameFrame.repaint();
	                                    inConversation = false;
	                                    gameFrame.removeKeyListener(this);
	                                    break;
	                            }
	                            break;
	                        case 3:     // 对应 11 楼的商店选项
	                            switch (selection) {
	                                case 0:
	                                    if (Misaka.getCoin() >= 100) {
	                                        Misaka.setCoin( -100);
	                                        Misaka.setHitPoint(4000);
	                                    }
	                                    break;
	                                case 1:
	                                    if (Misaka.getCoin() >= 100) {
	                                        Misaka.setCoin( -100);
	                                        Misaka.setAttackPower(20);
	                                    }
	                                    break;
	                                case 2:
	                                    if (Misaka.getCoin() >= 100) {
	                                        Misaka.setCoin( -100);
	                                        Misaka.setDefensivePower(20);
	                                    }
	                                    break;
	                                case 3:
	                                    dialogLPane.remove(imgIco);
	                                    dialogLPane.remove(text);
	                                    gamePanel.remove(dialogLPane);
	                                    gameFrame.repaint();
	                                    inConversation = false;
	                                    gameFrame.removeKeyListener(this);
	                                    break;
	                            }
	                            break;
	                        case 4:     // 对应12楼的商人选项
	                            switch (selection) {
	                                case 0:
	                                    if (Misaka.getYellowKey() > 0) {
	                                        Misaka.setYellowKey(-1 );
	                                        Misaka.setCoin(7);
	                                    }
	                                    break;
	                                case 1:
	                                    if (Misaka.getBlueKey() > 0) {
	                                        Misaka.setBlueKey( -1 );
	                                        Misaka.setCoin(35);
	                                    }
	                                    break;
	                                case 2:
	                                    if (Misaka.getRedKey() > 0) {
	                                        Misaka.setRedKey( -1 );
	                                        Misaka.setCoin(70);
	                                    }
	                                    break;
	                                case 3:
	                                    dialogLPane.remove(imgIco);
	                                    dialogLPane.remove(text);
	                                    gamePanel.remove(dialogLPane);
	                                    gameFrame.repaint();
	                                    inConversation = false;
	                                    gameFrame.removeKeyListener(this);
	                                    break;
	                            }
	                            break;
	                            
	                        case 5:// 对应13楼的商人选项
	                            switch (selection) {
	                                case 0:
	                                    if (Misaka.getExperience() >= 270) {
	                                        Misaka.setNowLevel(3);
	                                        Misaka.setExperience(-270);
	                                       
	                                    }
	                                    break;
	                                case 1:
	                                    if (Misaka.getExperience() >= 95) {
	                                        Misaka.setExperience(-95);
	                                        Misaka.setAttackPower(17);
	                                    }
	                                    break;
	                                case 2:
	                                    if (Misaka.getExperience() >= 95) {
	                                        Misaka.setExperience(- 95);
	                                        Misaka.setDefensivePower( 17);
	                                    }
	                                    break;
	                                case 3:
	                                    dialogLPane.remove(imgIco);
	                                    dialogLPane.remove(text);
	                                    gamePanel.remove(dialogLPane);
	                                    gameFrame.repaint();
	                                    inConversation = false;
	                                    gameFrame.removeKeyListener(this);
	                                    break;
	                            }
	                            break;
	                	 }
	            	
	                 }	            	
	            
	            }
	            public void keyReleased(KeyEvent e) {	            }
	        });

	 }
	 
}

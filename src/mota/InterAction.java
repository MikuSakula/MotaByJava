package mota;

import static mota.GameCenter.*;
import static mota.MapData.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.lang.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyListener.*;
import javax.swing.*;


public class InterAction{
	private int id ;
	private int posX;
	private int posY;
	private boolean whetherMovedAngle = false;
	public InterAction() {	}
	public void setInterAction(int id,int x,int y) {
		this.id = id;
		this.posX = y;
		this.posY = x;
		switch (this.id){
		case 0:  // 空地
			Misaka.move(x, y);
			break;
		case 1:   // 墙
			break;
		case 2:  // 黄门
			if(Misaka.getYellowKey()>0) {
				LevelMap[currentFloor][posX][posY]=0;
				Misaka.setYellowKey(-1);
			}
			break;
		case 3:  // 蓝门
			if(Misaka.getBlueKey()>0) {
				LevelMap[currentFloor][posX][posY]=0;
				Misaka.setBlueKey(-1);
			}
			break;  
		case 4:  // 红门
			if(Misaka.getRedKey()>0) {
				LevelMap[currentFloor][posX][posY]=0;
				Misaka.setRedKey(-1);
			}
			break;
		case 5:  // 地狱之门！！！
			//  you can't enter it if you don't talk to the thief at least once .
			break;
		case 6:   // 黄钥匙
			new GetThingsMessage().getYellowKey();
			Misaka.setYellowKey(1);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 7:  // 蓝钥匙
			new GetThingsMessage().getBlueKey();
			Misaka.setBlueKey(1);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 8:  // 红钥匙
			new GetThingsMessage().getRedKey();
			Misaka.setRedKey(1);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 9:  // 蓝宝石
			new GetThingsMessage().getBlueGem();
			Misaka.setDefensivePower(3);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 10:  // 红宝石
			new GetThingsMessage().getRedGem();
			Misaka.setAttackPower(3);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 11:  // 红药水
			new GetThingsMessage().getRedHpBottle();
			Misaka.setHitPoint(250);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 12:  // 蓝药水
			new GetThingsMessage().getBlueHpBottle();
			Misaka.setHitPoint(500);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 13:  // 上楼梯
			currentFloor++;
			maxFloor = Math.max(maxFloor, currentFloor);
		//	System.out.println(currentFloor);
			Misaka.setPosX(initPos[currentFloor][0]);
			Misaka.setPosY(initPos[currentFloor][1]);
			break;
		case 14:  // 下楼梯
			currentFloor--;
			//	System.out.println(currentFloor);
			Misaka.setPosX(finPos[currentFloor][0]);
			Misaka.setPosY(finPos[currentFloor][1]);
			break;
		case 15:  // 牢笼 == 墙 --> 触发
			if(currentFloor==7) {
				if(posX == 4 &&posY ==4) {
					LevelMap[currentFloor][posX][posY]=0;
				}
			}else if(currentFloor == 13){
				if(posX==6) {
					LevelMap[currentFloor][posX][posY]=0;
				}
			}else if(currentFloor == 21){
				if(Misaka.isKillTheBossInTwentyOne()==true) {
					LevelMap[currentFloor][posX][posY]=0;
				}
			}else if(currentFloor == 22){
				break;
			}else {
			
				LevelMap[currentFloor][posX][posY]=0;
			//	Misaka.move(x, y);
			}
			break;
		case 19:  // 红海 == 墙
			break;
		case 20: // 星空 == 墙
			// If you are in the "starry sky" , I will be glad to tell you encounter a bug 
			// and you can't leave there ,so exit it .
			break;
		case 21: // 商店右
			// Don't do anything
			break;
		case 22:  // 商店
			inConversation = true;
			if(currentFloor == 3)
				Shop.shop(0);
			else
				Shop.shop(3);
			break;
		case 23:  // 商店左
			// Don't do anything
			break;
		case 24: // 天使小蜜蜂			
			if(!whetherMovedAngle) {
				inConversation = true;
				new Message(id,id);
				LevelMap[currentFloor][posX][posY-1]=LevelMap[currentFloor][posX][posY];
				LevelMap[currentFloor][posX][posY]=0;
			//	System.out.println(LevelMap[currentFloor][posX][posY]);
				Misaka.setBlueKey(1);
				Misaka.setYellowKey(1);
				Misaka.setRedKey(1);
				// send
				whetherMovedAngle = true;
			}else {
				if(Misaka.isMeetTheOldMan() == true && GameCenter.currentFloor == 0 ){
					inConversation = true;
					new Message(id,2);					
				}				
				else if(Misaka.isHasTen() == true && GameCenter.currentFloor == 0) {
					inConversation = true;
					new Message(id,1);		
					LevelMap[20][7][5]=13;
					Misaka.setHasTen(false);
					Misaka.setAttackPower((int)(Misaka.getAttackPower()/3.0));
					Misaka.setDefensivePower((int)(Misaka.getDefensivePower()/3.0));
					Misaka.setHitPoint((int)(Misaka.getHitPoint()/3.0));
					if(Misaka.isMeetTheOldMan()==true)
						LevelMap[0][8][4]=0;
					
				}
			}
			break;
		case 25: // 小偷
			if(Misaka.getChangeTheThiefTimes() == 1 && Misaka.isNeedSayToThief()) {
				inConversation = true;
				new Message(id,12);
				
				Misaka.setNeedSayToThief(false);
				LevelMap[2][6][1] = 0;
			}
			if(Misaka.getChangeTheThiefTimes() == 2 && Misaka.isNeedSayToThief()) {
				inConversation = true;
				new Message(id,26);
				
				Misaka.setNeedSayToThief(false);
				LevelMap[18][8][5] = 0;
				LevelMap[18][9][5] = 0;
				LevelMap[currentFloor][posX][posY]=0;
			}
			break;
		case 26:  // 经验老头
			if(currentFloor == 2) {
				inConversation = true;
				new Message(id,6);
				Misaka.setAttackPower(30);				
				LevelMap[currentFloor][posX][posY]=0;
			}else if(currentFloor == 5) {
				inConversation = true;
				Shop.shop(1);
			}else if(currentFloor == 13) {
				inConversation = true;
				Shop.shop(5);
			}else if(currentFloor == 15) {
				inConversation = true;
				if(Misaka.getExperience()>=500) {
					new Message(id,4);
					LevelMap[currentFloor][posX][posY]=0;
				}else {
					new Message(id,3);
				}
			}else { // 16
				inConversation = true;
				new Message(id,5);
				Misaka.setMeetTheOldMan(true);
				LevelMap[currentFloor][posX][posY]=0;
			}
			break;
		case 27:  // 奸商钥匙老头
			if(currentFloor == 2) {
				inConversation = true;
				new Message(id,22);
				Misaka.setDefensivePower(30);				
				LevelMap[currentFloor][posX][posY]=0;
			}else if(currentFloor == 5) {
				inConversation = true;
				Shop.shop(2);
			}else if(currentFloor == 12) {
				inConversation = true;
				Shop.shop(4);
			}else {  // 15
				inConversation = true;
				if(Misaka.getCoin()>=500) {
					new Message(id,8);
					LevelMap[currentFloor][posX][posY]=0;
				}else {
					new Message(id,7);
				}
			}
			break;
		case 28: // 被SM R18的公主
			inConversation = true;
			new Message(id,19);
			break;
		case 30:  // 小飞羽
			new GetThingsMessage().getSmallFlyFeather();
			Misaka.setNowLevel(1);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 31:  //大飞羽
			new GetThingsMessage().getBigFlyFeather();
			Misaka.setNowLevel(3);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 32: // 十字架
			//
			new GetThingsMessage().getTheCross();
			Misaka.setHasTen(true);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 33:  // 圣水瓶
			new GetThingsMessage().getTheGodBottle();
			Misaka.setHitPoint(Misaka.getHitPoint());
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 34:  // 圣光徽
			// L
			new GetThingsMessage().getHolyLightBook();
			Misaka.setCanUseHolyLightBook(true);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 35: // 飞行堡垒
			new GetThingsMessage().getFlyingFortress();
			Misaka.setCanUseFlyingFortress(true);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 36:    // 钥匙盒
			new GetThingsMessage().getKeyBox();
			Misaka.setYellowKey(1);
			Misaka.setBlueKey(1);
			Misaka.setRedKey(1);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 38:  // 十字镐
			new GetThingsMessage().getThePick();
			Misaka.setHasPick(true);
			Misaka.setNeedSayToThief(true);
			Misaka.setChangeTheThiefTimes(2);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 39:  // 大金币
			new GetThingsMessage().getBigCoins();
			Misaka.setCoin(300);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 40:  //怪物
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
		case 59:
		case 60:
		case 61:
		case 62:
		case 63:
		case 64:
		case 65:
		case 66:
		case 67:
		case 68:
		case 69:
		case 70:
			new Fight(this.id,posX,posY) ;
			break;

		case 71:  // 铁剑
			Misaka.setAttackPower(10);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 73:  // 石中剑
			Misaka.setAttackPower(50);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 75:  // 电光剑
			Misaka.setAttackPower(100);
			LevelMap[currentFloor][posX][posY]=0;
			break;

		case 76:  // 铁盾
			Misaka.setDefensivePower(10);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 78: // 圣物之盾
			Misaka.setDefensivePower(50);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 80:  // 电磁之盾
			Misaka.setDefensivePower(100);
			LevelMap[currentFloor][posX][posY]=0;
			break;
		case 188:
		case 198:
			new Fight(this.id,posX,posY) ;
			break;
		case 202:
			break;
		case 203:
			break;
		case 301:  // 上24楼梯
			currentFloor++;
			maxFloor = Math.max(maxFloor, currentFloor);
		//	System.out.println(currentFloor);
			Misaka.setPosX(initPos[currentFloor][0]);
			Misaka.setPosY(initPos[currentFloor][1]);
			break;
		case 302:  // 上25楼梯
			currentFloor++;
			maxFloor = Math.max(maxFloor, currentFloor);
		//	System.out.println(currentFloor);
			Misaka.setPosX(initPos[currentFloor][0]);
			Misaka.setPosY(initPos[currentFloor][1]);
			break;
		case 401:  // 下24楼梯
			currentFloor--;
			//	System.out.println(currentFloor);
			Misaka.setPosX(finPos[currentFloor][0]);
			Misaka.setPosY(finPos[currentFloor][1]);
			break;
		case 402:  // 下25楼梯
			currentFloor--;
			//	System.out.println(currentFloor);
			Misaka.setPosX(finPos[currentFloor][0]);
			Misaka.setPosY(finPos[currentFloor][1]);
			break;
		
		

		}
	}
	public void moveUp() {
		
	}

	public void moveDown() {
		
	}
	public void moveRight() {
	
	}
	public void moveLeft() {
		
	}
}
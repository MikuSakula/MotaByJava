package mota;

import static mota.Message.id;

public class GetThingsMessage {
	public GetThingsMessage() { }
	public GetThingsMessage(int id) {
		if(id == 4) {
	   		SendMessage.displayMessage("获得破败之刃，攻击力增加120 ！");
	   	}
		if(id == 6) {
			SendMessage.displayMessage("获得无限之刃，攻击力增加30 ！");
		}	   	
	}
	public void getYellowKey() {
		SendMessage.displayMessage("获得黄钥匙，黄钥匙数量增加1 ！");
	}
	public void getBlueKey() {
		SendMessage.displayMessage("获得蓝钥匙，蓝钥匙数量增加1 ！");
	}
	public void getRedKey() {
		SendMessage.displayMessage("获得红钥匙，红钥匙数量增加1 ！");
	}
	public void getRedGem() {
		SendMessage.displayMessage("获得红宝石，攻击力增加3 ！");
	}
	public void getBlueGem() {
		SendMessage.displayMessage("获得蓝宝石，防御力增加3 ！");
	}
	public void getRedHpBottle() {
		SendMessage.displayMessage("获得红血瓶，生命值增加250 ！");
	}
	public void getBlueHpBottle() {
		SendMessage.displayMessage("获得蓝血瓶，生命值增加500 ！");
	}
	public void getKeyBox() {
		SendMessage.displayMessage("获得钥匙盒，各色钥匙数量增加1 ！");
		
	}
	public void getSmallFlyFeather() {
		SendMessage.displayMessage("获得小飞羽，等级提升一级 ！");
	}
	public void getBigFlyFeather() {
		SendMessage.displayMessage("获得大飞羽，等级提升三级 ！");
	}
	public void getTheCross() {
		SendMessage.displayMessage("获得十字架 ！");
	}
	public void getTheGodBottle() {
		SendMessage.displayMessage("获得圣水瓶，生命值增加一倍 ！");
	}
	public void getHolyLightBook() {
		SendMessage.displayMessage("获得圣光徽，按<L>可查看当前楼层怪物信息 ！");
	}
	public void getFlyingFortress() {
		SendMessage.displayMessage("获得飞行堡垒，按<J>可以移动到已达楼层 ！");

	}
	public void getBigCoins() {
		SendMessage.displayMessage("获得大金币，金币数增加300 ！");
	}
	public void getThePick() {
		SendMessage.displayMessage("获得星光神琅 ！");
	}
	public void RefuseToFight() {
		SendMessage.displayMessage("怪物嫌你战斗力过低，拒绝战斗 ！");
	}
}

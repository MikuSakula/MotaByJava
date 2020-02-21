# MotaByJava
Java Swing 魔塔

受 https://github.com/gdut-yy/MagicTower 启发而存储地图

关于魔塔：
1、总共共分为16个类

   character 角色信息类	
   
   Fight 战斗类
   
   Flyingfortress 飞行堡垒类
   
   Gamecenter 游戏中心类
   
   Getmonster 初始化怪物信息
   
   Getthingsmessage 获取物品的弹窗信息
   
   Interaction 行为类，触发事件
   
   Main 主函数
   
   Map 导入地图并编号
   
   Mapdata 把map中的元素按每个楼层摆好，可以自制地图
   
   Message 游戏内置的固定消息
   
   Monster 怪物的信息类
   
   Sendmessage 发送消息类
   
   Shop 商店类
   
   Utils 调用发送消息
 
 2、随意修改和创建MapData类即可自制地图
 
 3、道具名称已重置
 
 4、角色属性可以在character类中修改，怪物属性在monster类中
 
 5、优化了对战中时延问题，保证最多20秒结束战斗，防止50000HP /（10000-9999）这样的情况导致一百年也结束不了
 
 6、增加了不同怪物所触发的事件，包括幽灵法师扣除1/3血量和扫把人固定扣血
 
 7、增加了侮辱机制，当打不过怪物还要硬刚的时候，会嘲讽你
 
 8、部分功能仍为完成，敬请期待

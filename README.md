# DistributedExercise01
## 结构图

![image](https://user-images.githubusercontent.com/75611394/223910983-3702ff6a-a0f3-407a-9c3f-ec5e1e80841a.png)
## 结构说明		
#### OrderServer 提供的接口		
	#### 用户信息的登录	
		#### 用户信息保存到OrderDB
		#### 用户信息保存到Redis
		#### 通过RocketMQ发布消息（用户信息）
	#### 用户信息的更新	
		#### 用户信息保存到OrderDB
		#### 用户信息更新到Redis
		#### 通过RocketMQ发布消息（用户信息）
		
#### AccountServer 提供的接口		
	#### Order的创建	
		#### 用户信息的验证
		#### 订单创建
	#### 接收消息	
## 流程说明		
		#### 用户信息的登录
		#### 用户信息的更新
![image](https://user-images.githubusercontent.com/75611394/223911002-7f1f99dc-35b7-453c-b3c0-4a923986e78c.png)
		#### Order的创建
![image](https://user-images.githubusercontent.com/75611394/223911014-a6f352e0-9c66-4efe-a23e-a273575eabfc.png)

## dubbox与swagger集成
实现了dubbox与swagger集成，可以使用SwaggerUI在线测试rest接口，同时顺便升级了dubbox依赖的resteasy/jackson等库。


### 环境准备
git、jdk1.7+、maven4+、zookeeper、tomcat7+、Chrome浏览器


### 获取源码
源码在：https://github.com/kimmking/dubbo/tree/swaggerui

```
git clone https://github.com/kimmking/dubbo
git checkout -b swaggerui origin/swaggerui
```

### 编译
切换到源码根目录dubbo下，使用mvn编译


```
cd dubbo
mvn package install -DskipTests

```

1. 编译完成后，在dubbo\dubbo-demo\dubbo-demo-provider\target文件夹下，可以看到打包好的war：dubbo-demo-provider-2.8.4.war；
2. 删除tomcatwebapps下的所有文件，将dubbo-demo-provider-2.8.4.war复制过来，改名为ROOT.war；
3. tomcat端口号改为80；
4. 启动zookeeper，再启动tomcat；


## 测试
1. chrome浏览器启动参数加上： --args --disable-web-security --user-data-dir
2. 启动chrome，在chrome地址栏输入http://localhost/swagger/，即可测试rest

## screenshot

![image](https://github.com/kimmking/kk/blob/master/images/dubbo/01.png?raw=true)

![image](https://github.com/kimmking/kk/blob/master/images/dubbo/02.png?raw=true)

[windows平台mongoDB安装配置]

一.**首先安装mongodb**

1.官网下载mongoDB：https://www.mongodb.com/download-center/community?jmp=nav，选择windows平台。安装时，一路next就可以了。我安装在了F:\mongoDB目录下。

2.建立工作目录：

- 在mongoDB目录下新建数据存放目录：F:\mongoDB\data\db
- 在mongoDB目录下新建日志文件：F:\mongoDB\log\mongodb.log

2.建立工作目录：

- 在mongoDB目录下新建数据存放目录：F:\mongoDB\data\db
- 在mongoDB目录下新建日志文件：F:\mongoDB\log\mongodb.log

3.运行cmd.exe（以管理员身份打开），进入到F:\mongoDB\bin目录下，执行以下命令

　　> F:\mongoDB\bin>mongod -dbpath "F:\mongoDB\data\db"

　 若启动成功，会显示mongoDB默认的监听端口：27017，mysql的是3306

　　在浏览器中输入<http://localhost:27017/>。会出现：

　　It looks like you are trying to access MongoDB over HTTP on the native driver port.

　　表明服务已经启动。

4.测试连接

　　新开一个cmd窗口，进入mongodb的bin目录，输入mongo或者mongo.exe，出现如下信息说明测试通过，此时我们已经进入了test这个数据库。（前提：打开bin目录下的mongod.exe）

![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910144737832-1363597436.png)

5.当mongod.exe被关闭时，mongo.exe 就无法连接到数据库了，因此每次想使用mongodb数据库都要开启mongod.exe程序，所以比较麻烦，此时我们可以将MongoDB安装为windows服务

 　　还是运行cmd，进入bin文件夹，执行下列命令

　　> d:\mongoDB\bin>mongod --dbpath "d:\mongoDB\data\db" --logpath "d:\mongoDB\log\mongodb.log" --install --serviceName "MongoDB"
　　>
　　> ![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910145225863-277281762.png)

　　由于已经建立了一个mongodb.log文件，所以会出现上述情况，不过，这没有关系。这样已经成功将MongoDB安装为windows服务了。

6.启动MongoDB服务：同样是在bin目录下

> F:\monggoDB\bin\net start MongoDB

![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910145433551-1338821361.png)

打开任务管理器，就可以看到进程已经启动了

7.关闭服务和删除进程

　　> F:\monggoDB\bin>NET stop MongoDB (关闭服务)

　　> F:\monggoDB\bin>mongod --dbpath "d:\monggoDB\data\db" --logpath "d:\monggoDB\data\log\MongoDB.log" --remove --serviceName "MongoDB"

　　 (删除，注意不是--install了）

二.**MongoDB后台管理Shell**

1.如果你需要进入MongoDB后台管理，你需要先打开mongodb装目录的下的bin目录，然后执行mongo.exe文件，MongoDB Shell是MongoDB自带的交互式Javascript shell,用来对MongoDB进行操作和管理的交互式环境。

　　当你进入mongoDB后台后，它默认会链接到 test 文档（数据库）：

![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910150008879-1123917257.png)

由于它是一个JavaScript shell，您可以运行一些简单的算术运算:

![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910150056066-1224815401.png)

**db** 命令用于查看当前操作的文档（数据库）

![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910150128707-1190778833.png)

插入一些简单的记录并查找它：

![img](https://images2015.cnblogs.com/blog/883587/201609/883587-20160910150215535-1275068522.png)

第一个命令将数字 10 插入到 runoob 集合的 x 字段中


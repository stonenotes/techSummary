# Windows上搭建Flume运行环境

1.如果没有安装过Java环境，则需首先安装JDK。

可参考《[Windows上搭建Kafka运行环境](http://www.cnblogs.com/chevin/p/8427684.html)》中的搭建环境安装JDK部分

2.官方下载Flume（当前为apache-flume-1.8.0-bin.tar.gz）

[官方下载地址](http://flume.apache.org/download.html)

[官方用户手册](http://flume.apache.org/documentation.html)

3.根据官方用户手册，创建一个简单例子监听44444端口的输入并在console中输出。

①进入apache-flume-1.8.0-bin\conf文件夹中创建一个example.conf文件。

```
# example.conf: A single-node Flume configuration

# Name the components on this agent
a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source
a1.sources.r1.type = netcat
a1.sources.r1.bind = localhost
a1.sources.r1.port = 44444

# Describe the sink
a1.sinks.k1.type = logger

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1
```

②使用cmd，进入apache-flume-1.8.0-bin/bin，运行下面命令启动Flume。

```
flume-ng agent --conf ../conf --conf-file ../conf/example.conf --name a1 -property flume.root.logger=INFO,console
```

在console最后能看到下面这个端口监听提示表示Flume进程正常启动了。

![img](https://images2018.cnblogs.com/blog/55799/201803/55799-20180302110326415-44740780.png)

③启动另外一个cmd，使用telnet连接到44444端口并发送信息Hello World!

```
telnet localhost 44444
```

④在Flume的console中可以看到如下提示

![img](https://images2018.cnblogs.com/blog/55799/201803/55799-20180302110802896-1388836872.png)

 PS:当你发送的数据超过16字节时，在console的界面上也只能最多显示16字节，其实数据是能完全接收完全的！

如果发送数据真的超过最大长度，会出现Client sent event exceeding the maximum length错误。

可参考《[flume-ng 测试过程中event丢失部分body数据](http://rjhym.blog.163.com/blog/static/28130232201263042112497/)》


## 1 常用的命令操作

### 1.1 日常操作命令

```
**查看当前所在的工作目录
pwd
**查看当前系统的时间 
date
**查看有谁在线（哪些人登陆到了服务器）
who  查看当前在线
last 查看最近的登陆历史记录
```

### 1.2 文件系统操作

```
**
ls /    查看根目录下的子节点（文件夹和文件）信息
ls -al  -a是显示隐藏文件   -l是以更详细的列表形式显示

**切换目录
cd  /home

**创建文件夹
mkdir aaa     这是相对路径的写法 
mkdir -p aaa/bbb/ccc
mkdir  /data    这是绝对路径的写法 

**删除文件夹
rmdir   可以删除空目录
rm -r aaa   可以把aaa整个文件夹及其中的所有子节点全部删除
rm -rf aaa   强制删除aaa

**修改文件夹名称
mv aaa angelababy

**创建文件
touch  somefile.1   创建一个空文件
echo "i miss you,my baby" > somefile.2  利用重定向“>”的功能，将一条指令的输出结果写入到一个文件中，会覆盖原文件内容
echo "huangxiaoming ,gun dan" >> somefile.2     将一条指令的输出结果追加到一个文件中，不会覆盖原文件内容

用vi文本编辑器来编辑生成文件
******最基本用法
vi  somefile.4
1、首先会进入“一般模式”，此模式只接受各种快捷键，不能编辑文件内容
2、按i键，就会从一般模式进入编辑模式，此模式下，敲入的都是文件内容
3、编辑完成之后，按Esc键退出编辑模式，回到一般模式；
4、再按：，进入“底行命令模式”，输入wq命令，回车即可

******一些常用快捷键
一些有用的快捷键（在一般模式下使用）：
a  在光标后一位开始插入
A   在该行的最后插入
I   在该行的最前面插入
gg   直接跳到文件的首行
G    直接跳到文件的末行
dd   删除行，如果  5dd   ，则一次性删除光标后的5行
yy  复制当前行,  复制多行，则  3yy，则复制当前行附近的3行
p   粘贴
v  进入字符选择模式，选择完成后，按y复制，按p粘贴
ctrl+v  进入块选择模式，选择完成后，按y复制，按p粘贴
shift+v  进入行选择模式，选择完成后，按y复制，按p粘贴

查找并替换（在底行命令模式中输入）
%s/sad/88888888888888     效果：查找文件中所有sad，替换为88888888888888
/you       效果：查找文件中出现的you，并定位到第一个找到的地方，按n可以定位到下一个匹配位置（按N定位到上一个）
```

### 1.3 文件权限的操作

```
****linux文件权限的描述格式解读
drwxr-xr-x      （也可以用二进制表示  111 101 101  -->  755）

d：标识节点类型（d：文件夹   -：文件  l:链接）
r：可读   w：可写    x：可执行 
第一组rwx：  表示这个文件的拥有者对它的权限：可读可写可执行
第二组r-x：  表示这个文件的所属组对它的权限：可读，不可写，可执行
第三组r-x：  表示这个文件的其他用户（相对于上面两类用户）对它的权限：可读，不可写，可执行


****修改文件权限
chmod g-rw haha.dat    表示将haha.dat对所属组的rw权限取消
chmod o-rw haha.dat 	表示将haha.dat对其他人的rw权限取消
chmod u+x haha.dat      表示将haha.dat对所属用户的权限增加x

也可以用数字的方式来修改权限
chmod 664 haha.dat   
就会修改成   rw-rw-r--

如果要将一个文件夹的所有内容权限统一修改，则可以-R参数
chmod -R 770 aaa/
chown angela:angela aaa/    <只有root能执行>

目录没有执行权限的时候普通用户不能进入
文件只有读写权限的时候普通用户是可以删除的(删除文件不是修改它,是操作父及目录),只要父级目录有执行和修改的权限
```

### 1.4 基本的用户管理

```
*****添加用户
useradd  angela
要修改密码才能登陆 
passwd angela  按提示输入密码即可

**为用户配置sudo权限
用root编辑 vi /etc/sudoers
在文件的如下位置，为hadoop添加一行即可
root    ALL=(ALL)       ALL     
hadoop  ALL=(ALL)       ALL

然后，hadoop用户就可以用sudo来执行系统级别的指令
[hadoop@shizhan ~]$ sudo useradd huangxiaoming
```

### 1.5 系统管理操作

```
*****查看主机名
hostname
****修改主机名(重启后无效)
hostname hadoop

*****修改主机名(重启后永久生效)
vi /ect/sysconfig/network
****修改IP(重启后无效)
ifconfig eth0 192.168.12.22

****修改IP(重启后永久生效)
vi /etc/sysconfig/network-scripts/ifcfg-eth0

mount ****  挂载外部存储设备到文件系统中
mkdir   /mnt/cdrom      创建一个目录，用来挂载
mount -t iso9660 -o ro /dev/cdrom /mnt/cdrom/     将设备/dev/cdrom挂载到 挂载点 ：  /mnt/cdrom中

*****umount
umount /mnt/cdrom

*****统计文件或文件夹的大小
du -sh  /mnt/cdrom/Packages
df -h    查看磁盘的空间
****关机
halt
****重启
reboot

******配置主机之间的免密ssh登陆
假如 A  要登陆  B
在A上操作：
%%首先生成密钥对
ssh-keygen   (提示时，直接回车即可)
%%再将A自己的公钥拷贝并追加到B的授权列表文件authorized_keys中
ssh-copy-id   B
```

### 1.6 后台服务管理

```
service network status   查看指定服务的状态
service network stop     停止指定服务
service network start    启动指定服务
service network restart  重启指定服务
service --status-all  查看系统中所有的后台服务

设置后台服务的自启配置
chkconfig   查看所有服务器自启配置
chkconfig iptables off   关掉指定服务的自动启动
chkconfig iptables on   开启指定服务的自动启动
```



## 2 rz的使用

上传文件到linux上, 是上传到当前所在的目录下

```
yum list|grep lrzsz
sudo yum -y install lrzsz.x86_64
命令:(参数 -y 如果linux上有相同的文件, 会覆盖)
	rz
	rz -y
```

## 3 常用命令

```
说明：安装linux时，创建一个itcast用户，然后使用root用户登陆系统

1.进入到用户根目录
cd ~ 或 cd

2.查看当前所在目录
pwd

3.进入到itcast用户根目录
cd ~itcast

4.返回到原来目录
cd -

5.返回到上一级目录
cd ..

6.查看itcast用户根目录下的所有文件
ls -la

7.在根目录下创建一个itcast的文件夹
mkdir /itcast

8.在/itcast目录下创建src和WebRoot两个文件夹
分别创建：mkdir /itcast/src
		  mkdir /itcast/WebRoot
同时创建：mkdir /itcast/{src,WebRoot}

进入到/itcast目录，在该目录下创建.classpath和README文件
分别创建：touch .classpath
		  touch README
同时创建：touch {.classpath,README}

查看/itcast目录下面的所有文件
ls -la

在/itcast目录下面创建一个test.txt文件,同时写入内容"this is test"
echo "this is test" > test.txt

查看一下test.txt的内容
cat test.txt
more test.txt
less test.txt

向README文件追加写入"please read me first"
echo "please read me first" >> README

将test.txt的内容追加到README文件中
cat test.txt >> README

拷贝/itcast目录下的所有文件到/itcast-bak
cp -r /itcast /itcast-bak

进入到/itcast-bak目录，将test.txt移动到src目录下，并修改文件名为Student.java
mv test.txt src/Student.java

在src目录下创建一个struts.xml
> struts.xml

删除所有的xml类型的文件
rm -rf *.xml

删除/itcast-bak目录和下面的所有文件
rm -rf /itcast-bak

返回到/itcast目录，查看一下README文件有多单词，多少个少行
wc -w README
wc -l README

返回到根目录，将/itcast目录先打包，再用gzip压缩
分步完成：tar -cvf itcast.tar itcast
		  gzip itcast.tar
一步完成：tar -zcvf itcast.tar.gz itcast
		  
将其解压缩，再取消打包
分步完成：gzip -d itcast.tar.gz 或 gunzip itcast.tar.gz
一步完成：tar -zxvf itcast.tar.gz

将/itcast目录先打包，同时用bzip2压缩，并保存到/tmp目录下
tar -jcvf /tmp/itcast.tar.bz2 itcast

将/tmp/itcast.tar.bz2解压到/usr目录下面
tar -jxvf itcast.tar.bz2 -C /usr/
```

## 4 文件相关命令

```
1.进入到用户根目录
cd ~ 或者 cd
cd ~hadoop
回到原来路径
cd -

2.查看文件详情
stat a.txt

3.移动
mv a.txt /ect/
改名
mv b.txt a.txt
移动并改名
mv a.txt ../b.txt

4拷贝并改名
cp a.txt /etc/b.txt

5.vi撤销修改
ctrl + u (undo)
恢复
ctrl + r (redo)

6.名令设置别名(重启后无效)
alias ll="ls -l"
取消
unalias ll

7.如果想让别名重启后仍然有效需要修改
vi ~/.bashrc

8.添加用户
useradd hadoop
passwd hadoop

9创建多个文件
touch a.txt b.txt
touch /home/{a.txt,b.txt}

10.将一个文件的内容复制到里另一个文件中
cat a.txt > b.txt
追加内容
cat a.txt >> b.txt 

11.将a.txt 与b.txt设为其拥有者和其所属同一个组者可写入，但其他以外的人则不可写入:
chmod ug+w,o-w a.txt b.txt

chmod a=wx c.txt

12.将当前目录下的所有文件与子目录皆设为任何人可读取:
chmod -R a+r *

13.将a.txt的用户拥有者设为users,组的拥有者设为jessie:
chown users:jessie a.txt

14.将当前目录下的所有文件与子目录的用户的使用者为lamport,组拥有者皆设为users，
chown -R lamport:users *

15.将所有的java语言程式拷贝至finished子目录中:
cp *.java finished

16.将目前目录及其子目录下所有扩展名是java的文件列出来。
find -name "*.java"
查找当前目录下扩展名是java 的文件
find -name *.java

17.删除当前目录下扩展名是java的文件
rm -f *.java
```

## 5 系统命令

```
1.查看主机名
hostname

2.修改主机名(重启后无效)
hostname hadoop

3.修改主机名(重启后永久生效)
vi /ect/sysconfig/network

4.修改IP(重启后无效)
ifconfig eth0 192.168.12.22

5.修改IP(重启后永久生效)
vi /etc/sysconfig/network-scripts/ifcfg-eth0

6.查看系统信息
uname -a
uname -r

7.查看ID命令
id -u
id -g

8.日期
date
date +%Y-%m-%d
date +%T
date +%Y-%m-%d" "%T

9.日历
cal 2012

10.查看文件信息
file filename

11.挂载硬盘
mount
umount
加载windows共享
mount -t cifs //192.168.1.100/tools /mnt

12.查看文件大小
du -h
du -ah

13.查看分区
df -h

14.ssh
ssh hadoop@192.168.1.1

15.关机
shutdown -h now /init 0
shutdown -r now /reboot
```

## 6 用户和组

```
添加一个tom用户，设置它属于users组，并添加注释信息
分步完成：useradd tom
          usermod -g users tom
	      usermod -c "hr tom" tom
一步完成：useradd -g users -c "hr tom" tom

设置tom用户的密码
passwd tom

修改tom用户的登陆名为tomcat
usermod -l tomcat tom

将tomcat添加到sys和root组中
usermod -G sys,root tomcat

查看tomcat的组信息
groups tomcat

添加一个jerry用户并设置密码
useradd jerry
passwd jerry

添加一个交america的组
groupadd america

将jerry添加到america组中
usermod -g america jerry

将tomcat用户从root组和sys组删除
gpasswd -d tomcat root
gpasswd -d tomcat sys

将america组名修改为am
groupmod -n am america
```

## 7 权限

```
创建a.txt和b.txt文件，将他们设为其拥有者和所在组可写入，但其他以外的人则不可写入:
chmod ug+w,o-w a.txt b.txt

创建c.txt文件所有人都可以写和执行
chmod a=wx c.txt 或chmod 666 c.txt

将/itcast目录下的所有文件与子目录皆设为任何人可读取
chmod -R a+r /itcast

将/itcast目录下的所有文件与子目录的拥有者设为root，用户拥有组为users
chown -R root:users /itcast

将当前目录下的所有文件与子目录的用户皆设为itcast，组设为users
chown -R itcast:users *
```

## 8 文件夹属性

```
1.查看文件夹属性
ls -ld test

2.文件夹的rwx
--x:可以cd进去
r-x:可以cd进去并ls
-wx:可以cd进去并touch，rm自己的文件，并且可以vi其他用户的文件
-wt:可以cd进去并touch，rm自己的文件

ls -ld /tmp
drwxrwxrwt的权限值是1777(sticky)
```

## 9 安装软件

```
1.安装JDK
	*添加执行权限 
		chmod u+x jdk-6u45-linux-i586.bin
	*解压
		./jdk-6u45-linux-i586.bin
	*在/usr目录下创建java目录
		mkdir /usr/java
	*将/soft目录下的解压的jdk1.6.0_45剪切到/usr/java目录下
		mv jdk1.6.0_45/ /usr/java/
	*添加环境变量
		vim /etc/profile
		*在/etc/profile文件最后添加
			export JAVA_HOME=/usr/java/jdk1.6.0_45
			export CLASSPATH=$JAVA_HOME/lib
			export PATH=$PATH:$JAVA_HOME/bin
	*更新配置
		source /etc/profile
		
2.安装tomcat
	tar -zxvf /soft/apache-tomcat-7.0.47.tar.gz -C /programs/
	cd /programs/apache-tomcat-7.0.47/bin/
	./startup.sh
	
3.安装eclipse
```

## 10 查找

```
1.查找可执行的命令：
which ls

2.查找可执行的命令和帮助的位置：
whereis ls

3.查找文件(需要更新库:updatedb)
locate hadoop.txt

4.从某个文件夹开始查找
find / -name "hadooop*"
find / -name "hadooop*" -ls

5.查找并删除
find / -name "hadooop*" -ok rm {} \;
find / -name "hadooop*" -exec rm {} \;

6.查找用户为hadoop的文件
find /usr -user hadoop -ls

7.查找用户为hadoop并且(-a)拥有组为root的文件
find /usr -user hadoop -a -group root -ls

8.查找用户为hadoop或者(-o)拥有组为root并且是文件夹类型的文件
find /usr -user hadoop -o -group root -a -type d

9.查找权限为777的文件
find / -perm -777 -type d -ls

10.显示命令历史
history

11.grep
grep hadoop /etc/password
```

## 11 打包与压缩

```
1.gzip压缩
gzip a.txt

2.解压
gunzip a.txt.gz
gzip -d a.txt.gz

3.bzip2压缩
bzip2 a

4.解压
bunzip2 a.bz2
bzip2 -d a.bz2

5.将当前目录的文件打包
tar -cvf bak.tar .
将/etc/password追加文件到bak.tar中(r)
tar -rvf bak.tar /etc/password

6.解压
tar -xvf bak.tar

7.打包并压缩gzip
tar -zcvf a.tar.gz

8.解压缩
tar -zxvf a.tar.gz
解压到/usr/下
tar -zxvf a.tar.gz -C /usr

9.查看压缩包内容
tar -ztvf a.tar.gz

zip/unzip

10.打包并压缩成bz2
tar -jcvf a.tar.bz2

11.解压bz2
tar -jxvf a.tar.bz2
```

## 12 正则表达式

```
1.cut截取以:分割保留第七段
grep hadoop /etc/passwd | cut -d: -f7

2.排序
du | sort -n 

3.查询不包含hadoop的
grep -v hadoop /etc/passwd

4.正则表达包含hadoop
grep 'hadoop' /etc/passwd

5.正则表达(点代表任意一个字符)
grep 'h.*p' /etc/passwd

6.正则表达以hadoop开头
grep '^hadoop' /etc/passwd

7.正则表达以hadoop结尾
grep 'hadoop$' /etc/passwd

规则：
.  : 任意一个字符
a* : 任意多个a(零个或多个a)
a? : 零个或一个a
a+ : 一个或多个a
.* : 任意多个任意字符
\. : 转义.
\<h.*p\> ：以h开头，p结尾的一个单词
o\{2\} : o重复两次

grep '^i.\{18\}n$' /usr/share/dict/words

查找不是以#开头的行
grep -v '^#' a.txt | grep -v '^$' 

以h或r开头的
grep '^[hr]' /etc/passwd

不是以h和r开头的
grep '^[^hr]' /etc/passwd

不是以h到r开头的
grep '^[^h-r]' /etc/passwd
```

## 13 输入输出重定向及管道

```
1.新建一个文件
touch a.txt
> b.txt

2.错误重定向:2>
find /etc -name zhaoxing.txt 2> error.txt

3.将正确或错误的信息都输入到log.txt中
find /etc -name passwd > /tmp/log.txt 2>&1 
find /etc -name passwd &> /tmp/log.txt

4.追加>>

5.将小写转为大写（输入重定向）
tr "a-z" "A-Z" < /etc/passwd

6.自动创建文件
cat > log.txt << EXIT
> ccc
> ddd
> EXI

7.查看/etc下的文件有多少个？
ls -l /etc/ | grep '^d' | wc -l

8.查看/etc下的文件有多少个，并将文件详情输入到result.txt中
ls -l /etc/ | grep '^d' | tee result.txt | wc -l
```

## 14 进程控制

```
1.查看用户最近登录情况
last
lastlog

2.查看硬盘使用情况
df

3.查看文件大小
du

4.查看内存使用情况
free

5.查看文件系统
/proc

6.查看日志
ls /var/log/

7.查看系统报错日志
tail /var/log/messages

8.查看进程
top

9.结束进程
kill 1234
kill -9 4333
```

## 15 iptables

```
#查看帮助
iptables -h
man iptables

列出iptables规则
iptables -L -n
列出iptables规则并显示规则编号
iptables -L -n --line-numbers

列出iptables nat表规则（默认是filter表）
iptables -L -n -t nat

清除默认规则（注意默认是filter表，如果对nat表操作要加-t nat）
#清楚所有规则
iptables -F 

#重启iptables发现规则依然存在，因为没有保存
service iptables restart

#保存配置
service iptables save

#禁止ssh登陆（若果服务器在机房，一定要小心）
iptables -A INPUT -p tcp --dport 22 -j DROP
#删除规则
iptables -D INPUT -p tcp --dport 22 -j DROP

-A, --append chain	追加到规则的最后一条
-D, --delete chain [rulenum]	Delete rule rulenum (1 = first) from chain
-I, --insert chain [rulenum]	Insert in chain as rulenum (default 1=first) 添加到规则的第一条
-p, --proto  proto	protocol: by number or name, eg. 'tcp',常用协议有tcp、udp、icmp、all
-j, --jump target 常见的行为有ACCEPT、DROP和REJECT三种，但一般不用REJECT，会带来安全隐患

注意：INPUT和DROP这样的关键字需要大写

#禁止192.168.33.0网段从eth0网卡接入
iptables -A INPUT -p tcp -i eth0 -s 192.168.33.0 -j DROP
iptables -A INPUT -p tcp --dport 22 -i eth0 -s 192.168.33.61  -j ACCEPT

#禁止ip地址非192.168.10.10的所有类型数据接入
iptables -A INPUT ! -s 192.168.10.10 -j DROP

#禁止ip地址非192.168.10.10的ping请求
iptables -I INPUT -p icmp --icmp-type 8 -s 192.168.50.100 -j DROP

#扩展匹配：1.隐式扩展 2.显示扩展
	#隐式扩展
	-p tcp
		--sport PORT 源端口
		--dport PORT 目标端口

	#显示扩展：使用额外的匹配规则
	-m EXTENSTION --SUB-OPT
	-p tcp --dport 22 与 -p tcp -m tcp --dport 22功能相同

	state：状态扩展，接口ip_contrack追踪会话状态
		NEW：新的连接请求
		ESTABLISHED：已建立的连接请求
		INVALID：非法连接
		RELATED：相关联的连接
	
#匹配端口范围
iptables -I INPUT -p tcp --dport 22:80 -j DROP

#匹配多个端口
iptables -I INPUT -p tcp -m multiport --dport 22,80,3306 -j ACCEPT

#不允许源端口为80的数据流出
iptables -I OUTPUT -p tcp --sport 80 -j DROP

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
```

## 16 自动化部署脚本

**install_everyone.sh**

```
#!/bin/bash
BASE_SERVER=172.16.203.100
yum install -y wget
wget $BASE_SERVER/soft/jdk-7u45-linux-x64.tar.gz
tar -zxvf jdk-7u45-linux-x64.tar.gz -C /usr/local
cat >> /etc/profile << EOF
export JAVA_HOME=/usr/local/jdk1.7.0_45
export PATH=\$PATH:\$JAVA_HOME/bin
EOF
```

**boot.sh**

```
#!/bin/bash

SERVERS="node-3.itcast.cn node-4.itcast.cn"
PASSWORD=123456
BASE_SERVER=172.16.203.100

auto_ssh_copy_id() {
    expect -c "set timeout -1;
        spawn ssh-copy-id $1;
        expect {
            *(yes/no)* {send -- yes\r;exp_continue;}
            *assword:* {send -- $2\r;exp_continue;}
            eof        {exit 0;}
        }";
}

ssh_copy_id_to_all() {
    for SERVER in $SERVERS
    do
        auto_ssh_copy_id $SERVER $PASSWORD
    done
}

ssh_copy_id_to_all

for SERVER in $SERVERS
do
    scp install.sh root@$SERVER:/root
    ssh root@$SERVER /root/install.sh
done
```


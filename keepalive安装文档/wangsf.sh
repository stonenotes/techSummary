#!/bin/bash
while true;
do
    A=`ps -ef|grep tomcat |wc -l`
	B=`ps -ef|grep keepalived |wc -l`
echo $A
if [ $A -eq 1 ];then
                echo 'restart tomcat!!!!'
                /usr/local/server/apache-tomcat-6.0.37/bin/startupsss.sh
				 if [ $A -eq 1 ];then
					if [ $B -gt 1 ];then
						   echo 'tomcat dead  !!!! kill keepalived'
						   killall keepalived
					fi
				fi				
fi
if [ $A -eq 2 ];then
					if [ $B -eq 1 ];then
						   echo 'tomcat live  !!!! start keepalived'
						   service keepalived start
					fi
				fi
sleep 3
done

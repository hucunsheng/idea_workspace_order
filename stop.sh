#!将应用停止
#!stop.sh
#!/bin/bash
cd /root/.jenkins/workspace/idea_workspace_order
echo "Stopping SpringBoot Application for ORDER"
ls
pid=`ps -ef | grep order-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}'`
if [ -n "$pid" ]
then
#!kill -9 强制终止
   echo "kill -9 的pid:" $pid
   kill -9 $pid
fi
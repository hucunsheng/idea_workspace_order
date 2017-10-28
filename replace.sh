jar_name=order-0.0.1-SNAPSHOT.jar
#!编译好的jar包存放地址
file_path=/root/.jenkins/workspace/idea_workspace_order/target
#!将现有的jar备份后，将新的jar包替换
file="/root/deploy/order-0.0.1-SNAPSHOT.jar"
if [ -f "$file" ]
then
mv /root/deploy/order-0.0.1-SNAPSHOT.jar /root/deploy_backup/order-0.0.1-SNAPSHOT.jar.`date +%Y%m%d%H%M%S`
fi
cp /root/.jenkins/workspace/idea_workspace_order/target/order-0.0.1-SNAPSHOT.jar /root/deploy
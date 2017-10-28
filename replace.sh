jar_name=order-0.0.1-SNAPSHOT.jar
#!编译好的jar包存放地址
file_path=~/.jenkins/workspace/idea_workspace_order/target
#!将现有的jar备份后，将新的jar包替换
file="/deploy/order-0.0.1-SNAPSHOT.jar"
if [ -f "$file" ]
then
mv /deploy/order-0.0.1-SNAPSHOT.jar /deploy_backup/order-0.0.1-SNAPSHOT.jar.`date +%Y%m%d%H%M%S`
fi
cp ~/.jenkins/workspace/idea_workspace_order/target/order-0.0.1-SNAPSHOT.jar /deploy
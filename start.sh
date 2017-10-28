echo "授予当前用户权限"
chmod 777 /root/deploy/order-0.0.1-SNAPSHOT.jar
echo "执行....."
cd /root/deploy
java -jar order-0.0.1-SNAPSHOT.jar
echo "**********************cmp on  jenkins started*************************"
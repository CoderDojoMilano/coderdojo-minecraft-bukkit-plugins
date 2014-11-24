#!/bin/sh

set -e

export JAVA_HOME=/home/ubuntu/jdk1.8.0_25
cd /home/ubuntu/coderdojo-minecraft-bukkit-plugins
git pull
/home/ubuntu/crafbukkit/server.sh stop
./build-and-deploy-plugins.sh /home/ubuntu/crafbukkit
/home/ubuntu/crafbukkit/server.sh start


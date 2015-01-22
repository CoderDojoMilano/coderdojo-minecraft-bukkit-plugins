#!/bin/sh

set -e

export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
cd /root/coderdojo-minecraft-bukkit-plugins
git pull
/etc/init.d/craftbukkit stop
./build-and-deploy-plugins.sh /etc/craftbukkit
/etc/init.d/craftbukkit start

#!/bin/bash
# setup-java21.sh
# This script installs and configures Java 21 using APT (Option 2)
# It also ensures Yarn's public key is present to avoid apt errors

set -e

echo "=== Fixing Yarn GPG key if missing ==="
YARN_KEYRING="/usr/share/keyrings/yarn-archive-keyring.gpg"
if [ ! -f "$YARN_KEYRING" ]; then
    echo "Adding Yarn GPG key..."
    curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo gpg --dearmor -o "$YARN_KEYRING"
    echo "deb [signed-by=$YARN_KEYRING] https://dl.yarnpkg.com/debian stable main" | sudo tee /etc/apt/sources.list.d/yarn.list
else
    echo "Yarn key already exists."
fi

echo "=== Updating package lists ==="
sudo apt update

echo "=== Installing Java 21 ==="
sudo apt install -y openjdk-21-jdk

JAVA_PATH="/usr/lib/jvm/java-21-openjdk-amd64"

echo "=== Registering Java 21 with update-alternatives ==="
sudo update-alternatives --install /usr/bin/java java $JAVA_PATH/bin/java 2
sudo update-alternatives --install /usr/bin/javac javac $JAVA_PATH/bin/javac 2

echo "=== Selecting Java 21 as default ==="
sudo update-alternatives --set java $JAVA_PATH/bin/java
sudo update-alternatives --set javac $JAVA_PATH/bin/javac

echo "=== Configuring JAVA_HOME in ~/.bashrc ==="
if ! grep -q "JAVA_HOME=$JAVA_PATH" ~/.bashrc; then
    echo "export JAVA_HOME=$JAVA_PATH" >> ~/.bashrc
    echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.bashrc
fi

# Apply changes to current shell
export JAVA_HOME=$JAVA_PATH
export PATH=$JAVA_HOME/bin:$PATH

echo "=== Verification ==="
java -version
javac -version
echo "JAVA_HOME=$JAVA_HOME"

echo "=== Setup complete! ==="



# Use Ubuntu 24.04 as base
FROM ubuntu:24.04

# Avoid prompts
ENV DEBIAN_FRONTEND=noninteractive

# Install Java 21, Maven, Git, curl
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    git \
    curl \
    unzip \
    && apt-get clean

# Set JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Set workspace
WORKDIR /workspace
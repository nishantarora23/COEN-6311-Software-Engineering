#!/bin/bash

# Install Git
sudo yum install git -y
git clone https://github.com/nishantarora23/ICDE-JobHive.git

# Install Docker
sudo yum update -y
sudo yum install -y docker

# Start Docker
sudo service docker start
sudo systemctl enable docker

# Wait for Docker to start
sleep 10

# Add ec2-user to docker group
sudo usermod -aG docker ec2-user

# Configure and Install Docker-Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Call replace.sh script
sudo chmod +x ICDE-JobHive/config/aws/replace.sh
sudo ICDE-JobHive/config/aws/replace.sh
FROM jenkins/jenkins
ENV JENKINS_OPTS --httpPort=8090
EXPOSE 8090

USER root

# Root image based on Debian 9, so we need to install fresher version of Ansible
RUN echo "deb http://ppa.launchpad.net/ansible/ansible/ubuntu trusty main" >> /etc/apt/sources.list
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 93C4A3FD7BB9C367

# Update and Upgrade system
RUN apt update && apt upgrade -y

# Install Docker
RUN apt install -y apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable"
RUN apt update && apt install -y docker-ce

# Install additional libs
RUN apt install -y ansible vim maven
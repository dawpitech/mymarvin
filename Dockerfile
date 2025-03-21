FROM jenkins/jenkins:lts-jdk17

USER root

RUN apt update && apt install -y build-essential libcriterion-dev

USER jenkins

COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt

RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

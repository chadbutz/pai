FROM ubuntu

VOLUME /tmp

# Update Ubuntu
RUN \
  bash -c 'apt-get -qq update && apt-get -y upgrade && apt-get -y autoclean && apt-get -y autoremove' && \
  bash -c 'DEBIAN_FRONTEND=noninteractive apt-get install -y curl wget tar default-jre mysql-server'

ENV USER_NAME pai
ENV APP_HOME /opt/poc-api/$USER_NAME
ENV JAVA_HOME /usr/lib/jvm/java-7-openjdk-amd64

RUN \
  useradd -ms /bin/bash $USER_NAME && \
  mkdir -p $APP_HOME
 
ADD pai.jar ${APP_HOME}/pai.jar

RUN \
  chown $USER_NAME $APP_HOME/pai.jar && \
  bash -c 'touch ${APP_HOME}/pai.jar'
      
CMD ["/usr/bin/mysqld"]
EXPOSE 3306
#RUN /usr/sbin/mysqld & \
#    sleep 10s && \
#    echo "GRANT ALL ON *.* TO admin@'%' IDENTIFIED BY 'changeme' WITH GRANT OPTION; FLUSH PRIVILEGES" | mysql
    

ENV JAVA_TOOL_OPTIONS "-Xms128M -Xmx128M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"


USER $USER_NAME
WORKDIR $APP_HOME
ENTRYPOINT ["java", "-jar", "pai.jar"]
# Run as:
# mvn -U -X package docker:build -Dpush.image=true
# docker run -idt -p 8701:8701 -e appPort=8701 chadbutz/pai:latest

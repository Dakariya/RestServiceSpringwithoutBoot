FROM tomcat:9.0.91
WORKDIR /usr/local/tomcat
COPY /target/RestServiceSpringwithoutBoot.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
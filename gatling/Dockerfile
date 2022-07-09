FROM eclipse-temurin:17

WORKDIR /opt/

ENV GATLING_VERSION 3.7.6
ENV GATLING_HOME /opt/gatling

RUN apt update && apt install unzip -y \
      && curl -LsS https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/$GATLING_VERSION/gatling-charts-highcharts-bundle-$GATLING_VERSION-bundle.zip > gatling.zip \
      && unzip gatling.zip && mv gatling-charts-highcharts-bundle-$GATLING_VERSION gatling \
      && rm -rf gatling/user-files/* gatling.zip

COPY simulations/ /opt/gatling/user-files/simulations/
COPY run.sh /opt/

CMD ["sh", "-c", "/opt/run.sh ${HOSTS} ${ENDPOINTS}"]
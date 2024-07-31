FROM curlimages/curl:8.2.1 AS download
ARG OTEL_AGENT_VERSION="2.5.0"
RUN curl --silent --fail -L "https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v${OTEL_AGENT_VERSION}/opentelemetry-javaagent.jar" \
    -o "$HOME/opentelemetry-javaagent.jar"

FROM amazoncorretto:21.0.3-alpine
COPY --from=download /home/curl_user/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
COPY target/questions-0.0.1.jar questions-0.0.1.jar

ENTRYPOINT ["java", \
  "-javaagent:/opentelemetry-javaagent.jar", \
  "-Dotel.service.name=questions", \
  "-jar", "/questions-0.0.1.jar" \
  ]

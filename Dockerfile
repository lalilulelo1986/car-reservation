FROM gradle:7.4.2-jdk17 as builder
USER root
RUN addgroup builder && adduser --ingroup builder builder
COPY --chown=builder:builder . /home/builder
USER builder
WORKDIR /home/builder
RUN gradle build -info
FROM openjdk:16-alpine
USER root
RUN addgroup appuser && adduser --disabled-password --ingroup appuser appuser
RUN mkdir -p /app /app/logs
RUN chown appuser /app/logs
USER appuser
WORKDIR /app
COPY --from=builder --chown=appuser:appuser /home/builder/build/libs/car-reservation-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT exec java -jar /app/app.jar

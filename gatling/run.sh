#!/usr/bin/env bash

SERVER_HOST=$1
ENDPOINTS=$2

echo "Starting the stress test on ${SERVER_HOST}"

for endpoint in $(echo ${ENDPOINTS} | tr "," "\n"); do
  echo "Testing endpoint ${endpoint}"
  JAVA_OPTS="-DbaseUrl=${SERVER_HOST} -Dendpoint=${endpoint}" \
    "$GATLING_HOME/bin/gatling.sh" --run-description "Benchmark for endpoint ${endpoint}" -s LoadSimulation
done

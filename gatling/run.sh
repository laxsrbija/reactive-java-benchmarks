#!/usr/bin/env bash

HOSTS=$1
ENDPOINTS=$2

for host in $(echo "${HOSTS}" | tr "," "\n"); do
  for endpoint in $(echo "${ENDPOINTS}" | tr "," "\n"); do
    DESCRIPTION="Benchmark for endpoint '${endpoint}' on ${host}"
    echo "$DESCRIPTION"

    JAVA_OPTS="-DbaseUrl=${host} -Dendpoint=${endpoint}" \
      "$GATLING_HOME/bin/gatling.sh" --run-description "${DESCRIPTION}" -s LoadSimulation

    echo "Round finished, sleeping for 15 sec"
    sleep 15s
  done
done

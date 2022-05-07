#!/bin/bash
export CLASSPATH=target/sqs_consomer-1.0-SNAPSHOT.jar
export className=App
export KAFKA_HOST="localhost:9092"
export KAFKA_TOPIC="TOPICO_1"
export KAFKA_GROUP_ID_READER="consome_1"
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.sqs_consomer.$className"
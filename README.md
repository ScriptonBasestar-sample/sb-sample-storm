Sample Storm
============


## Storm topology

### deploy
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test2-topic -from-beginning

storm jar topology/build/libs/topology-0.0.1-SNAPSHOT-all.jar sample.111
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test1-topic -from-beginning

storm jar topology/build/libs/topology-0.0.1-SNAPSHOT-all.jar sample.222
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test2-topic -from-beginning

storm jar topology/build/libs/topology-0.0.1-SNAPSHOT-all.jar sample.333
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test3-topic -from-beginning

## Reference

* https://github.com/apache/storm


## Trivia

~
# Topic 생성
docker exec -it local-kafka /opt/kafka/bin/kafka-topics.sh --create --replication-factor 1 --partitions 1 --zookeeper zookeeper:2181 --topic test-message-stream
docker exec -it kafka_kafka_1 /opt/kafka/bin/kafka-topics.sh --zookeeper zookeeper:2181 --alter --topic test-message-stream --config retention.ms=3600000

# Topic List 보기
docker exec -it local-kafka /opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181

# Topic 삭제
docker exec -it local-kafka /opt/kafka/bin/kafka-topics.sh --delete --zookeeper zookeeper:2181 --topic test-message-stream

# Message 보기
docker exec -it local-kafka /opt/kafka/bin/kafka-console-consumer.sh -bootstrap-server localhost:9092 -topic test-message-stream -from-beginning
# Kafka Concepts Demo

## Descrição
Este projeto é uma demonstração de conceitos do Apache Kafka utilizando Spring Boot. A aplicação implementa um produtor e um consumidor Kafka, permitindo o envio e recebimento de mensagens através de uma API REST.
É um projeto de estudo que está e estará em constante desenvolvimento.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.5.3
- Apache Kafka
- Docker e Docker Compose
- Maven

## Pré-requisitos
Para executar esta aplicação localmente, você precisará ter instalado:
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Docker Compose](https://docs.docker.com/compose/install/) (geralmente já vem com o Docker Desktop)

## Como Executar Localmente

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/kafka-concepts-demo.git
cd kafka-concepts-demo
```

### 2. Inicie o Kafka com Docker Compose
O projeto inclui um arquivo `docker-compose.yml` que configura o Zookeeper e o Kafka:

```bash
docker-compose up -d
```

Este comando iniciará:
- Zookeeper na porta 2181
- Kafka na porta 9092

Você pode verificar se os containers estão rodando com:
```bash
docker-compose ps
```

### 3. Compile e execute a aplicação
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Como Usar a Aplicação

### Enviar uma mensagem para o Kafka
Para enviar uma mensagem, faça uma requisição POST para o endpoint `/messages`:

```bash
curl -X POST http://localhost:8080/messages \
  -H "Content-Type: application/json" \
  -d '{"key": "chave-exemplo", "message": "Mensagem de teste"}'
```

A mensagem será enviada para o tópico "my-topic" no Kafka.

### Consumir mensagens
O consumidor Kafka está configurado para escutar automaticamente o tópico "my-topic". Quando uma mensagem é recebida, ela é processada e o resultado é exibido no console da aplicação.

## Estrutura do Projeto

```
src/main/java/com/lubeck/kafka_concepts_demo/
├── KafkaConceptsDemoApplication.java        # Classe principal da aplicação
├── adapters
│   ├── messaging
│   │   ├── config
│   │   │   ├── consumer
│   │   │   │   └── KafkaConsumerConfig.java # Configuração do consumidor Kafka
│   │   │   └── producer
│   │   │       └── KafkaProducerConfig.java # Configuração do produtor Kafka
│   │   ├── consumer
│   │   │   └── KafkaConsumer.java           # Implementação do consumidor Kafka
│   │   └── producer
│   │       └── MessageProducer.java         # Implementação do produtor Kafka
│   └── web
│       ├── controller
│       │   └── MessageController.java       # Controller REST
│       └── dto
│           └── MessageRequestDTO.java       # DTO para requisições
```

## Configuração do Kafka
A aplicação está configurada para se conectar ao Kafka em `localhost:9092`. As principais configurações podem ser encontradas em:
- `src/main/resources/application.yaml`
- `src/main/java/com/lubeck/kafka_concepts_demo/adapters/messaging/config/consumer/KafkaConsumerConfig.java`
- `src/main/java/com/lubeck/kafka_concepts_demo/adapters/messaging/config/producer/KafkaProducerConfig.java`

## Parando a Aplicação

### 1. Pare a aplicação Spring Boot
Pressione `Ctrl+C` no terminal onde a aplicação está rodando.

### 2. Pare os containers Docker
```bash
docker-compose down
```

## Informações Adicionais
- O consumidor Kafka está configurado com confirmação manual de mensagens (manual acknowledgment)
- O produtor Kafka utiliza CompletableFuture para processamento assíncrono
- O tópico "my-topic" é criado automaticamente se não existir

---

Desenvolvido como demonstração de conceitos do Apache Kafka com Spring Boot.
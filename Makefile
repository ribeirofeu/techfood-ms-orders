build:
	docker-compose up -d --build

up:
	docker-compose up -d

down:
	docker-compose down

status:
	docker ps

start:
	mvn spring-boot:run

test:
	mvn test

integration-test:
	mvn test -P integration-tests
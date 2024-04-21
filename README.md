# Запуск
1) Перейти в директорию Docker
2) Собрать образ приложения "docker build -t card-service:latest ."
3) Запустить docker compose "docker-compose up -d"
4) Сервис доступен на localhost:8080
5) БД postgres поднимается отдельным контейнером в докере. Доступн по адресу jdbc:postgresql://localhost:5433/fitcha postgres/password
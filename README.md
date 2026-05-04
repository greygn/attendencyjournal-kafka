# Attendance Journal

REST API для управления посещаемостью студентов.

## Описание

Приложение предоставляет HTTP API для работы с учебными группами, студентами, занятиями и регистрацией посещаемости. База данных построена на PostgreSQL.

## Запуск через Docker Compose

```bash
docker-compose up
```

API будет доступен по адресу `http://localhost:8080`.

## Локальная разработка

### Предварительные требования

- Maven 3.9+
- Java 21+
- PostgreSQL 16+ (для локального развертывания)

### Запуск тестов

```bash
mvn clean test
```

Выполняются unit тесты с проверкой coverage (минимум 50%).

### Сборка

```bash
mvn clean package
```

### Проверка качества кода

```bash
mvn checkstyle:check
```

### Запуск приложения локально

Требуется работающий PostgreSQL. Установите переменные окружения:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/attendancy_db
export SPRING_DATASOURCE_USERNAME=journaluser
export SPRING_DATASOURCE_PASSWORD=password
```

Затем запустите:

```bash
mvn spring-boot:run
```

## Docker

### Сборка образа

```bash
docker build -t attendencyjournal:latest .
```

### Запуск контейнера

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/appdb \
  -e SPRING_DATASOURCE_USERNAME=journaluser \
  -e SPRING_DATASOURCE_PASSWORD=password \
  attendencyjournal:latest
```

## Структура проекта

Multi-module Spring Boot приложение:

- **api-service** – REST gateway, прокси запросы к data-service, Kafka producer для checkins
- **data-service** – основной бизнес-сервис с логикой, БД (PostgreSQL), отчёты
- **common** – общие классы (DTO, Entity, утилиты)

Каждый модуль имеет стандартную структуру:

```
├── controller/       REST endpoints
├── service/          Бизнес-логика
├── entity/           Модели БД (только data-service)
├── repository/       Data access (только data-service)
└── dto/              Transfer objects
```

## Endpoints

Все ниже адреса дергают **API на порту 8080**. Создание посещений уходит в **Kafka**, поэтому `POST /checkins` возвращает **202 Accepted**, а строка может появиться в БД с небольшой задержкой.

### Study Groups

**POST /groups** – Создать учебную группу (поле номера курса: **`course`** или **`courseNumber`**)

```bash
curl -X POST http://localhost:8080/groups \
  -H "Content-Type: application/json" \
  -d '{"name": "b1-IFST-32", "courseNumber": 3}'
```

**GET /groups** – Получить все группы

```bash
curl http://localhost:8080/groups
```

### Students

**POST /students** – Создать студента

```bash
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{"name": "Ivan Petrov", "groupId": 1}'
```

**GET /students** – Получить всех студентов

```bash
curl http://localhost:8080/students
```

### Lessons

**POST /lessons** – Создать занятие (`datetime` должен быть **в будущем** относительно текущего времени сервера)

```bash
curl -X POST http://localhost:8080/lessons \
  -H "Content-Type: application/json" \
  -d '{"name": "Lecture", "datetime": "2027-04-09T09:45:00", "groupIds": [1]}'
```

**GET /lessons** – Получить все занятия

```bash
curl http://localhost:8080/lessons
```

### Checkins

Эквивалентно `POST http://localhost:8080/api/data/batches` (то же сообщение в Kafka).

**POST /checkins** – Отметить посещение (ответ **202**)

```bash
curl -X POST http://localhost:8080/checkins \
  -H "Content-Type: application/json" \
  -d '{"lessonId": 1, "studentId": 1}'
```

**GET /checkins/student/{id}/count** – Получить количество посещений студента

```bash
curl http://localhost:8080/checkins/student/1/count
```

### Отчёты

**GET /api/reports/attendance-by-group** – Посещаемость по группам

```bash
curl http://localhost:8080/api/reports/attendance-by-group
```

**GET /api/reports/attendance-by-lesson** – Посещаемость по занятиям

```bash
curl http://localhost:8080/api/reports/attendance-by-lesson
```

**GET /api/reports/summary** – Сводный отчёт по посещаемости

```bash
curl http://localhost:8080/api/reports/summary
```

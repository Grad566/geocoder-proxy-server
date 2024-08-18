## Тестовое задание от Effective Mobile

[![My test](https://github.com/Grad566/geodocer-proxy-server/actions/workflows/myTest.yml/badge.svg)](https://github.com/Grad566/geodocer-proxy-server/actions/workflows/myTest.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/f218e7cd00bb377b8821/maintainability)](https://codeclimate.com/github/Grad566/geodocer-proxy-server/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f218e7cd00bb377b8821/test_coverage)](https://codeclimate.com/github/Grad566/geodocer-proxy-server/test_coverage)

Требования - [здесь](https://github.com/waliot/test-tasks/blob/master/tasks/backend-1.md)

Приложение представляет собой API прокси-сервис геокодирования.
Описание задания в самом низу.

## Локальный запуск
1) Если есть Docker и docker-compose.

```
docker-compose up
```

После чего приложение будет доступно по http://localhost:8080/

2) Запуск без докера.

Требования:
- jdk 21
- gradle 8.7

```
make dev
```

После чего приложение будет доступно по http://localhost:8080/

В качестве бд будет H2. (в докере PostgreSQL)


## Документация
Документация swagger по url:
1) http://localhost:8080/swagger-ui/index.html
2) http://localhost:8080/v3/api-docs

Два эндпоинта
```
/api/geocoding/get_coordinates?address=...

/api/geocoding/get_address?coordinates=...
```


Дополнительные команды:
```
// запуск checkStyle
make lint 

// запуск тестов
make test 

// запуск приложения без docker-compose
make dev 
```


## Дополнительно.
Была выбрана связь manyToMany т.к. одому адрессу/координатам  может соотвествовать несколько значений.

## Описание задания
Тестовое задание на позицию backend разработчика.

Задача
Необходимо разработать приложение с возможностью прямого и обратного геокодирования (из координат в адрес и наоборот). 
Не требуется разворачивать собственную базу данных с адресами, достаточно использовать сторонние API (Google, Яндекс, другое). 
Таким образом приложение является кэширующим прокси-сервисом для стороннего сервиса геокодирования. Приложение должно предоставлять API с JSON форматом данных.

Требования
1) Java / Kotlin

2) Spring Boot

3) Кэширование результатов запросов. В качестве кэша можно использовать SQL базу данных (MySQL, Postgres, другое), либо NoSQL решение (Memcached, Redis, другое).

4) Логирование ошибок и работы приложения в целом.

5) Покрытие тестами базовой функциональности.

6) Дополнительно (будет преимуществом) Метрики работы приложения (Actuator, Dropwizard, другое).

7) Сборка и запуск приложения в Docker.






# Bacon SNS

A modern social networking application built with Spring Boot

## Project Structure

```
bacon/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── bacon/
│   │   │           ├── controller/    # REST Controllers
│   │   │           ├── config/        # Configuration classes
│   │   │           ├── entity/        # Entity classes
│   │   │           ├── form/          # Form validation classes
│   │   │           ├── mapper/        # MyBatis mappers
│   │   │           └── service/       # Business logic
│   │   └── resources/
│   │       ├── com/bacon/mapper/      # MyBatis XML files
│   │       ├── static/                # CSS, JS files
│   │       ├── templates/             # Thymeleaf templates
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## API Endpoints

- `GET /` - Redirects to home
- `GET /home` - Home page with timeline
- `GET/POST /login` - User login
- `GET/POST /register` - User registration
- `GET /message` - Message list
- `GET/POST /message/{userId}` - Direct messages with a user
- `GET /u/{userId}` - User profile
- `GET /bookmark` - Bookmarked posts
- `GET/POST /search` - Search functionality

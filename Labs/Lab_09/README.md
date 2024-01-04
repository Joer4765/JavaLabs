# Lab 9

## Logging Configuration with Log4j

Log4j is configured through a dedicated configuration file, typically named `log4j2.xml`. In this project, the configuration file is located in the `src/main/resources` directory.

### Console Appender

The project uses a console appender (`Console` in `log4j2.xml`) to log messages to the console. The log messages include a timestamp, thread information, log level, logger name, and the actual log message.

### File Appender with RollingFile Appender

Additionally, a file appender (`RollingFile` in `log4j2.xml`) is configured to log messages to a file (`logs/app.log`). This file appender supports log rotation based on time and size, ensuring that log files do not become excessively large.

## Log Levels

The root logger (`<Root level="info">`) is configured with a log level of `info`, which means log messages at the `info` level and above will be captured. Adjust the log level as needed for your application's requirements.

## Logging Integration in Code

Logging is integrated into the codebase, primarily within exception handling scenarios. For example, in the `Main` class, errors are logged with stack traces:

```java
catch (SQLException e) {
    logger.error("An error occurred: {}", e.getMessage(), e);
    throw new RuntimeException(e);
}
```

## Lombok Integration

This project utilizes Project Lombok to simplify the creation of Java classes. Lombok's annotations are used to generate boilerplate code, such as getters, setters, and constructors, automatically. This reduces the amount of code you need to write and maintain.

### About Lombok

[Lombok](https://projectlombok.org/) is a library that helps to reduce boilerplate code in Java classes. It provides annotations that, when processed during compilation, generate the code for common Java tasks.

### Lombok Annotations Used in This Project

- `@Data`: Generates getters, setters, `toString`, `equals`, and `hashCode` methods for all fields.
- `@NonNull`: Ensures that the annotated field cannot be `null`.

## Changes Made

### Added
- Implementation of reading and saving teachers to JSON.
- Unit tests for the `TeacherDAO` class.

### Modified
- Updated the `Main` class to include JSON read and save operations.
- Updated the `TeacherDAO` class to include methods for reading all teachers.

## Getting Started

These instructions will guide you on how to set up and run the project on your local machine.

### Prerequisites

- Java Development Kit (JDK) installed (version X.X or later)
- Gradle build tool installed (optional, if you are using Gradle)

### Installing

Clone the repository to your local machine:

```bash
git clone https://github.com/joert/Lab_9.git
```

Certainly! Here's an example set of instructions for building, running, and testing the application using Gradle:

### Building the Application:

1. Open a terminal/command prompt.
2. Navigate to the project's root directory:

    ```bash
    cd Lab_9
    ```

3. Build the application using Gradle:

    ```bash
    ./gradlew build
    ```

   or on Windows:

    ```bash
    gradlew.bat build
    ```

   This will download dependencies, compile the code, and generate the executable JAR file.

### Running the Application:

1. After building the project, run the application:

    ```bash
    ./gradlew run
    ```

   or on Windows:

    ```bash
    gradlew.bat run
    ```

   This will execute the `main` method in the `Main` class, launching the application.

### Testing the Application:

1. Run the unit tests:

    ```bash
    ./gradlew test
    ```

   or on Windows:

    ```bash
    gradlew.bat test
    ```

   This will execute the unit tests and provide feedback on test results.

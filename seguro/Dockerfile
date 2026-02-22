# ===== Stage 1 - Build =====
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copia apenas o pom primeiro (melhora cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código
COPY src ./src

# Executa o package
RUN mvn clean package -DskipTests


# ===== Stage 2 - Runtime =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
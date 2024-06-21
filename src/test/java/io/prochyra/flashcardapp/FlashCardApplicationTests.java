package io.prochyra.flashcardapp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Tag("integration")
@SpringBootTest
@Testcontainers(disabledWithoutDocker = true)
class FlashCardApplicationTests {

    public static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER;

    static {
        //noinspection resource
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16"))
                .withDatabaseName("postgrestest")
                .withUsername("test")
                .withPassword("test")
                .withReuse(true);

        POSTGRESQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
    }

    @Test
    void contextLoads() {
    }

}

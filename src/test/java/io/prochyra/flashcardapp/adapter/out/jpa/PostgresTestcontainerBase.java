package io.prochyra.flashcardapp.adapter.out.jpa;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Tag("integration")
@Testcontainers(disabledWithoutDocker = true)
@DataJpaTest(properties = "spring.test.database.replace=NONE")
public abstract class PostgresTestcontainerBase {

    public static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER;

    static {
        //noinspection resource
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"))
                .withDatabaseName("postgrestest")
                .withUsername("test")
                .withPassword("test")
                .withReuse(true);

        POSTGRESQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void datasourceConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }
}

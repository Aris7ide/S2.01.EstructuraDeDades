package N1.EX1.repository;

import N1.EX1.models.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProviderRepositoryTest {

    private Connection connection;
    private ProviderRepository repository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);
        repository = new ProviderRepository();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    void shouldCreateProvider() throws SQLException {
        Provider provider = new Provider(
                "Optica Test Rollback",
                "Calle Falsa",
                "123",
                "1",
                "A",
                "Barcelona",
                "08001",
                "España",
                "930000000",
                "930000001",
                "Z1111111Z"
        );

        repository.save(provider);

        assertThat(provider.getName()).isEqualTo("Optica Test Rollback");
    }




}
package N1.EX1.repository;

import N1.EX1.models.Glasses;
import N1.EX1.models.Provider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GlassesRepositoryTest {

    private Connection connection;
    private GlassesRepository glassesRepository;
    private ProviderRepository providerRepository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);

        providerRepository = new ProviderRepository();
        glassesRepository = new GlassesRepository();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    void shouldAddGlasses() throws SQLException {
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
        providerRepository.save(connection, provider);

        Glasses glasses = new Glasses(
                "RayBan",
                0.50,
                0.70,
                "pasta",
                "Red",
                "Black",
                "Black",
                345.67,
                provider
        );
        glassesRepository.save(connection, glasses);

        assertThat(glasses.getBrand()).isEqualTo("RayBan");
        assertThat(glasses.getPrice()).isNotEqualTo(0);
        assertThat(glasses.getProvider()).isNotNull();
    }

}
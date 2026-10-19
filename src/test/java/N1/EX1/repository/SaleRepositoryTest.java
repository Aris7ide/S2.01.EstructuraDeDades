package N1.EX1.repository;

import N1.EX1.models.Client;
import N1.EX1.models.Glasses;
import N1.EX1.models.Provider;
import N1.EX1.models.Sale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SaleRepositoryTest {

    private Connection connection;
    private SaleRepository saleRepository;
    private ClientRepository clientRepository;
    private GlassesRepository glassesRepository;
    private ProviderRepository providerRepository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);

        saleRepository = new SaleRepository();
        clientRepository = new ClientRepository();
        glassesRepository = new GlassesRepository();
        providerRepository = new ProviderRepository();
    }



    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    void shouldSaveSale() {
        Client client = new Client("Aris",
                "Carrer de Lancaster 4, 2-2",
                691760715,
                "Aris7die@gmail.com",
                null,
                "Fabio"
        );
        clientRepository.save(connection, client);

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

        Sale sale = new Sale(
                glasses,
                client
        );
        saleRepository.save(connection, sale);
    }


}
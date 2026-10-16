package N1.EX1.repository;

import N1.EX1.models.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    private Connection connection;

    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);

        clientRepository = new ClientRepository();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    void shouldAddClient() throws SQLException {
        Client client1 = new Client(
                "Aris",
                "Carrer de Lancaster 4, 2-2",
                691760715,
                "Aris7die@gmail.com",
                null,
                "Fabio"

        );

        clientRepository.save(connection, client1);

        Client client2 = new Client(
                "Martina",
                "Muntaner 245",
                674585643,
                "Marti@gmail.com",
                client1,
                "Fabio"
        );
        clientRepository.save(connection, client2);
    }

}
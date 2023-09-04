package pl.ciesielski.dominik.app.cardealerapp.dao.repository;

import org.junit.jupiter.api.Test;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.AddressEntity;
import pl.ciesielski.dominik.app.cardealerapp.dao.entity.ClientEntity;

class ClientRepositoryTest {

    @Test
    void create() {
        // Given
        ClientRepository clientRepository = new ClientRepository();

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity("Warsaw");

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setAddress(addressEntity);

        // When
        clientRepository.create(clientEntity);
        // Then

    }
}
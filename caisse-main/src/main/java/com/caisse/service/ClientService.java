package com.caisse.service;

import java.util.List;
import java.util.Optional;

import com.caisse.entity.Client;
import com.caisse.repository.ClientRepository;
import com.caisse.service.IService.IClientService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        if (client.getNom() == null || client.getNom().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add client", e);
        }
    }

    @Override
    public Client editClient(Client client) throws RuntimeException {
        if (client.getId() == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        if (client.getNom() == null || client.getNom().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }
        try {
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update client", e);
        }
    }

    @Override
    public void deleteClient(Integer idClient) {
        Optional<Client> client = clientRepository.findById(idClient);

        client.ifPresent(c -> {
            clientRepository.delete(c);
            log.info("Client with id " + idClient + " has been deleted");
        });
    }

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }
}

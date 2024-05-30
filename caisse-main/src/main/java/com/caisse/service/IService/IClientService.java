package com.caisse.service.IService;

import com.caisse.entity.Client;

import java.util.List;

public interface IClientService {

    Client addClient(Client client);

    Client editClient(Client client);

    void deleteClient(Integer idClient);

    List<Client> retrieveAllClients();
}
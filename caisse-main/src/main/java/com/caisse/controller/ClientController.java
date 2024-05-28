package com.caisse.controller;

import java.util.List;

import com.caisse.entity.Client;
import com.caisse.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client savedClient = clientService.addClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/edit-client/{id}")
    public ResponseEntity<Client> editClient(@PathVariable Integer id, @RequestBody Client client) {
        client.setId(id);
        Client updatedClient = clientService.editClient(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/delet-client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clients-all")
    public List<Client> retrieveAllClients(){
        return clientService.retrieveAllClients();
    }

    /*  public ResponseEntity<List<Client>> retrieveAllClients() {
        List<Client> clients = clientService.retrieveAllClients();
        return ResponseEntity.ok(clients);
    }*/
}

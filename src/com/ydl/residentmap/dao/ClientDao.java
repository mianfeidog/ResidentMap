package com.ydl.residentmap.dao;

import com.ydl.residentmap.model.Client;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ClientDao {

    public Client createClient(Client client);
    public Client updateClient(Client client);
    public void deleteClient(Long clientId);

    public Client findOne(Long clientId);

    public List<Client> findAll();

    public Client findByClientId(String clientId);
    public Client findByClientSecret(String clientSecret);

}

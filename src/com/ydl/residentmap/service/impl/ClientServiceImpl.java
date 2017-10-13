package com.ydl.residentmap.service.impl;


import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ydl.residentmap.service.ClientService;
import com.ydl.residentmap.model.Client;
import com.ydl.residentmap.dao.ClientDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;



/**
 * <p>User:
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Resource
    private ClientDao clientDao;

    @Override
    public Client createClient(Client client) {

        client.setClientId(UUID.randomUUID().toString());
        client.setClientSecret(UUID.randomUUID().toString());
        return clientDao.createClient(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientDao.updateClient(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientDao.deleteClient(clientId);
    }

    @Override
    public Client findOne(Long clientId) {
        return clientDao.findOne(clientId);
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findByClientId(String clientId) {
        return clientDao.findByClientId(clientId);
    }

    @Override
    public Client findByClientSecret(String clientSecret) {
        return clientDao.findByClientSecret(clientSecret);
    }
}

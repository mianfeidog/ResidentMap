package com.ydl.residentmap.dao.impl;


import com.ydl.residentmap.dao.BaseDao;
import com.ydl.residentmap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.ydl.residentmap.dao.ClientDao;
import com.ydl.residentmap.model.Client;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class ClientDaoImpl implements ClientDao {
    @Resource
    private BaseDao<User> baseDAO;

    public Client createClient(Client client) {
        return client;
    }

    public Client updateClient(Client client){
        return client;
    }
    public void deleteClient(Long clientId){

    }

    public Client findOne(Long clientId) {
        Client client = new Client();
        return client;
    }

    public List<Client> findAll(){
        List<Client> clients = new ArrayList<>();
        return clients;
    }

    public Client findByClientId(String clientId){
        Client client = new Client();
        return client;
    }

    public Client findByClientSecret(String clientSecret){
        Client client = new Client();
        return client;
    }


//    @Resource
//    private JdbcTemplate jdbcTemplate;
//
//    public Client createClient(final Client client) {
//        final String sql = "insert into oauth2_client(client_name, client_id, client_secret) values(?,?,?)";
//
//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
//                int count = 1;
//                psst.setString(count++, client.getClientName());
//                psst.setString(count++, client.getClientId());
//                psst.setString(count++, client.getClientSecret());
//                return psst;
//            }
//        }, keyHolder);
//
//        client.setId(keyHolder.getKey().longValue());
//        return client;
//    }
//
//    public Client updateClient(Client client) {
//        String sql = "update oauth2_client set client_name=?, client_id=?, client_secret=? where id=?";
//        jdbcTemplate.update(
//                sql,
//                client.getClientName(), client.getClientId(), client.getClientSecret(), client.getId());
//        return client;
//    }
//
//    public void deleteClient(Long clientId) {
//        String sql = "delete from oauth2_client where id=?";
//        jdbcTemplate.update(sql, clientId);
//    }
//
//    @Override
//    public Client findOne(Long clientId) {
//        String sql = "select id, client_name, client_id, client_secret from oauth2_client where id=?";
//        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
//        if(clientList.size() == 0) {
//            return null;
//        }
//        return clientList.get(0);
//    }
//
//    @Override
//    public List<Client> findAll() {
//        String sql = "select id, client_name, client_id, client_secret from oauth2_client";
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class));
//    }
//
//
//    @Override
//    public Client findByClientId(String clientId) {
//        String sql = "select id, client_name, client_id, client_secret from oauth2_client where client_id=?";
//        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientId);
//        if(clientList.size() == 0) {
//            return null;
//        }
//        return clientList.get(0);
//    }
//
//
//    @Override
//    public Client findByClientSecret(String clientSecret) {
//        String sql = "select id, client_name, client_id, client_secret from oauth2_client where client_secret=?";
//        List<Client> clientList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), clientSecret);
//        if(clientList.size() == 0) {
//            return null;
//        }
//        return clientList.get(0);
//    }
}

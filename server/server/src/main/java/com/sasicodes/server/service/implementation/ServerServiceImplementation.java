package com.sasicodes.server.service.implementation;

import com.sasicodes.server.model.Server;
import com.sasicodes.server.repository.ServerRepository;
import com.sasicodes.server.service.ServerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.sasicodes.server.enumeration.Status.SERVER_DOWN;
import static com.sasicodes.server.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class ServerServiceImplementation implements ServerService {

    private final ServerRepository serverRepository;
    @Override
    public Server createServer(Server server) {
        log.info("saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    private String setServerImageUrl() {
        return null;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)?SERVER_UP:SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers");
        List<Server> servers = serverRepository.findAll(PageRequest.of(0, limit)).toList();
        return servers;
    }

    @Override
    public Server get(Long id) {
        log.info("fetching server by id: {}", id);
        Optional<Server> byId = serverRepository.findById(id);
        return byId.get();
    }

    @Override
    public Server update(Server server) {
        log.info("updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("deleting server by id: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }
}

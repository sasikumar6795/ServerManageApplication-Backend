package com.sasicodes.server.service.implementation;

import com.sasicodes.server.model.Server;
import com.sasicodes.server.repository.ServerRepository;
import com.sasicodes.server.service.ServerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        String[] imageNames={"server1.png","server2.png","server3.png","server4.png"};

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(4)])
                .toUriString();
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
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
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

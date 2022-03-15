package com.sasicodes.server.controller;

import com.sasicodes.server.model.Response;
import com.sasicodes.server.model.Server;
import com.sasicodes.server.service.implementation.ServerServiceImplementation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

import static com.sasicodes.server.enumeration.Status.SERVER_UP;
import static java.time.LocalDate.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/server")
public class ServerController {

    private final ServerServiceImplementation serverServiceImplementation;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers()
    {
        return ResponseEntity.ok(
                Response.builder()
                        .localDateTime(LocalDateTime.from(now()))
                        .status(OK)
                        .statusCode(OK.value())
                        .message("servers retrieved")
                        .data(of("servers", serverServiceImplementation.list(30)))
                        .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> getServers(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceImplementation.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .localDateTime(LocalDateTime.from(now()))
                        .status(OK)
                        .statusCode(OK.value())
                        .message(server.getStatus() ==SERVER_UP ? "Ping success" : "Ping Failure")
                        .data(of("server", server))
                        .build()
        );
    }


    @PostMapping("/save")
    public ResponseEntity<Response> getServers(@RequestBody @Valid  Server server) {
        return ResponseEntity.ok(
                Response.builder()
                        .localDateTime(LocalDateTime.from(now()))
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .message("server created")
                        .data(of("server", serverServiceImplementation.createServer(server)))
                        .build()
        );
    }
}

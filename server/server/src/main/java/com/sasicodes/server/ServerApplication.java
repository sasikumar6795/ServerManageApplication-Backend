package com.sasicodes.server;

import com.sasicodes.server.model.Server;
import com.sasicodes.server.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.sasicodes.server.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository)
	{
		return args -> {
			serverRepository
					.save(new Server(null, "192.168.1.160", "Linux", "16 GB", "PC", "http://localhost:8080/server/image/server1.png", SERVER_UP));
		};
	}
}

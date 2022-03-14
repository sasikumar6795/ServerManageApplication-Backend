package com.sasicodes.server.model;

import com.sasicodes.server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Server {

    @Id
    @SequenceGenerator(name = "server_id_sequence",
            sequenceName = "server_id_sequence",
            allocationSize =1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "server_id_sequence"
    )
    @Column(name="ID",nullable = false, updatable =false)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "IP address cannot be empty")
    private String ipAddress;
    private String name;
    private Long memory;
    private String type;
    private String imageUrl;
    private Status status;


}

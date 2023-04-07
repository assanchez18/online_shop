package com.online.shop.user.persistence;

import com.online.shop.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name="user", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    @Column
    private String name;

    public UserEntity(User user) {
        this.id = UUID.randomUUID();
        this.name = user.getName();
    }

    //@Column(nullable = false)
    //private UserRole role;

    //@Column(nullable = false)
    //private LocalDate creationTime;
}

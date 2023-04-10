package com.online.shop.user.persistence;

import com.online.shop.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@Entity
@Table(name="users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    @Column
    private String name;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }


    public UserEntity(ResultSet rs) throws SQLException {
        this.id = rs.getObject("id", UUID.class);
        this.name = rs.getObject("name", String.class);
    }
    //@Column(nullable = false)
    //private UserRole role;

    //@Column(nullable = false)
    //private LocalDate creationTime;
}

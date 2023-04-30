package com.online.shop.user.persistence;

import com.online.shop.user.User;
import com.online.shop.user.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name="users", schema = "production")
@PrimaryKeyJoinColumn(name="id")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = UserRepositoryImpl.ID)
    private UUID id;

    @Column(name = UserRepositoryImpl.NAME)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = UserRepositoryImpl.ROLE)
    private UserRole role;

    @Column(name = UserRepositoryImpl.CREATION_TIME)
    private Timestamp creationTime;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
        this.creationTime = new Timestamp(Instant.now().toEpochMilli());
    }


    public UserEntity(ResultSet rs) throws SQLException {
        this.id = rs.getObject(UserRepositoryImpl.ID, UUID.class);
        this.name = rs.getObject(UserRepositoryImpl.NAME, String.class);
        this.role = UserRole.valueOf(rs.getObject(UserRepositoryImpl.ROLE, String.class));
        this.creationTime = rs.getObject(UserRepositoryImpl.CREATION_TIME, Timestamp.class);
    }
    //@Column(nullable = false)
    //private UserRole role;

    //@Column(nullable = false)
    //private LocalDate creationTime;
}

package com.online.shop.user.persistence;

import com.online.shop.user.User;
import com.online.shop.user.UserRepository;
import com.online.shop.user.persistence.filters.UserFilters;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final String ID = "id";
    public static final String TABLE = "production.\"users\"";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String CREATION_TIME = "creationTime";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<UserEntity> getUsers(UserFilters userFilters) {
        StringBuilder sqlBuilder = new StringBuilder(buildSelectAllQuery());
        MapSqlParameterSource params = new MapSqlParameterSource();
        buildPreparedSqlStatement(sqlBuilder, params, userFilters);
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, this::mapRow);
    }

    private String buildSelectAllQuery() {
        return String.format("SELECT * FROM %s ",TABLE);
    }


    private void buildPreparedSqlStatement(StringBuilder sqlBuilder, MapSqlParameterSource params, UserFilters filters) {
        if (!filters.getFilters().isEmpty()) {
            sqlBuilder.append(" WHERE ");
            final String AND = " AND ";
            filters.getFilters().forEach(filter -> {
                sqlBuilder.append(filter.getFilterQuery()).append(AND);
                params.addValues(filter.getNamedParam());
            });
            sqlBuilder.delete(sqlBuilder.length() - AND.length(), sqlBuilder.length());
        }
        if (null != filters.getOrderBy()) {
            sqlBuilder.append(filters.getOrderBy().getFilterQuery());
        }

        /*
        if (null != filters.pagination()) {
            sqlBuilder.append(filters.pagination().getFilterQuery());
        }
         */
    }

    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserEntity(rs);
    }

    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity(user);
        final SqlParameterSource source = createSource(entity);
        String sql = String.format("insert into %s (%s, %s) values (:%s, :%s)", TABLE, ID, NAME, ID, NAME);
        namedParameterJdbcTemplate.update(sql, source);
    }

    private SqlParameterSource createSource(UserEntity user) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(ID, user.getId());
        source.addValue(NAME, user.getName());
        return source;

    }
/*
    private final JdbcTemplate jdbcTemplate;
    private final UserConverter userConverter;

    private static final String[] INSERT_COLS = {
            ID,
            NAME,
            ROLE,
            CREATION_TIME
    };
    private static final String[] UPDATE_COLS = {
            ID,
            NAME,
            ROLE,
            CREATION_TIME
    };


    @Override
    public Set<User> getUsers(Set<UUID> userIds) {
        List<UserEntity> users = jdbcTemplate.query("Select * from UserRepository", this::mapRow);
        return users.stream().map(userConverter::userEntityToUser).collect(Collectors.toSet());
    }

    @Override
    public User save(User user) {
        try {
            final SqlParameterSource source = createSource(user);
            final String upsertSql = upsert(TABLE, asList(INSERT_COLS.clone()), asList(UPDATE_COLS.clone()), ID);

            jdbcTemplate.update(upsertSql, source);
            return user;
        }catch (Exception e) {
            throw e;
        }
    }

    private UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        final UserEntity user = new UserEntity();
        user.setId(rs.getObject(ID, UUID.class));
        user.setName(rs.getString(NAME));
        user.setRole(rs.getObject(ROLE, UserRole.class));
        user.setCreationTime(rs.getObject(CREATION_TIME, LocalDate.class));
        return user;
    }
    private SqlParameterSource createSource(User user) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(ID, user.getId());
        source.addValue(NAME, user.getName());
        source.addValue(ROLE, user.getRole());
        source.addValue(CREATION_TIME, LocalDate.now());
        return source;
    }

    public static String upsert(String table, List<String> insertFields, List<String> updateFields, String uniqueKey) {
        return "insert into " + table + " (" + StringUtils.join(insertFields, ',') + ") values (" + (String)insertFields.stream().map((p) -> {
            return ":" + p;
        }).collect(Collectors.joining(", ")) + ")" + " on conflict (" + uniqueKey + ") do update set " + (String)updateFields.stream().map((p) -> {
            return p + " = :" + p;
        }).collect(Collectors.joining(", "));
    }

 */
}

package com.online.shop.user.persistence;

import com.online.shop.user.User;
import com.online.shop.user.UserRepository;
import com.online.shop.user.persistence.filters.UserFilters;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    public static final String ID = "id";
    public static final String TABLE = "production.\"users\"";
    public static final String NAME = "name";
    public static final String ROLE = "role";
    public static final String CREATION_TIME = "creation_time";

    private static final List<String> INSERT_COLS = List.of(
            ID,
            NAME,
            ROLE,
            CREATION_TIME
    );

    private static final List<String> UPDATE_COLS = List.of(
            NAME,
            ROLE
    );

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
            filters.getFilters().values().forEach(filter -> {
                sqlBuilder.append("(").append(filter.getFilterQuery()).append(")").append(AND);
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
        namedParameterJdbcTemplate.update(buildUpsertQuery(), createSource(entity));
    }

    private String buildUpsertQuery() {
        return String.format("INSERT INTO %s (%s) VALUES (%s) ON CONFLICT (%s) DO UPDATE SET %s;",
                TABLE, buildColumns(), buildParameters(), ID, buildUpdateColumns());
    }

    private String buildUpdateColumns() {
        return StringUtils.join(UPDATE_COLS.stream()
                .map(column -> column + "=:" + column)
                .collect(Collectors.toList()),',');
    }

    private String buildColumns() {
        return StringUtils.join(INSERT_COLS, ',');
    }

    private String buildParameters() {
        return StringUtils.join(INSERT_COLS.stream()
                .map(column -> ":" + column)
                .collect(Collectors.toList()),',');
    }

    private SqlParameterSource createSource(UserEntity user) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(ID, user.getId());
        source.addValue(NAME, user.getName());
        source.addValue(ROLE, user.getRole().name());
        source.addValue(CREATION_TIME, user.getCreationTime());
        return source;
    }
}

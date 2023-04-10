package com.online.shop.filters;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public abstract class InternalIdsFilter implements InternalFilter {

    private final Set<UUID> ids;
    private final String tableName;
    private final String columnName;
    private final String paramName;


    @Override
    public String getFilterQuery() {
        return String.format("%s.%s IN (:%s)", tableName, columnName, paramName);
    }

    @Override
    public Map<String, ?> getNamedParam() {
        return Map.of(paramName, ids);
    }

}

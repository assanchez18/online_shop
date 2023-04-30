package com.online.shop.filters;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public abstract class InternalOrderFilter implements InternalFilter {

    protected final FilterField orderField;
    private final OrderType orderType;
    private final String table;

    @Override
    public String getFilterQuery() {
        return String.format(" ORDER BY %s.%s %s", table, getFieldName(), orderType) ;
    }

    @Override
    public Map<String, ?> getNamedParam() {
        return Map.of();
    }


    protected abstract String getFieldName();
}

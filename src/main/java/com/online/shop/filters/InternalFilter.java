package com.online.shop.filters;

import java.util.Map;

public interface InternalFilter {
    String getFilterQuery();

    Map<String, ?> getNamedParam();

}

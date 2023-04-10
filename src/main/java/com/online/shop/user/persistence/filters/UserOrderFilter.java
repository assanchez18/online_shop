package com.online.shop.user.persistence.filters;

import com.online.shop.filters.FilterField;
import com.online.shop.filters.InternalOrderFilter;
import com.online.shop.filters.OrderType;
import com.online.shop.user.persistence.UserRepositoryImpl;

public class UserOrderFilter extends InternalOrderFilter implements InternalUserFilter {

    public UserOrderFilter(OrderType orderType, String table, FilterField orderField) {
        super(orderType, table, orderField);
    }

    @Override
    protected String getFieldName() {
        return switch ((UserFilterField) orderField) {
            case USER_ID -> UserRepositoryImpl.ID;
        };

    }
}

package com.online.shop.user.persistence.filters;

import com.online.shop.filters.FilterField;
import com.online.shop.filters.InternalOrderFilter;
import com.online.shop.filters.OrderType;
import com.online.shop.user.persistence.UserRepositoryImpl;

public class UserOrderFilter extends InternalOrderFilter implements InternalUserFilter {

    public UserOrderFilter(FilterField orderField, OrderType orderType) {
        super(orderField, orderType, UserRepositoryImpl.TABLE);
    }

    @Override
    protected String getFieldName() {
        return switch ((UserFilterField) orderField) {
            case USER_ID, TEAM_LEAD -> UserRepositoryImpl.ID;
            case USER_ROLE -> UserRepositoryImpl.ROLE;
        };

    }
}

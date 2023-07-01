package com.online.shop.user.persistence.filters;

import com.online.shop.filters.OrderType;
import com.online.shop.user.UserRole;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Getter
public class UserFilters {

    private final Map<UserFilterField, InternalUserFilter> filters;
    private final UserOrderFilter orderBy;
    //private final PageFilter pageination;

    private final static UserOrderFilter DEFAULT_ORDER_BY = new UserOrderFilter(UserFilterField.USER_ID, OrderType.ASC);
    private UserFilters(Map<UserFilterField, InternalUserFilter> filter, UserOrderFilter orderBy) {
        this.filters = filter;
        this.orderBy = null == orderBy ? DEFAULT_ORDER_BY : orderBy;

    }

    public static UserFiltersBuilder builder() {
        return new UserFiltersBuilder();
    }

    public static class UserFiltersBuilder {
        private Map<UserFilterField, InternalUserFilter> filters;
        private UserOrderFilter orderBy;

        public UserFiltersBuilder() {
            this.filters = new HashMap<>();
            this.orderBy = null;
        }

        public UserFiltersBuilder withUserId(UUID id) {
            return null != id ? withUserIds(Set.of(id)) : this;
        }

        public UserFiltersBuilder withUserIds(Set<UUID> id) {
            if(!isNullOrEmtpy(id)) {
                this.filters.put(UserFilterField.USER_ID, new UserIdsFilter(id));
            }
            return this;
        }

        public UserFiltersBuilder withUserRole(UserRole role) {
            return null != role ? withUserRoles(Set.of(role)) : this;
        }

        public UserFiltersBuilder withUserRoles(Set<UserRole> roles) {
            if(!isNullOrEmtpy(roles)) {
                this.filters.put(UserFilterField.USER_ROLE, new UserRoleFilter(roles));
            }
            return this;
        }

        public UserFiltersBuilder withTeamLead(Set<UUID> id, UserRole role) {
            if(!isNullOrEmtpy(id) && null != role) {
                this.filters.put(UserFilterField.TEAM_LEAD, new UserTeamLeadFilter(id, role));
            }
            return this;
        }

        public UserFiltersBuilder orderBy(UserFilterField field, OrderType type) {
            if(null == field || null == type) {
                this.orderBy = DEFAULT_ORDER_BY;
            }
            else {
                this.orderBy = new UserOrderFilter(field, type);
            }
            return this;
        }
        public UserFilters build() {
            return new UserFilters(filters, orderBy);
        }

        private boolean isNullOrEmtpy(Collection<?> values) {
            return CollectionUtils.isEmpty(values);
        }
    }
}

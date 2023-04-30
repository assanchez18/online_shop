package com.online.shop.user.persistence.filters;

import com.online.shop.filters.InternalFilter;
import com.online.shop.user.UserRole;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class UserTeamLeadFilter implements InternalFilter, InternalUserFilter {

    private final UserIdsFilter userIdsFilter;
    private final UserRoleFilter userRoleFilter;

    private final String TEAM_LEAD_ID = "teamLeadId";
    private final String TEAM_LEAD_ROLE = "teamLeadRole";

    public UserTeamLeadFilter(Set<UUID> ids, UserRole role) {
        this.userIdsFilter = new UserIdsFilter(ids, TEAM_LEAD_ID);
        this.userRoleFilter = new UserRoleFilter(Set.of(role), TEAM_LEAD_ROLE);
    }

    @Override
    public String getFilterQuery() {
        return String.format("(%s OR %s)", userIdsFilter.getFilterQuery(), userRoleFilter.getFilterQuery());
    }

    @Override
    public Map<String, ?> getNamedParam() {
        Map<String, Object> combinedParams = new HashMap<>();
        combinedParams.putAll(userIdsFilter.getNamedParam());
        combinedParams.putAll(userRoleFilter.getNamedParam());
        return combinedParams;
    }

}

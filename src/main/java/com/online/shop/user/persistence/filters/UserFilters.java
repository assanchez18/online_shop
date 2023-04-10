package com.online.shop.user.persistence.filters;

import com.online.shop.filters.InternalOrderFilter;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserFilters {

    @Builder.Default List<InternalUserFilter> filters = List.of();
    @Builder.Default InternalOrderFilter orderBy = null;
    //PageFilter pageination
}

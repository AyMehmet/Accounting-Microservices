package com.memo.service;







import com.memo.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto findRoleById(Long id);
    List<RoleDto> getFilteredRolesForCurrentUser();

}

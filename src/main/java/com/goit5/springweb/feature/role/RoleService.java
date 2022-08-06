package com.goit5.springweb.feature.role;

import com.goit5.springweb.exception.ValidationException;

import java.util.List;

public interface RoleService {

    void saveRole(Role role) throws ValidationException;

    void deleteRole(Long id);

    Role findByName(String name);

    List<Role> findAll();

    void updateRole(Role role, Long id);

    Role findById(Long id);
}

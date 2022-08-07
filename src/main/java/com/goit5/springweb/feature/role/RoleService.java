package com.goit5.springweb.feature.role;


import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    void deleteRole(Long id);

    Role findByName(String name);

    List<Role> findAll();

    void updateRole(Role role, Long id);

    Role findById(Long id);
}

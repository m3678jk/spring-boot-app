package com.goit5.springweb.feature.role;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public void saveRole(Role role) {
        repository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Role not found"));
        repository.delete(role);
    }

    @Override
    public Role findByName(String name) {
        Role role = repository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("Role not found"));
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = repository.findAll();
        return list;
    }

    @Override
    public void updateRole(Role role, Long id) {
        Role roleToUpdate = findById(id);
        roleToUpdate.setName(role.getName());
        repository.save(roleToUpdate);
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Role not found"));
    }
}

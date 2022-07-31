//package com.goit5.springweb.feature.user;
//
//import com.goit5.springweb.dto.RoleDto;
//import com.goit5.springweb.feature.user.UserDto;
//import com.goit5.springweb.feature.user.role.Role;
//import com.goit5.springweb.feature.user.User;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RoleConverter {
//    public Role fromRoleDtoToRole(RoleDto roleDto) {
//        Role role = new Role();
//        role.setId(roleDto.getId());
//        role.setName(roleDto.getName());
//        return role;
//    }
//
//    public RoleDto fromRoleToRoleDto(Role role) {
//        return RoleDto.builder()
//                .id(role.getId())
//                .name(role.getName())
//                .build();
//    }
//}

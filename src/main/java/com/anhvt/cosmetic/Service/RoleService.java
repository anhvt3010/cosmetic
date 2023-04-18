package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Role;
import com.anhvt.cosmetic.Repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Optional<Role> findById(Long id){
        return roleRepository.findById(id);
    }
    public void save(Role role){
        roleRepository.save(role);
    }
}

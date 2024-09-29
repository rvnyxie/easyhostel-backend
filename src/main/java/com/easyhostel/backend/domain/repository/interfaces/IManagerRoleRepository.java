package com.easyhostel.backend.domain.repository.interfaces;

import com.easyhostel.backend.domain.entity.ManagerRole;
import com.easyhostel.backend.domain.entity.embedded.ManagerRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManagerRoleRepository extends JpaRepository<ManagerRole, ManagerRoleId> {
}

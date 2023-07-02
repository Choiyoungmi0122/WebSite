package com.version1.insight.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	Optional<UserInfo> findByusername(String username);
}
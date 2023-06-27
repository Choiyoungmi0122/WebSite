
package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.table.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, String>{
	Optional<UserInfo> findByname(String name);
}
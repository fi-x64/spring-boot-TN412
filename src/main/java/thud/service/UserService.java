package thud.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thud.entity.Users;

@Repository
public interface UserService extends JpaRepository<Users, Long>{
	
}

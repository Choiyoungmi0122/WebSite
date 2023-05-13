package Calender;

import Table.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<Calender, Integer>{
	
}
package templates;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class UserInfo {

	
	//Calender에 PK 쓰기위해 선언
	@OneToMany(mappedBy="UserInfo")
	private Calender calender;
	
	//Schedule에 PK 쓰기위해 선언
	@ManyToOne(mappedBy="UserInfo")
	private Schedule schedule;
	
}

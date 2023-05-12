package Table;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Calender {
	@Id
	private Integer Calender_Id;
	
	private String Student_Id;
	private LocalDateTime Calender_Register;
	private String Calender_Text;
	private LocalDateTime Calender_Deadline;
	private String Calender_Category;
	private String Calender_Replay;
	@OneToOne
}

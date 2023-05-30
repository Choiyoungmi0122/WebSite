package com.example.demo.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="userinfo")
public class UserInfo {

   @Id
   @Column(name="Student_Id", unique = true)
   private Integer id;
   
   @Column(name="Student_Name")
   private String name;
   
   @Column(name="Pwd")
   private String pwd;
   
   @Column(name="Email", unique = true)
   private String email;
  
<<<<<<< HEAD
}
=======

}
>>>>>>> branch 'Calender' of https://github.com/Choiyoungmi0122/WebSite.git

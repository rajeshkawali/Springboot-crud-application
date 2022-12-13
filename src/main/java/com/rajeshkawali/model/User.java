package com.rajeshkawali.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Rajesh_Kawali
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@ApiModelProperty("ID is unique to user")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(name = "firstname")
	@ApiModelProperty("First Name of the User.")
	private String firstName;

	@NotBlank
	@Column(name = "lastname")
	@ApiModelProperty("Last Name of the User.")
	private String lastName;

	@ApiModelProperty("Age of the User.")
	private Integer age;

	@NotBlank
	@ApiModelProperty("Gender of the User.")
	private String gender;

	@NotBlank
	@ApiModelProperty("Role of the User.")
	private String role;
}

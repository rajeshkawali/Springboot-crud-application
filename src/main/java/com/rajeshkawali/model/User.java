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
/*
Java Validation Annotations:-->

@NotNull: This annotation validates that the annotated property value is not null. If the value is null, a constraint violation is raised.
@AssertTrue: This annotation validates that the annotated property value is true. If the value is false, a constraint violation is raised.
@Size: This annotation validates that the annotated property value has a size between the attributes min and max. This can be applied to String, Collection, Map, and array properties.
@Min: This annotation validates that the annotated property has a value no smaller than the value attribute. This can be applied to numeric properties.
@Max: This annotation validates that the annotated property has a value no larger than the value attribute. This can be applied to numeric properties.
@Email: This annotation validates that the annotated property is a valid email address.
@NotEmpty: This annotation validates that the property is not null or empty. This can be applied to String, Collection, Map or Array values.
@NotBlank: This annotation can be applied only to text values and validates that the property is not null or whitespace.
@Positive and @PositiveOrZero: These annotations apply to numeric values and validate that they are strictly positive, or positive including 0.
@Negative and @NegativeOrZero: These annotations apply to numeric values and validate that they are strictly negative, or negative including 0.
@Past and @PastOrPresent: These annotations validate that a date value is in the past or the past including the present. They can be applied to date types including those added in Java 8.
@Future and @FutureOrPresent: These annotations validate that a date value is in the future, or in the future including the present. They can also be applied to date types.
@Digits: validates that the annotated property is a numeric value with a specified number of digits in total, and a specified number of digits to the right of the decimal point.
@Pattern: validates that the annotated property matches a specified regular expression pattern.

*/
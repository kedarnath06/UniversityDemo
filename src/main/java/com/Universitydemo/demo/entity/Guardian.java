package com.Universitydemo.demo.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(
                        name = "guardian_email",
                        nullable = false
                )
        ),
        @AttributeOverride(
                name = "phone",
                column = @Column(
                        name = "guardian_mobile",
                        nullable = false
                )
        )}
)
public class Guardian {

    private String name;
    private String email;
    private int phone;

}

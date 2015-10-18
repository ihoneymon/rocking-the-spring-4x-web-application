package org.ksug.seminar.spring4xweb.v41;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.Assert;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Rest implements Serializable {
    private static final long serialVersionUID = -8705689122722779290L;

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    public Rest(String name) {
        setName(name);
    }

    /**
     * @param name
     */
    private void setName(String name) {
        Assert.hasText(name);
        this.name = name;
    }
}

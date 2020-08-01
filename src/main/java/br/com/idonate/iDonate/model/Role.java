package br.com.idonate.iDonate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "role")
@EqualsAndHashCode(callSuper = false, of = {"id"})
@ToString(of = {"id"})
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "name_role")
    private String nameRole;

    @Override
    public String getAuthority() {
        return this.nameRole;
    }

}

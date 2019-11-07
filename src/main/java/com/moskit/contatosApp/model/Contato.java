package com.moskit.contatosApp.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contatos")
@DynamicUpdate(true)
public class Contato {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @SequenceGenerator(
            name = "contato_generator",
            sequenceName = "contato_sequence"
    )
    private Long id;

    private String nome;

    @Column(name = "telefone_comercial")
    private String telefoneComercial;

    @Column(name = "telefone_residencial")
    private String telefoneResidencial;

    @Column(name = "telefone_celular")
    private String telefoneCelular;

    @Column(name = "email_comercial")
    private String emailComercial;

    @Column(name = "email_residencial")
    private String emailResidencial;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    private Boolean favorito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public String getEmailComercial() {
        return emailComercial;
    }

    public void setEmailComercial(String emailComercial) {
        this.emailComercial = emailComercial;
    }

    public String getEmailResidencial() {
        return emailResidencial;
    }

    public void setEmailResidencial(String emailResidencial) {
        this.emailResidencial = emailResidencial;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }
}

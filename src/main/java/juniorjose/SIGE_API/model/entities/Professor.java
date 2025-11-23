package juniorjose.SIGE_API.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String nome;
    private String telefone;
    private String logradouro;
    private String genero;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Professor(String cpf, String nome, String telefone, String logradouro, String genero, Usuario usuario) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.genero = genero;
        this.usuario = usuario;
    }

    public Professor() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

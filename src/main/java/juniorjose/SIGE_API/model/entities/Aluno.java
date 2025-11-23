package juniorjose.SIGE_API.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String cpf;

    private String matricula;
    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_nascimento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data_matricula;

    private String genero;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Aluno(String cpf, String matricula, String nome, LocalDate data_nascimento, LocalDate data_matricula, String genero, String telefone, Usuario usuario) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.data_matricula = data_matricula;
        this.genero = genero;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public Aluno () {}

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public LocalDate getData_matricula() {
        return data_matricula;
    }

    public void setData_matricula(LocalDate data_matricula) {
        this.data_matricula = data_matricula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

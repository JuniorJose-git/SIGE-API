package juniorjose.SIGE_API.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private  Long capacidade;

    public Sala(){}

    public Sala(Long id, String nome, Long capacidade){
        this.id=id;
        this.nome=nome;
        this.capacidade=capacidade;
    }

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

    public Long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Long capacidade) {
        this.capacidade = capacidade;
    }
}

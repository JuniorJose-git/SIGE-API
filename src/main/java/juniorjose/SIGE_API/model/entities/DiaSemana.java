package juniorjose.SIGE_API.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="dias_semana")
public class DiaSemana {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private Long numero;

    public DiaSemana(){}

    public DiaSemana(Long id, String nome, Long numero){
        this.id=id;
        this.nome=nome;
        this.numero=numero;
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

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}

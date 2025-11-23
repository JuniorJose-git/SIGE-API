package juniorjose.SIGE_API.model.entities;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name="horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate horarioInicio;
    private LocalDate horarioFim;
    private String nome;

    public Horario(){}

    public Horario(Long id,
                   LocalDate horarioInicio,
                   LocalDate horarioFim,
                   String nome){
        this.id=id;
        this.horarioInicio=horarioInicio;
        this.horarioFim=horarioFim;
        this.nome=nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDate horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDate getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalDate horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

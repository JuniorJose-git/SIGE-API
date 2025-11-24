package juniorjose.SIGE_API.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name="horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalTime horarioInicio;

    private LocalTime horarioFim;

    private String nome;

    public Horario(){}

    public Horario(Long id,
                   LocalTime horarioInicio,
                   LocalTime horarioFim,
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

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

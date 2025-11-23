package juniorjose.SIGE_API.model.entities;
import jakarta.persistence.*;

@Entity
@Table(name="agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = true)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "dia_semana_id", nullable = true)
    private DiaSemana diaSemana;

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = true)
    private Horario horario;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = true)
    private Turma turma;

    public Agendamento(){}

    public Agendamento(Long id, Sala sala, DiaSemana diaSemana, Horario horario, Turma turma){
        this.id=id;
        this.sala=sala;
        this.diaSemana=diaSemana;
        this.horario=horario;
        this.turma=turma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}

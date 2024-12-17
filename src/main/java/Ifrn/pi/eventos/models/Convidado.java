package Ifrn.pi.eventos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Convidado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String localE;
    private String metodoP;

    @ManyToOne
    private Evento evento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalE() { // Corrigido o nome do método
        return localE;
    }

    public void setLocalE(String localE) { // Corrigido o nome do método
        this.localE = localE;
    }

    public String getMetodoP() { // Corrigido o nome do método
        return metodoP;
    }

    public void setMetodoP(String metodoP) { // Corrigido o nome do método
        this.metodoP = metodoP;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

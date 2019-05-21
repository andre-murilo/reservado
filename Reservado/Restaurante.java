package Reservado;

import java.util.ArrayList;
import java.util.List;

public class Restaurante
{
    private int codigo;
    private String nome;
    private String endereco;
    private String telefone;
    //private Calendar horarioFuncionamento;

    private List<Mesa> mesas;
    private List<Reserva> reservas;
    private List<HistoricoReserva> historicoReservas;

    public Restaurante(int codigo, String nome)
    {
        this.codigo = codigo;
        this.nome = nome;

        this.mesas = new ArrayList<Mesa>();
        this.reservas = new ArrayList<Reserva>();
        this.historicoReservas = new ArrayList<HistoricoReserva>();
    }

    public void AddMesa(Mesa mesa)
    {
        this.mesas.add(mesa);
    }

    public void AddReserva(Reserva reserva)
    {
        this.reservas.add(reserva);
    }

    public void AddHistorioReserva(HistoricoReserva hReserva)
    {
        this.historicoReservas.add(hReserva);
    }

    public int GetCodigo()
    {
        return this.codigo;
    }

    public String GetNome()
    {
        return this.nome;
    }

    public String GetEndereco()
    {
        return this.endereco;
    }

    public String GetTelefone()
    {
        return this.telefone;
    }

    public List<Mesa> GetMesas()
    {
        return this.mesas;
    }

    public List<Reserva> GetReservas()
    {
        return this.reservas;
    }

    public List<HistoricoReserva> GetHistoricoReservas()
    {
        return this.historicoReservas;
    }
    
    public void SetEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public void SetTelefone(String telefone)
    {
        this.telefone = telefone;
    }
}
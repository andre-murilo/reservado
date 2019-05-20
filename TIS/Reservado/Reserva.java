package Reservado;

import java.util.Calendar;

public class Reserva
{
    private int codigo;
    private int codigoRestaurante;
    private int codigoCliente;
    private int codigoMesa;
   

    private Calendar dataReserva;
    private Calendar dataPaga;
    private Calendar validade;

    public Reserva(int codigo, int codigoRestaurante, int codigoCliente, int codigoMesa, Calendar dataReserva, Calendar dataPaga, Calendar validade)
    {
        this.codigo = codigo;
        this.codigoRestaurante = codigoRestaurante;
        this.codigoCliente = codigoCliente;
        this.codigoMesa = codigoMesa;
        this.dataReserva = dataReserva;
        this.dataPaga = dataPaga;
        this.validade = validade;
    }

    public Reserva(int codigo, int codigoRestaurante, int codigoCliente, int codigoMesa)
    {
        this.codigo = codigo;
        this.codigoRestaurante = codigoRestaurante;
        this.codigoCliente = codigoCliente;
        this.codigoMesa = codigoMesa;
    }

    public int GetCodigo()
    {
        return codigo;
    }

    public int GetCodigoRestaurante()
    {
        return codigoRestaurante;
    }

    public int GetCodigoCliente()
    {
        return codigoCliente;
    }

    public int GetCodigoMesa()
    {
        return codigoMesa;
    }

    public Calendar GetDataReserva()
    {
        return dataReserva;
    }

    public Calendar GetDataPaga()
    {
        return dataPaga;
    }

    public Calendar GetDataValidade()
    {
        return validade;
    }

    public void SetDataReserva(Calendar date)
    {
        this.dataReserva = date;
    }

    public void SetDataPaga(Calendar date)
    {
        this.dataPaga = date;
    }

    public void SetValidade(Calendar date)
    {
        this.validade = date;
    }

}

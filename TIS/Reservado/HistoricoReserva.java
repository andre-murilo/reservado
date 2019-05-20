package Reservado;

public class HistoricoReserva
{
    private int codCliente;
    private int codRestaurante;
    private int codReserva;
    private int status; // 0 - disponivel, 1 - pago

    public HistoricoReserva(int codCLiente, int codRestaurante, int codReserva, int status)
    {
        this.codCliente = codCLiente;
        this.codRestaurante = codRestaurante;
        this.codReserva = codReserva;
        this.status = status;
    }

    public int GetCodigoCliente()
    {
        return codCliente;
    }

    public int GetCodigoRestaurante()
    {
        return codRestaurante;
    }

    public int GetCodigoReserva()
    {
        return codReserva;
    }
}
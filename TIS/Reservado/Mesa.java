package Reservado;

public class Mesa
{
    private int codigo;
    private int codigoRestaurante;
    private int status; // 0 disponivel, 1 usado
    
    public Mesa(int codigo, int codigoRestaurante, int status)
    {
        this.codigo = codigo;
        this.codigoRestaurante = codigoRestaurante;
        this.status = status;
    }

    public int GetCodigo()
    {
        return this.codigo;
    }

    public int GetCodigoRestaurante()
    {
        return this.codigoRestaurante;
    }

    public int GetStatus()
    {
        return this.status;
    }
}
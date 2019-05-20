package Reservado;

import java.util.ArrayList;
import java.util.List;

public class Reservado
{
    private List<Restaurante> restaurantes;
    private List<Cliente> clientes;


    public Reservado()
    {
        this.restaurantes = new ArrayList<Restaurante>();
        this.clientes = new ArrayList<Cliente>();
    }

    public List<Restaurante> GetRestaurantes()
    {
        return this.restaurantes;
    }

    public void AddRestaurante(Restaurante rest)
    {
        this.restaurantes.add(rest);
    }

    public void AddCliente(Cliente cliente)
    {
        this.clientes.add(cliente);
    }
}
package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import Reservado.*;

public class Database
{
    private Reservado reservado;

    public Database(Reservado reservado)
    {
        this.reservado = reservado;
    }

    private String[] FilesFromDir(String path)
    {
        File file = new File(path);
        List<String> files = new ArrayList<String>();

        for(File entry : file.listFiles())
        {
            if(entry.isFile())
            {
                files.add(entry.getPath());
            }
        }

        String[] array = new String[files.size()];
        array = (String[])files.toArray();
        return array;
    }

    private String[] ReadAllLines(String path)
    {
        BufferedReader reader;
        List<String> lines = new ArrayList<String>();

        try
        {
            reader = new BufferedReader(new FileReader(path));

            String line = reader.readLine();
            while(line != null)
            {
                lines.add(line);
                line = reader.readLine();
            }
        }
        catch(Exception e)
        {

        }


        String[] array = new String[lines.size()];
        for(int i = 0; i < array.length; i++)
            array[i] = lines.get(i);

        return array;
    }


    public void Load()
    {
        LoadClientes();
        LoadRestaurantes();
    }

    private void LoadClientes()
    {
        String[] lines = ReadAllLines("./Data/Cliente.dat");

        for(String line : lines)
        {
            String[] splitted = line.split(":");
            
            int id = Integer.parseInt(splitted[0]);
            String nome = splitted[1];
            String telefone = splitted[2];
            int senha = Integer.parseInt(splitted[3]);

            String formated = String.format("%d|%s|%s|%d", id, nome, telefone, senha);


            Cliente cliente = new Cliente(id, nome, telefone);
            cliente.SetSenha(senha);

            reservado.AddCliente(cliente);

            System.out.println(formated);
        }
    }

    private void LoadRestaurantes()
    {
        String[] lines = ReadAllLines("./Data/Restaurante.dat");

        for(String line : lines)
        {
            String[] splitted = line.split(":");

            int id = Integer.parseInt(splitted[0]);
            String nome = splitted[1];
            String endereco = splitted[2];
            String telefone = splitted[3];

            Restaurante restaurante = new Restaurante(id, nome);
            restaurante.SetEndereco(endereco);
            restaurante.SetTelefone(telefone);

            LoadMesas(restaurante);
            LoadReservas(restaurante);
        }
    }

    private void LoadMesas(Restaurante restaurante)
    {
        String[] lines = ReadAllLines("./Data/Mesa.dat");

        for(String line : lines)
        {
            String[] splitted = line.split(":");

            int id = Integer.parseInt(splitted[0]);
            int codRestaurante = Integer.parseInt(splitted[1]);
            int status = Integer.parseInt(splitted[2]);

            Mesa mesa = new Mesa(id, codRestaurante, status);
            restaurante.AddMesa(mesa);
        }
    }

    private void LoadReservas(Restaurante restaurante)
    {
        String[] lines = ReadAllLines("./Data/Reserva.dat");

        for(String line : lines)
        {
            String[] splitted = line.split(":");

            int id = Integer.parseInt(splitted[0]);
            int codRestaurante = Integer.parseInt(splitted[1]);
            int clienteID = Integer.parseInt(splitted[2]);
            int mesaID = Integer.parseInt(splitted[3]);

            Reserva reserva = new Reserva(id, codRestaurante, clienteID, mesaID);

            restaurante.AddReserva(reserva);
        }
    }

    public void Save()
    {
        SaveClients();
        SaveRestaurants();
        
    }
    
    private void SaveClients()
    {
      File file = new File("./Data/Cliente.dat");
      try
      {
         FileWriter fileStream = new FileWriter(file, false);
         
         for(Cliente c : reservado.GetClientes())
         {
            String formated = String.format("%d:%s:%s:%d\n", c.GetCodigo(), c.GetNome(), c.GetTelefone(), c.GetSenha());
            fileStream.write(formated);
         }
         fileStream.close();
      }
      catch(Exception e)
      {
         
      }
    }
    
    private void SaveRestaurants()
    {
    	List<Mesa> mesas = new ArrayList<Mesa>();
    	List<Reserva> reservas = new ArrayList<Reserva>();
    	
    	for(Restaurante rest : reservado.GetRestaurantes())
    	{
    		mesas.addAll(rest.GetMesas());
    		reservas.addAll(rest.GetReservas());
    	}
    	
    	SaveMesas(mesas);
    	SaveReservas(reservas);
    }
    
    private void SaveMesas(List<Mesa> mesas)
    {
    	File file = new File("./Data/Mesa.dat");
    	
    	try
    	{
    		FileWriter fileStream = new FileWriter(file, false);
    		for(Mesa mesa : mesas)
        	{
    			String formated = String.format("%d:%d:%d\n", mesa.GetCodigo(), mesa.GetCodigoRestaurante(), mesa.GetStatus());
                
    			fileStream.write(formated);
        	}
    		
    		fileStream.close();
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
    
    private void SaveReservas(List<Reserva> reservas)
    {
    	File file = new File("./Data/Reserva.dat");
    	
    	try
    	{
    		FileWriter fileStream = new FileWriter(file, false);
    		for(Reserva reserva : reservas)
        	{

    			String formated = String.format("%d:%d:%d:%d\n", reserva.GetCodigo(), reserva.GetCodigoRestaurante(),
    					reserva.GetCodigoCliente(), reserva.GetCodigoMesa());
                
    			fileStream.write(formated);
        	}
    		
    		fileStream.close();
    	}
    	catch(Exception e)
    	{
    		
    	}
    }
}
#region REQUISITOS
/* 
// empresa contrato com reservado.
1º Restaurante faz o cadastro & contrato com a "Reservado";
2º Verifica As informações Do restaurante.
3º Conclui o cadastro & contrato.

// Processo do cliente no site
1º Cliente faz o cadastro na aplicação.
2º cliente escolhe um restaurante com uma data e hora.
3º Client escolhe uma mesa disponivel.
4º Cliente reserva a mesa.

obs: Caso a mesa não esteja disponivel, ele pode entrar na lista de espera.

// Processo do cliente na cancelação da mesa
1º identifica na aplicação.
2º navega até a pagina de mesas reservadas.
3º Faz o cancelamento e sistema é notificado.


// Processo do cliente no restaurante
1º Se indentifica no restaurante.
2º Locomove-se para a mesa registrada.
3º Come igual a um porco.
4º Realiza o pagamento e o sistema é notificado.


obs: Cliente pode reservar até no maximo 2 mesas por restaurante.
obs: Cancelamento não tem penalidade.
obs: Caso o cliente não compareça no restaurante com limite de 30 minutos, automaticamente a mesa é liberada.
*/
#endregion

public class Cliente
{
    public int Codigo {get; private set;}
    public string Nome {get; private set;}
    public string Telefone {get; private set;}
    public int senha {get; set;}

    public Cliente(int id, string nome, string telefone)
    {
        this.Codigo = id;
        this.Nome = nome;
        this.Telefone = telefone;
    } 
}


public class HitoricoReserva
{
    int codigoCliente;
    int codigoReserva;
    string status;
}

public class Mesa
{
    int Codigo {get; set;}
    int numeroCadeiras {get; set;}
    bool disponivel;
}

public class Reserva
{
    int codigoRestaurante;
    int codigoCliente;
    int codigoMesa;

    Date dataReserva;
    Date dataPaga;
    Date validade;
    bool pago;
}


public class Restaurante
{
    int MAX_RESERVAS_POR_CLIENTE = 2;


    string nome;
    Date horarioFuncionamento;
    string endereco;
    string telefone;

    Mesa[] mesas;
    Reserva[] reservas;
    

    public Cancelar(Reserva reserva);
    
    public void LoadMesas();
    public void LoadReservas();


    public void ReservarMesa(Cliente cliente);
    public void LiberarMesa(Clinte cliente, Mesa mesa);
    public Mesa[] PegarMesasDisponiveis();
    public Mesa[] PegarMesasReservadas();
    public Mesa[] PegarMesasDoCliente(Cliente cliente);
}


/* aplicativo - Classe principal */
public class App
{
    const int MAX_RESTAURANTES = 3;
    Restaurante[] restaurante;
    Cliente[] clientes;

    public void CadastrarRestaurante();
    public void CancelarRestaurante();
}
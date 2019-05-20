package Reservado;

public class Cliente
{
    private int codigo;
    private String nome;
    private String telefone;
    private int senha;

    public Cliente(int cod, String nome, String telefone)
    {
        this.codigo = cod;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = 0;
    }

    public Cliente(int cod, String nome)
    {
        this.codigo = cod;
        this.nome = nome;
    }

    public int GetCodigo()
    {
        return codigo;
    }

    public String GetNome()
    {
        return nome;
    }

    public String GetTelefone()
    {
        return telefone;
    }

    public int GetSenha()
    {
        return senha;
    }

    public void AlterarNome(String nome)
    {
        this.nome = nome;
    }

    public void AlterarTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public void SetSenha(int senha)
    {
        this.senha = senha;
    }






}
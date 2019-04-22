public class Cliente
	{
	    
		public int codigo;	
		public int getCodigo() 
		{
			return codigo;
		}
		
	

		public String nome;
		public String getNome() 
		{
			return nome;
		}
		private void setNome(String nome) {
			this.nome = nome;
		}

		
		

		public String telefone;
		public String getTelefone() 
		{
			return telefone;
		}
        private void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		
		

		public int senha;
		public int getSenha() 
		{
			return senha;
		}
		private void setSenha(int senha) {
			this.senha = senha;
		}
		
		
		public Cliente(int id, String nome, String telefone, int senha)
	    {
	        this.codigo = id;
	        this.nome = nome;
	        this.telefone = telefone;
	        this.senha = senha;
	    } 
	

}

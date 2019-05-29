
//começo da pagina
$('#button-reseve').click(function(){
          
    $('.banner').html(" ")
  $('.banner').addClass('animated').addClass('bounceInRight')
  //coloquei button mais tem que ser input
  $('.banner').html(`
  <form id='cadastro'>
  <div class='row'>
      <div class='col-md-6 form-group'>
          <label for='nomeCliente'>Nome</label>
          <input type='text' class='form-control' id='nomeCliente'> </div>
      <div class='col-md-6 form-group'>
          <label for='telefoneCliente'>Telefone</label>
          <input type='text' class='form-control' id='telefoneCliente'> </div>
  </div>
  <div class='row'>
      <div class='col-md-12 form-group'> </div>
  </div>
</form>
<button id='sign' onclick='reserva()' class='btn btn-primary btn-lg btn-block' value=''>Reserve Agora</button>
` )
  
  
 
})

// acao do click no botao reserve agora que modifica a pagina para aparecer a escolha dos restaurantes
// decidir fazer usando uma funçao .


function reserva(){
  

  
  $.post("ajax/reservar", {nome: $('#nomeCliente').val(), telefone: $('#telefoneCliente').val()}, function(data){
    var obj = JSON.parse(data);
    console.log(obj.nome)
    window.location.assign("/home.html")
  })

  
    

   
    }

  
 
//depois que estiver tudo implementado add esse ajax

// $('#cadastro').on('submit', function(e){
//   e.preventDefault();
//   $.ajax({
//     type: "POST",
//     cache: false,
//     url: $(this).attr('action'),
//     data: $(this).serialize() ,
//     success: function(data){
//       $('.site-cover').remove()
//       $('.site-section').find('container').html()

//     }
//   })
// })

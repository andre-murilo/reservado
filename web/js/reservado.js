// ************************* Common  *************************
var DB = {

}


function LoadDB()
{
    var data = localStorage.getItem("data");
    if(data) {
        DB = JSON.parse(data);
    }
}
LoadDB();

function SaveDB()
{
    localStorage.setItem("data", JSON.stringify(DB));
}


function initializeLogin()
{
    
}




// ************************* INDEX  *************************

function onIndexLoaded()
{
    initializeLogin();
    getStats();
}

function getStats()
{
    


    $.post("ajax/getStats", function(data) {
        var obj = JSON.parse(data);
        
        $("#total-users").text(" " + obj.clientes);
        $("#total-reservas").text(" " + obj.restaurantes);
        $("#total-restaurantes").text(" " + obj.reservas);

    }) ;
}


// ************************* RESTAURANTES  *************************

function onRestaurantesPageLoaded()
{
    initializeLogin();
    loadRestaurantes();
}


function loadRestaurantes()
{
    // ajax
    // $.post("ajax/getRestaurantes", function(data) {

    // });

}

function cadastrarRestaurante()
{
    let nome = $("#nome").val();
    let endereco = $("#endereco").val();
    let mesas = $("#mesas").val();

    console.log(nome + " " + endereco + " " + mesas);

    // ajax
    // $.post("ajax/cadastrar", {name: "Teste", endereco: "Teste2", mesas: 25}, function(data) {
    //     alert("Resposta: " + data);
    // });



    return false;
}


// ************************* RESTAURANTES  *************************

function onReservaPageLoaded()
{
    initializeLogin();
    loadMesas();

}

function loadMesas()
{
    let restauranteSelecionado = $("#restaurante-selected").val();
    let reservaData = $("#reserva-data").val();

    console.log(restauranteSelecionado + " " + reservaData);
}

function submitFilter()
{
    loadMesas();
    return false;
}



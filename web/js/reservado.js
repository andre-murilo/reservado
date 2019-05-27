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
        $("#total-reservas").text(" " + obj.reservas);
        $("#total-restaurantes").text(" " + obj.restaurantes);

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

    $.post("ajax/getRestaurantes", function(data) {
        console.log(data);

        let obj = JSON.parse(data);

        let rests = obj.restaurantes;

        let html = '';

        rests.forEach(e => {
            html += `<div class="restaurant-container"><div><a href="#" class="rest-title">${e}</a></div><img src="img/rest.jpg" alt="" srcset=""></div>`;

            console.log(e);
        });

        $("#restaurantes").html(html);

    });

}

function cadastrarRestaurante()
{
    let nome = $("#nome").val();
    let endereco = $("#endereco").val();
    let mesas = $("#mesas").val();

    console.log(nome + " " + endereco + " " + mesas);

    ajax
    $.post("ajax/cadastrar", {name: nome, endereco: endereco, mesas: mesas}, function(data) {
        alert("Resposta: " + data);
    });

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



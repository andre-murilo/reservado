class params {
    constructor(name, value){
        this.name = name;
        this.value = value;
    }
}
var params = [Cadartro]

function getParams(){
    let urlParams = new URLSearchParams(window.location.search);
    let params = [];

    for(var i = 0; i < parameters.length; i++)
    {
        if(urlParams.has(parameters[i]))
        {
            console.log(urlParams.get(parameters[i]));
            params.push(new Params(parameters[i], urlParams.get(parameters[i])));
        }
    }


    return params;
}
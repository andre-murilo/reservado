var blocks = document.getElementsByClassName("block");
for(var i = 0; i < blocks.length; i++)
{
    blocks[i].addEventListener("click", OnBlockClicked);
}

// function RemoveSelecteds()
// {
//     for(var i = 0; i < blocks.length; i++)
//     {
//         blocks[i].classList.remove("selecionado");
//     }
// }

function GetBlockByID(id)
{
    for(var i = 0; i < blocks.length; i++)
    {
        if(blocks[i].getAttribute("id") == id)
        {
            return blocks[i];
        }
    }
}

function OnBlockClicked(obj)
{
    var id = obj.target.getAttribute("id");
    var objectClicked = GetBlockByID(id);

    var selected = objectClicked.classList.contains("selecionado");
    if(selected)
    {
        objectClicked.classList.remove("selecionado");
    }
    else
    {
        objectClicked.classList.add("selecionado");
    }

    
    //RemoveSelecteds();
    
   



    console.log(id);

}


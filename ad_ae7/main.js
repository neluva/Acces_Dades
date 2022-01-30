function getGroupInfo() {
    let valor = document.getElementById('valor').value;
    axios
        .get("https://www.thecocktaildb.com/api/json/v1/1/search.php?s="+valor)
        .then(response => {
            
            document.getElementById('idDrink').value = response.data.drinks[0].idDrink;
            document.getElementById('strDrink').textContent = response.data.drinks[0].strDrink;
            document.getElementById('strCategory').textContent = response.data.drinks[0].strCategory;
            document.getElementById('strAlcoholic').textContent = response.data.drinks[0].strAlcoholic;
            document.getElementById('strInstructions').textContent = response.data.drinks[0].strInstructions;
            document.getElementById('strIngredient1').textContent = response.data.drinks[0].strIngredient1;
            document.getElementById('strIngredient2').textContent = response.data.drinks[0].strIngredient2;
        })
        .catch(error => {
            console.error(error);
        });
};
function guardar() {
    let strDrink = document.getElementById('strDrink').textContent;
    let idDrink = document.getElementById('idDrink').textContent;
    let strCategory = document.getElementById('strCategory').textContent;
    let strAlcoholic = document.getElementById('strAlcoholic').textContent;
    let strInstructions = document.getElementById('strInstructions').textContent;
    let strIngredient1 = document.getElementById('strIngredient1').textContent;
    let strIngredient2 = document.getElementById('strIngredient2').textContent;



    $.ajax({
        type: "POST",
        url: "./index.php",
        data: {
            strDrink: strDrink, idDrink: idDrink, strCategory: strCategory,
            strAlcoholic: strAlcoholic, strInstructions: strInstructions, strIngredient1: strIngredient1, strIngredient2: strIngredient2
        },
        success: function (response) {
            alert(response);
        },
        error: function () {
            alert("Error");
        }
    });
}
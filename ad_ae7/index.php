<?php
if (isset($_POST["valor"])) {
    $Drink = $_POST["strDrink"];
    $Category= $_POST["strCategory"];
    $servidor = "localhost";
    $usuario = "root";
    $password = "";
    $dbname = "aev7_ad";
    $conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
    if (!$conexion) {
        echo "Error en la conexion a MySQL: " . mysqli_connect_error();
        exit();
    }
    $sql = "INSERT INTO Drinks(strDrink,strCategory) VALUES ('" . $Drink . "','" . $Category . "')";
    if (mysqli_query($conexion, $sql)) {
        echo "Registro insertado correctamente.";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
    }
}
?>
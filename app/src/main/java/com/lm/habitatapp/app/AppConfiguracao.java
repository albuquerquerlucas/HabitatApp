package com.lm.habitatapp.app;

/**
 * Created by Mrluke on 20/09/2016.
 */

public class AppConfiguracao {

    public static String ipCasa = "192.168.25.13:80";
    public static String ipFa7 = "10.54.108.183:80";

    public static String URL_LOGIN = "http://" + ipFa7 + "/habitatpetWs/serverside/loginUsuario.php";
    public static String URL_CADASTRO = "http://" + ipFa7 + "/habitatpetWs/serverside/cadastroUsuarios.php";
    public static String URL_PEIXES = "http://" + ipFa7 + "/habitatpetWs/serverside/exibirPeixesJson.php";
    public static String URL_PLANTAS = "http://" + ipFa7 + "/plantasWs/exibirPlantasJson.php";
    public static String URL_PRODUTOS = "http://" + ipFa7 + "/produtosWs/exibirProdutosJson.php";
}

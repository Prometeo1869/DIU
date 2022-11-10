package ch.makery.address;

import Modelo.ExcepcionMoneda;
import ch.makery.address.model.MonedaModelo;
import javafx.application.Application;
import javafx.stage.Stage;
import Modelo.repository.impl.MonedaRepositoryImpl;
import Modelo.MonedaVO;

import java.io.IOException;
import java.util.Iterator;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws ExcepcionMoneda {
        MonedaRepositoryImpl rep = new MonedaRepositoryImpl();

        MonedaModelo modelo = new MonedaModelo();
        modelo.setRepos(rep);
        Iterator iteradorMonedas = modelo.obtenerListaMonedas().iterator();

        do{
            System.out.println("$$$$$$$$$$$$$ "+((MonedaVO) iteradorMonedas.next()).getNombre());
        }while(iteradorMonedas.hasNext());


    }


    public static void main(String[] args) {
        launch(args);
    }
}

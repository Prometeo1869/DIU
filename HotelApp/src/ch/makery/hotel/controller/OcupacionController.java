package ch.makery.hotel.controller;

import ch.makery.hotel.model.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class OcupacionController {

    @FXML
    private BarChart<String, Integer> grafica;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> mesesNombre = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Get an array with the month names.
        String[] meses = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        mesesNombre.addAll(Arrays.asList(meses));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(mesesNombre);
    }

    public void setReservaData(List<Reserva> listaReservasTotales) {
        // Count the number of reservas in a specific month.
        int[] contadorMes = new int[12];
        for (Reserva r : listaReservasTotales) {
            int mes = r.getFechaLlegada().getMonthValue() - 1;
            contadorMes[mes]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < contadorMes.length; i++) {
            series.getData().add(new XYChart.Data<>(mesesNombre.get(i), (int) (contadorMes[i]*100/120)));
        }

        grafica.getData().add(series);
    }
}

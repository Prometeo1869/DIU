

    @FXML
    private Label numMonedas;
    private IntegerProperty numero = new SimpleIntegerProperty(0);
    MonedaModelo modelo;

    @FXML
    private void initialize() throws ExcepcionMoneda {

    }


    public void numeroMonedas() {

        numero.addListener((observable, oldValue, newValue) -> numMonedas.setText("" + newValue));
        numero.bindBidirectional(modelo.getNumeroMonedas());
    }

    public void setModelo(MonedaModelo modelo) {
        this.modelo = modelo;
    }

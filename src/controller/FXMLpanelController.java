package controller;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.APIConnector;
import service.UtilTemperatura;
/**
 * Controlador del panel principal que contiene el convertidor de divisas y temperatura.
 * @author Emmanuel Silano
 */
public class FXMLpanelController implements Initializable {

    String url = "https://api.exchangerate.host/";
    String simbol = "symbols";
    APIConnector apiConnector = new APIConnector(url + simbol);
    APIConnector apiConnectorConverter = new APIConnector(url);
    Map<String, String> mapList;
    List<String> listTemp = UtilTemperatura.getListTemperatura();
    List<String> listTempNom = UtilTemperatura.getListTemperaturaNom();

    @FXML
    private HBox panelInfo;

    @FXML
    private VBox panelDivisas;

    @FXML
    private TextField textDivisaOne;

    @FXML
    private ComboBox<?> comboDivisaOne;

    @FXML
    private ComboBox<?> comboDivisaSec;

    @FXML
    private Label divisaLb1;

    @FXML
    private Label divisaLb2;

    @FXML
    private Label divisaLb4;

    @FXML
    private Label divisaLb5;

    @FXML
    private VBox panelTemp;

    @FXML
    private TextField textTempOne;

    @FXML
    private ComboBox<?> comboTempOne;

    @FXML
    private ComboBox<?> comboTempSec;

    @FXML
    private Label tempLb1;

    @FXML
    private Label tempLb2;

    @FXML
    private Label tempLb4;

    @FXML
    private Label tempLb5;

    @FXML
    private Label navPanelLabel;

    @FXML
    private Button btnTemp;
    /**
     * Evento click del boton que lleva a la ventana del convertidor de divisas
     * @param event 
     */
    @FXML
    public void actionBtnNavD(ActionEvent event) {
        switchContentPanel(panelTemp, panelDivisas, panelInfo, false, true, false);
        toggleTitlePanel("Convertidor de divisas");
    }
    /**
     * Evento click del boton que lleva a la ventana de información.
     * @param event 
     */
    @FXML
    public void actionBtnNavInf(ActionEvent event) {
        switchContentPanel(panelTemp, panelDivisas, panelInfo, false, false, true);
        toggleTitlePanel("Convertidor Alura - Challenge #1 Back G5");

    }
    /**
     * Evento click que lleva a la ventana del convertidor de temperatura.
     * @param event 
     */
    @FXML
    public void actionBtnNavT(ActionEvent event) {
        switchContentPanel(panelDivisas, panelTemp, panelInfo, false, true, false);
        toggleTitlePanel("Convertidor de temperartura");

    }
    
    //método conversor de temperatura
    private void converterTemperatura() {
        String textOneTemp = getTextTempOne().getText();
        boolean g = isNumeric(textOneTemp);
        if (g) {
            getTempLb1().setText(textOneTemp);
            Double grado = Double.valueOf(textOneTemp);
            String a = getTempLb2().getText();
            String b = getTempLb5().getText();
            if (a.equals("°C") && (b.equals("°K"))) {
                String result = UtilTemperatura.celsiusToKelvin(grado);
                getTempLb4().setText(result);
            }
            if (a.equals("°C") && (b.equals("°F"))) {
                String result = UtilTemperatura.celsiusToFahrenheit(grado);
                getTempLb4().setText(result);
            }
            if (a.equals("°F") && (b.equals("°K"))) {
                String result = UtilTemperatura.fahrenheitToKelvin(grado);
                getTempLb4().setText(result);
            }
            if (a.equals("°F") && (b.equals("°C"))) {
                String result = UtilTemperatura.fahrenheitToCelsius(grado);
                getTempLb4().setText(result);
            }
            if (a.equals("°K") && (b.equals("°C"))) {
                String result = UtilTemperatura.kelvinToCelsius(grado);
                getTempLb4().setText(result);
            }
            if (a.equals("°K") && (b.equals("°F"))) {
                String result = UtilTemperatura.kelvinToFahrenheit(grado);
                getTempLb4().setText(result);
            }
            if (a.equals(b)) {
                getTempLb4().setText(getTextTempOne().getText());
            }
        } else {
            showAlertErro();
        }
    }
    
    //método que permite que el elemento seleccionado combobox de temperatura sea igual al texto del label resultado
    private void selectClickComboboxTemp(ComboBox combobox, Label label) {
        Integer intText = combobox.getSelectionModel().getSelectedIndex();
        for (int i = 0; i < listTempNom.size(); i++) {
            if (intText == i) {
                label.setText(listTempNom.get(i));
                break;
            }
        }
    }
    /**
     * Evento key que permite realizar la conversion de temperatura al oprimir ENTER.
     * @param event recibe valor de la tecla ENTER
     */
    @FXML
    public void tempEventKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (!(getTextTempOne().getText().isEmpty())) {
                converterTemperatura();
            } else {
                emptyResult(getTempLb1(), getTempLb4());
                showAlertInfo();
            }

        }

    }
    /**
     * Evento click que permite realizar la conversion de temperatura al seleccionar un 
     * elemento del primer combobox temperatura.
     * @param event 
     */
    @FXML
    public void comboOneTempEvent(ActionEvent event) {
        selectClickComboboxTemp(getComboTempOne(), getTempLb2());
        if (!(getTextTempOne().getText().isEmpty())) {
            
            converterTemperatura();
        } else {
            emptyResult(getTempLb1(), getTempLb4());
            showAlertInfo();
        }
    }
    /**
     * Evento click que permite realizar la conversion de temperatura al seleccionar
     * un elemento del segundo combobox temperatura.
     * @param event 
     */
    @FXML
    public void comboSecTempEvent(ActionEvent event) {
        selectClickComboboxTemp(getComboTempSec(), getTempLb5());
        if (!(getTextTempOne().getText().isEmpty())) {
            
            converterTemperatura();
        } else {
            emptyResult(getTempLb1(), getTempLb4());
            showAlertInfo();
        }

    }

//--------------------------------DIVISAS--------------------------------------
    //método que conversor de divisas
    private void converterDivisa() {
        String textOne = getTextDivisaOne().getText();
        boolean b = isNumeric(textOne);
        if (b) {
            getDivisaLb1().setText(textOne);
            Double amount = Double.parseDouble(textOne);
            String textRs, txD1, txD2;
            txD1 = getDivisaLb2().getText();
            txD2 = getDivisaLb5().getText();

            textRs = apiConnectorConverter.getResultConverter(txD1, txD2, amount);
            getDivisaLb4().setText(textRs);
        } else {
            showAlertErro();
        }
    }

    //método de interpolación de un elemento seleccionado del combobox divisas y el texto label de resultado
    private void setItemLabelDivisaResult(Map<String, String> mapList, ComboBox combobox, Label lb) {
        mapList = apiConnector.getMapDivisa();
        String text = (combobox.getSelectionModel().getSelectedItem()).toString();
            for (Map.Entry<String, String> m : mapList.entrySet()) {
                if (m.toString().equals(text)) {
                    lb.setText(m.getKey());
                    break;
                }
            }
       
    }
    /**
     * Evento Key que realiza la conversion de divisas a oprimir ENTER.
     * @param event Valor de la tecla ENTER.
     */
    @FXML
    public void eventKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (!(getTextDivisaOne().getText().isEmpty())) {
                converterDivisa();
            } else {
                emptyResult(getDivisaLb1(), getDivisaLb4());
                showAlertInfo();
            }

        }
    }
    /**
     * Evento Click que realiza la conversión de divisas al seleccionar un elemento del
     * primer combobox divisas.
     * @param event 
     */
    @FXML
    private void comboEventDivisaOne(ActionEvent event) {
        setItemLabelDivisaResult(mapList, getComboDivisaOne(), getDivisaLb2());
        //verificamos que la casilla de la cantidad no este vacia y que sea un numero
        //para convertir la cantidad:
        if (!(getTextDivisaOne().getText().isEmpty())) {
            converterDivisa();
        } else {
            emptyResult(getDivisaLb1(), getDivisaLb4());
            showAlertInfo();
        }

    }
    /**
     * Evento Click que realiza la conversión de divisas al seleccionar un elemento del
     * segundo combobox divisas.
     * @param event 
     */
    @FXML
    private void comboEventDivisaSec(ActionEvent event) {
        setItemLabelDivisaResult(mapList, getComboDivisaSec(), getDivisaLb5());
        if (!(getTextDivisaOne().getText().isEmpty())) {
            converterDivisa();
        } else {
            emptyResult(getDivisaLb1(), getDivisaLb4());
            showAlertInfo();
        }
    }

    /**
     * método que cambia el titulo de un panel
     */
    public void toggleTitlePanel(String nameLabel) {
        this.navPanelLabel.setText(nameLabel);
    }

    /**
     * Método sobrecargado que cambia la visibilidad de dos paneles VBox y uno HBox. Recibe
     * true para activar y false para desactivar.
     * @param panelA VBox Panel uno.
     * @param panelB VBox Panel dos.
     * @param panelC HBox Panel tres.
     * @param a Valor booleano  true o false para el panelA.
     * @param b Valor booleano true o false para panelB.
     * @param c valor booleano true o false para panelC.
     */
    public void switchContentPanel(VBox panelA, VBox panelB, HBox panelC, boolean a, boolean b, boolean c) {
        panelA.setVisible(a);
        panelB.setVisible(b);
        panelC.setVisible(c);
    }
    /**
     * Cambia la visibilidad de un panel del tipo VBox.
     * @param panelZ Panel VBox.
     * @param z Valor true para activar y false para desactivar.
     */
    public void switchContentPanel(VBox panelZ, boolean z) {
        panelZ.setVisible(z);
    }

    //--------------------GETTERS------------------------------
    /**
     * 
     * @return Retorna el panel o ventana del convertidor de divisas.
     */
    public VBox getPanelDivisas() {
        return panelDivisas;
    }
    /**
     * 
     * @return Retorna el panel ventana de información.
     */
    public HBox getPanelInfo() {
        return panelInfo;
    }
    /**
     * 
     * @return Retorna el TextField del panel del convertidor de divisas.
     */
    public TextField getTextDivisaOne() {
        return textDivisaOne;
    }
    /**
     * 
     * @return Retorna el primer combobox del panel del convertidor de divisas.
     */
    public ComboBox<?> getComboDivisaOne() {
        return comboDivisaOne;
    }
    /**
     * 
     * @return Retorna el segundo combobox del panel de convertidor de divisas.
     */
    public ComboBox<?> getComboDivisaSec() {
        return comboDivisaSec;
    }
    
    public Label getDivisaLb1() {
        return divisaLb1;
    }

    public Label getDivisaLb2() {
        return divisaLb2;
    }

    public Label getDivisaLb4() {
        return divisaLb4;
    }

    public Label getDivisaLb5() {
        return divisaLb5;
    }

    public Label getNavPanelLabel() {
        return navPanelLabel;
    }

    public Button getBtnTemp() {
        return btnTemp;
    }
    /**
     * 
     * @return Retorna el panel del convertidor de temperatura.
     */
    public VBox getPanelTemp() {
        return panelTemp;
    }
    /**
     * 
     * @return Retorna el TextField del panel del conversor de temperatura.
     */
    public TextField getTextTempOne() {
        return textTempOne;
    }
    /**
     * 
     * @return Retorna el primer combobox del panel del conversor de temperatura.
     */
    public ComboBox<?> getComboTempOne() {
        return comboTempOne;
    }
    /**
     * 
     * @return Retorna el segundo combobox del conversor de temperatura.
     */
    public ComboBox<?> getComboTempSec() {
        return comboTempSec;
    }

    public Label getTempLb1() {
        return tempLb1;
    }

    public Label getTempLb2() {
        return tempLb2;
    }

    public Label getTempLb4() {
        return tempLb4;
    }

    public Label getTempLb5() {
        return tempLb5;
    }

    /**
     * Método estatico que verifica si el valor es un número.
     * @param s Valor del tipo String.
     * @return Retorna true si es un numero.
     */
    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    //método para anular el texto o cambiarlo por "guiones" del resultado
    private void emptyResult(Label lb1, Label lb2) {
        String strEmpty = "--";
        lb1.setText(strEmpty);
        lb2.setText(strEmpty);
       
    }

    //Método que muestra una alerta de error de que el campo se le debe ingresar numeros
    @FXML
    private void showAlertErro() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error al ingresar datos");
        alert.setContentText("Por favor, tiene que ingresar solo numeros.");
        alert.showAndWait();
    }
    //método que muestra una alerta informativa de que el campo de texto esta vacío
    @FXML
    private void showAlertInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Importante");
        alert.setContentText("¡Hey! El campo está vacío.");
        alert.showAndWait();
    }

    /**
     * Método que se ejecuta a inicializar el Scene principal que contiene el convertidor
     * de divisas y de temperatura.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* 
        =======================PANEL TEMPERATURA==================================
         */
        //implementa una lista a los combobox's y se predefine el item al iniciar el panel
        getComboTempOne().getItems().addAll((Collection) listTemp);
        getComboTempOne().getSelectionModel().select(0);

        getComboTempSec().getItems().addAll((Collection) listTemp);
        getComboTempSec().getSelectionModel().select(1);
        selectClickComboboxTemp(getComboTempOne(), getTempLb2());
        selectClickComboboxTemp(getComboTempSec(), getTempLb5());

        //valida y obtiene el valor del campo de texto
        if (!(getTextTempOne().getText().isEmpty())) {
            converterTemperatura();
        } else {
            showAlertInfo();
        }

        /*======================PANEL DIVISAS====================================*/
        //implementa la lista en el combobox y preselecionamos un elemento
        mapList = apiConnector.getMapDivisa();

        getComboDivisaOne().getItems().addAll((Collection) mapList.entrySet());
        getComboDivisaOne().getSelectionModel().select(65);
        getComboDivisaSec().getItems().addAll((Collection) mapList.entrySet());
        getComboDivisaSec().getSelectionModel().select(67);

        //toma de valor del item preseleccionado en el combobox
        String strOne = String.valueOf(getComboDivisaOne().getSelectionModel().getSelectedItem());
        String strSec = String.valueOf(getComboDivisaSec().getSelectionModel().getSelectedItem());

        for (Map.Entry<String, String> m : mapList.entrySet()) {
            if (m.toString().equals(strOne)) {
                getDivisaLb2().setText(m.getKey());
                break;
            }
        }
        for (Map.Entry<String, String> m : mapList.entrySet()) {
            if (m.toString().equals(strSec)) {
                getDivisaLb5().setText(m.getKey());
                break;
            }
        }

        //valida y contiene valor del campo de texto
        if (!(getTextDivisaOne().getText().isEmpty())) {
            converterDivisa();
        } else {
            showAlertInfo();
        }

    }
}

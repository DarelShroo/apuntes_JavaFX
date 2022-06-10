package dad.preparacionexamendad;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnCambiosScene;

    @FXML
    private Button btnActualizar;

    @FXML
    private RadioButton rdBtnGato;

    @FXML
    private RadioButton rdBtnPerro;

    @FXML
    private DatePicker myDatePicker;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colNombre2;

    @FXML
    private TableColumn<?, ?> colTel2;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private ComboBox<String> myCombobox;

    @FXML
    private Label myLabel;

    @FXML
    private ListView<String> myList;

    @FXML
    private TableView<Persona> myTableView;

    @FXML
    private TextField myTextField;

    @FXML
    private TableView<Persona> tblPersonas;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private BorderPane view;

    @FXML
    private Button btnCambiarScena;

    private Scene scene;

    @FXML
    private StackPane myStackPaneImage;


    @FXML
    private ImageView myImageView;


    @FXML
    void onActionCambiarScena(ActionEvent event) {

    }


    @FXML
    void onActionAbrir(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("."));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            myList.getItems().add(selectedFile.getAbsolutePath());
        }
    }

    //Aquí abrimos la ventana modal
    @FXML
    void modalityWindow(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/scene1.fxml"));

        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onActionBorrarPersona(ActionEvent event) {
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if (p == null) {
            //Llamamos a un Alert en caso de que ya exista la persona
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {
            this.personas.remove(p);
            this.tblPersonas.refresh();
        }
    }

    @FXML
    void onActionActualizarPersona(ActionEvent event) {
        //Cogemos los datos de la persona desde el TableView
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if (p == null) {
            //Llamamos a un Alert en caso de que ya exista la persona
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {
            try {
                StringProperty nombre = new SimpleStringProperty(this.txtNombre.getText());
                IntegerProperty telefono = new SimpleIntegerProperty(Integer.parseInt(this.txtTelefono.getText()));

                Persona aux = new Persona(nombre, telefono);

                if (!this.personas.contains(aux)) {
                    p.setNombre(nombre.getValue());
                    p.setTelefono(telefono.getValue());
                    this.tblPersonas.refresh();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Persona modificada");
                    alert.showAndWait();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La persona ya existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }

    @FXML
    void onClickSeleccionarPersona(MouseEvent event) {
        //Cogemos los datos de la persona desde el TableView
        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if (p != null) {
            this.txtNombre.setText(p.getNombre());
            this.txtTelefono.setText(String.valueOf(p.getTelefono()));
        }
    }

    @FXML
    void onActionAgregarPersona(ActionEvent event) {
        StringProperty nombre = new SimpleStringProperty(this.txtNombre.getText());
        SimpleIntegerProperty telefono = new SimpleIntegerProperty(Integer.parseInt(this.txtTelefono.getText()));

        Persona p = new Persona(nombre, telefono);

        //Controlamos que no exista esa persona
        if (!personas.contains(p)) {
            this.personas.add(p);
            this.tblPersonas.setItems(personas);
        } else {
            //Llamamos a un Alert en caso de que ya exista la persona
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }

    //ObservableList de la Clase Personas para rellenar el TableView
    ObservableList<Persona> personas;
    ObservableList<Persona> personas2;

    //Datos de relleno para el ChoiceBox
    private String[] comida = {"Manzana", "lechuga", "Monster"};

    public RootController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/responsiveDesign.fxml"));
        loader.setController(this);
        loader.load();
    }

    public BorderPane getView() {
        return view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Rellenando el ChoiceBox
        myChoiceBox.getItems().addAll(comida);
        //Obteniendo la Acción del choiceBox
        myChoiceBox.setOnAction(this::getComida);

        //Rellenando el combobox
        rellenandoCombobox();
        //Obteniendo la Acción del combobox
        myCombobox.setOnAction(this::comboboxEvent);

        //Agrupando radiobuttons
        radiButtonGrupo();

        //Rellenando el TableView y sus columnas
        personas = FXCollections.observableArrayList();

        //Enlazamos los datos de la clase Persona con las columnas del TableView
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        //Enlazamos los datos de la clase Persona con las columnas del TableView
        this.colNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colTel2.setCellValueFactory(new PropertyValueFactory<>("telefono"));


        //TRABAJANDO CON LISTAS
       ObservableList<String> nombres = FXCollections.observableArrayList();
        try {
            crearPersonas();
            for(Persona nombrePersona: personas2 ){
                nombres.add(nombrePersona.getNombre());
            }
        }catch (NullPointerException ignored){}
       this.myList.setItems(nombres);


        //BINDEOS
       // myTextField.textProperty().bind(myLabel.textProperty());
        myTextField.textProperty().bindBidirectional(myLabel.textProperty());


        myDatePicker.setOnAction(this::getDataPicker);
    }

    private void getDataPicker(ActionEvent event) {
        ObjectProperty<LocalDate> clientDate = new SimpleObjectProperty(myDatePicker.getValue());
        System.out.println(clientDate.get());
    }

    private void comboboxEvent(ActionEvent event) {
        //Modificamos los datos del label con el valor del combobox
        Object evnt = event.getSource();

        if(evnt.equals(myCombobox)){
            String sexo = myCombobox.getSelectionModel().getSelectedItem();
            myLabel.setText(sexo);
        }
    }

    //Obteniendo la acción del ChoiceBox
    public void getComida(ActionEvent event) {
        String miComida = myChoiceBox.getValue();
        myLabel.setText(miComida);
    }

    //Rellenando un Combobox
    private void rellenandoCombobox(){
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, new String[]{"Masculino", "femenino"});

        myCombobox.getItems().add("Extraterrestre");

        myCombobox.getItems().addAll(list);
    }

    //Grupo de radioButtons
    private void radiButtonGrupo(){
        ToggleGroup tg = new ToggleGroup();
        this.rdBtnGato.setToggleGroup(tg);
        this.rdBtnPerro.setToggleGroup(tg);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton r = (RadioButton) newValue;

                myLabel.setText(r.getText());
            }
        });
    }


    private void crearPersonas(){
        Persona p1 = new Persona(new SimpleStringProperty("Maribel"), new SimpleIntegerProperty(23423));
        Persona p2 = new Persona(new SimpleStringProperty("Juana"), new SimpleIntegerProperty(54435));
        Persona p3 = new Persona(new SimpleStringProperty("Julieta"), new SimpleIntegerProperty(7456));
        Persona p4 = new Persona(new SimpleStringProperty("Rocio"), new SimpleIntegerProperty(87567));

        personas2 = FXCollections.observableArrayList();

        personas2.addAll(p1,p2,p3,p4);

        myTableView.setItems(personas2);
    }
}
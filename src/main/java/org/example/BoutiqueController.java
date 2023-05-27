package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.entities.Boutique;
import org.example.services.BoutiqueService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BoutiqueController implements Initializable {
    private BoutiqueService boutiqueService = new BoutiqueService();
    @FXML
    private TextField TAdr;

    @FXML
    private TextField TTel;
    @FXML
    private TextField TNbVend;

    @FXML
    private TextField TNom;
    @FXML
    private TextField TDescription;

    @FXML
    private TableView<Boutique> Table;

    @FXML
    private TableColumn<Boutique, Integer> colId;

    @FXML
    private TableColumn<Boutique, String> colAdr;

    @FXML
    private TableColumn<Boutique, Integer> colNbVend;

    @FXML
    private TableColumn<Boutique, String> colTel;

    @FXML
    private TableColumn<Boutique, String> colDesc;

    @FXML
    private TableColumn<Boutique, String> colNom;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnExportExcel;

    @FXML
    private Button btnImportExcel;

    @FXML
    void clearField(ActionEvent event) {
        clear();
    }

    @FXML
    void deleteBoutique(ActionEvent event) {
        Boutique boutique = boutiqueService.findByID(id);
        boutiqueService.remove(boutique.getId());
        showBoutiques();
        clear();
    }

    @FXML
    void saveBoutique(ActionEvent event) {
        Boutique boutique = new Boutique();
        boutique.setnom(TNom.getText());
        boutique.setadress(TAdr.getText());
        boutique.setnbvend(Integer.parseInt(TNbVend.getText()));
        boutique.settel(TTel.getText());
        boutique.setdescription(TDescription.getText());
        boutiqueService.save(boutique);
        showBoutiques();
        clear();
    }

    @FXML
    void updateBoutique(ActionEvent event) {
        Boutique boutique = boutiqueService.findByID(id);
        boutique.setnom(TNom.getText());
        boutique.setadress(TAdr.getText());
        boutique.setnbvend(Integer.parseInt(TNbVend.getText()));
        boutique.settel(TTel.getText());
        boutique.setdescription(TDescription.getText());
        boutiqueService.update(boutique);
        showBoutiques();
        clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBoutiques();
    }

    public ObservableList<Boutique> getBoutiques(){
        ObservableList<Boutique> list = FXCollections.observableArrayList();;
        for (Boutique boutique : boutiqueService.findAll())
            list.add(boutique);
        return list;
    }

    public void showBoutiques(){
        Table.setItems(getBoutiques());
        colId.setCellValueFactory(new PropertyValueFactory<Boutique, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colAdr.setCellValueFactory(new PropertyValueFactory<Boutique, String>("adress"));
        colNbVend.setCellValueFactory(new PropertyValueFactory<Boutique, Integer>("nbvend"));
        colTel.setCellValueFactory(new PropertyValueFactory<Boutique, String>("tel"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    int id=0;
    @FXML
    void getData(MouseEvent event) {
        Boutique boutique = Table.getSelectionModel().getSelectedItem();
        id = boutique.getId();
        TNom.setText(boutique.getnom());
        TAdr.setText(boutique.getadress());
        TNbVend.setText(boutique.getnbvend()+"");
        TTel.setText(boutique.gettel());
        TDescription.setText(boutique.getdescription());
        btnSave.setDisable(true);
    }

    void clear(){
        TNom.setText(null);
        TAdr.setText(null);
        TNbVend.setText(null);
        TTel.setText(null);
        TDescription.setText(null);
        btnSave.setDisable(false);
    }

    @FXML
    void ExportToExcel(ActionEvent event) throws Exception {
        boutiqueService.writeXLSX("src/main/resources/BoutiqueList.xlsx");
    }

    FileChooser fileChooser = new FileChooser();
    @FXML
    void ImportFromExcel(ActionEvent event) {
        File file = fileChooser.showOpenDialog(new Stage());
        boutiqueService.readXLSX(file.getAbsolutePath());
        showBoutiques();
    }

}

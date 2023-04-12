package pkg72220533_tts_netbeans;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    Text txtJudul = new Text("Data Pasien");
    Label lblKodePasien = new Label("Kode Pasien");
    Label lblNama = new Label("Nama Pasien");
    Label lblAlamat = new Label("Alamat");
    Label lblKeluhan = new Label("Keluhan");
    Label lblSemua = new Label("Daftar Pasien :");
    Label lblUrut = new Label("Urut berdasarkan");
    Label lblOrder = new Label("Jenis Urutan");
    TextField fieldKodePasien = new TextField();
    TextField fieldNama = new TextField();
    TextField fieldAlamat = new TextField();
    TextField fieldKeluhan = new TextField();
    TextArea txtareaDaftar = new TextArea();
    Button btnTambah = new Button("Tambah");
    Button btnProcess = new Button("Proses Bubble Sort");
    Button btnDelete = new Button("Hapus");
    Button btnClose = new Button("Tutup");
    ComboBox cbUrut = new ComboBox();
    ComboBox cbOrder = new ComboBox();
    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    HBox hb3 = new HBox();

    public static void errorDisplay(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("An error has occured");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void cleartextfield() {
        fieldKodePasien.clear();
        fieldAlamat.clear();
        fieldNama.clear();
        fieldKeluhan.clear();
    }

    public boolean cekField() {
        boolean cek = false;
        if (!fieldKodePasien.getText().equalsIgnoreCase("")) {
            if (!fieldNama.getText().equalsIgnoreCase("")) {
                if (!fieldAlamat.getText().equalsIgnoreCase("")) {
                    if (!fieldKeluhan.getText().equalsIgnoreCase("")) {
                        cek = true;
                    }
                }
            }
        }
        return cek;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        txtJudul.setFont(Font.font("Arial", 28));
        hb1.getChildren().addAll(btnDelete, btnTambah);
        hb1.setAlignment(Pos.BASELINE_RIGHT);
        hb1.setSpacing(10);
        hb2.getChildren().add(btnClose);
        hb2.setAlignment(Pos.BOTTOM_RIGHT);
        hb3.getChildren().addAll(cbOrder, btnProcess);
        hb3.setSpacing(5);
        hb3.setMinWidth(505);
        fieldKodePasien.setMaxWidth(80);
        txtareaDaftar.setMinHeight(300);
        txtareaDaftar.setFont(Font.font("Monospace", 12));
        cbUrut.getItems().addAll("Kode Pasien", "Nama Pasien", "Alamat", "Keluhan");
        cbUrut.setValue("Kode Pasien");
        cbOrder.getItems().addAll("Ascending", "Descending");
        cbOrder.setValue("Ascending");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(3);
        grid.setHgap(10);
        grid.setGridLinesVisible(false);
        grid.add(txtJudul, 0, 0, 2, 1);
        grid.add(lblKodePasien, 0, 1);
        grid.add(fieldKodePasien, 1, 1);
        grid.add(lblNama, 0, 2);
        grid.add(fieldNama, 1, 2, 2, 1);
        grid.add(lblAlamat, 0, 3);
        grid.add(fieldAlamat, 1, 3, 2, 1);
        grid.add(lblKeluhan, 0, 4);
        grid.add(fieldKeluhan, 1, 4, 2, 1);
        grid.add(hb1, 2, 5);
        grid.add(lblUrut, 0, 6);
        grid.add(cbUrut, 1, 6);
        grid.add(lblOrder, 0, 7);
        grid.add(hb3, 1, 7);
        grid.add(lblSemua, 0, 8);
        grid.add(txtareaDaftar, 0, 9, 3, 1);
        grid.add(hb2, 0, 10, 3, 1);

        DaftarPasien list = new DaftarPasien(1);

        txtareaDaftar.setText(list.getAll());
        btnTambah.setOnAction(new EventHandler<ActionEvent>() {
            String addNama, addKodePasien, addAlamat, addKeluhan;
            @Override
            public void handle(ActionEvent e) {
                if (cekField()) {
                    addNama = fieldNama.getText();
                    addKodePasien = fieldKodePasien.getText();
                    addAlamat = fieldAlamat.getText();
                    addKeluhan = fieldKeluhan.getText();
                    list.tambah(addKodePasien, addNama, addAlamat, addKeluhan);
                    if (list.getCekKodePasien()) {
                        if (list.getCekString()) {
                            txtareaDaftar.setText(list.getAll());
                            cleartextfield();
                            fieldKodePasien.requestFocus();
                        } else {
                            cleartextfield();
                            list.deleteData();
                            errorDisplay("Nama yang anda masukkan salah");
                            fieldKodePasien.requestFocus();
                        }
                    } else {
                        cleartextfield();
                        list.deleteData();
                        errorDisplay("Kode Pasien yang anda masukkan salah");
                        fieldKodePasien.requestFocus();
                    }
                    try {
                        list.saveToFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    errorDisplay("Input masih ada yang kosong!");
                    if (fieldKodePasien.getText().equalsIgnoreCase("")) {
                        fieldKodePasien.requestFocus();
                    }else if (fieldNama.getText().equalsIgnoreCase("")) {
                        fieldNama.requestFocus();
                    }else if (fieldAlamat.getText().equalsIgnoreCase("")) {
                        fieldAlamat.requestFocus();
                    }else if (fieldKeluhan.getText().equalsIgnoreCase("")) {
                        fieldKeluhan.requestFocus();
                    }
                }
            }
        });

        btnProcess.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (cbUrut.getValue().equals("Kode Pasien")) {
                    if (cbOrder.getValue().equals("Ascending")) {
                        list.bubbleKodePasien("Ascending");
                        txtareaDaftar.setText(list.getAll());
                    } else {
                        list.bubbleKodePasien("Descending");
                        txtareaDaftar.setText(list.getAll());
                    }
                } else if (cbUrut.getValue().equals("Nama Pasiens")) {
                    if (cbOrder.getValue().equals("Ascending")) {
                        list.bubbleNama("Ascending");
                        txtareaDaftar.setText(list.getAll());
                    } else {
                        list.bubbleNama("Descending");
                        txtareaDaftar.setText(list.getAll());
                    }
                } else if (cbUrut.getValue().equals("Alamat")) {
                    if (cbOrder.getValue().equals("Ascending")) {
                        list.bubbleAlamat("Ascending");
                        txtareaDaftar.setText(list.getAll());
                    } else {
                        list.bubbleAlamat("Descending");
                        txtareaDaftar.setText(list.getAll());
                    }
                } else if (cbUrut.getValue().equals("Keluhan")) {
                    if (cbOrder.getValue().equals("Ascending")) {
                        list.bubbleKeluhan("Ascending");
                        txtareaDaftar.setText(list.getAll());
                    } else {
                        list.bubbleKeluhan("Descending");
                        txtareaDaftar.setText(list.getAll());
                    }
                }
            }
        });

        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                list.deleteData();
                txtareaDaftar.setText(list.getAll());
                fieldKodePasien.requestFocus();
                try {
                    list.saveToFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        // grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Data Pasien");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
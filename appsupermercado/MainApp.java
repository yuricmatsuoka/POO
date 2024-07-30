import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainApp extends Application {
    private TableView<Produto> tableView;
    private ObservableList<Produto> productList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Aplicativo de Supermercado");

        // Configurar TableView
        tableView = new TableView<>();
        TableColumn<Produto, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TableColumn<Produto, String> nomeColumn = new TableColumn<>("Nome");
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        TableColumn<Produto, Double> precoColumn = new TableColumn<>("Preço");
        precoColumn.setCellValueFactory(cellData -> cellData.getValue().precoProperty().asObject());
        tableView.getColumns().addAll(idColumn, nomeColumn, precoColumn);

        // Configurar Formulário
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setPadding(new Insets(10, 10, 10, 10));

        TextField nomeField = new TextField();
        TextField precoField = new TextField();
        Button addButton = new Button("Adicionar");

        form.add(new Label("Nome:"), 0, 0);
        form.add(nomeField, 1, 0);
        form.add(new Label("Preço:"), 0, 1);
        form.add(precoField, 1, 1);
        form.add(addButton, 1, 2);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(tableView, form);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Carregar dados
        loadProducts();

        // Adicionar produto
        addButton.setOnAction(event -> {
            String nome = nomeField.getText();
            double preco = Double.parseDouble(precoField.getText());
            addProduct(nome, preco);
            loadProducts();
        });
    }

    private void loadProducts() {
        productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM produto";
        try (Connection connection = DatabaseConnection.getConnection();
             ResultSet rs = connection.createStatement().executeQuery(sql)) {
            while (rs.next()) {
                productList.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableView.setItems(productList);
    }

    private void addProduct(String nome, double preco) {
        String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setDouble(2, preco);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
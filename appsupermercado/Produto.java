import javafx.beans.property.*;

public class Produto {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final DoubleProperty preco;

    public Produto(int id, String nome, double preco) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.preco = new SimpleDoubleProperty(preco);
    }

    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    public String getNome() { return nome.get(); }
    public void setNome(String nome) { this.nome.set(nome); }
    public StringProperty nomeProperty() { return nome; }

    public double getPreco() { return preco.get(); }
    public void setPreco(double preco) { this.preco.set(preco); }
    public DoubleProperty precoProperty() { return preco; }
}
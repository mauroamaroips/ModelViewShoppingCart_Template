```java
public class ShoppingCart extends Subject {
    
    private String name;
    private List<Product> products;

    public ShoppingCart(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public void addProduct(Product p) {
        products.add(p);
        notifyObservers(this);
    }

    public void addProduct(int id) throws ShoppingCartException {
        Product p= ProductListFactory.createProduct(id);
        addProduct(p);
    }

    public void removerProduct(Product p) {
        products.remove(p);
        this.notifyObservers(this);
    }
    public Product lastProductAdded(){
          return products.get(products.size()-1);

    }
    public String getName() {
        return name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public float getTotal() {
        float total=0.0f;
        for(Product p: products)
            total+=p.getCost();
        return total;
    }


}  

public class ShoppingCartUI extends VBox implements Observer {

    //controlos
    private TextField txtInputId;
    private Button btAddProduct;
    private ListView<Product> listProductsView;
    private Label lblError;
    private Label lblCount;

    //modelo
    private final ShoppingCart model;
    
    public ShoppingCartUI(ShoppingCart model) {
        this.model = model;
        initComponents();
        setTriggers();
        update(model);
    }
    
    @Override
    public void update(Object o) {
        if(o instanceof ShoppingCart) {
            ShoppingCart model = (ShoppingCart)o;
            Collection<Product> listProducts = model.getProducts();
            this.listProductsView.getItems().clear();
            listProductsView.getItems().addAll(listProducts);
            lblCount.setText(""+ model.getTotal());
        }
    }
    
    private void initComponents() {
        
        this.txtInputId = new TextField();
        this.btAddProduct = new Button("Add");
        this.listProductsView = new ListView<>();
        lblError = new Label();
        
        lblCount = new Label("0");
        
        HBox firstRow = new HBox(txtInputId,btAddProduct, new Label("Total Value"),lblCount);
        firstRow.setAlignment(Pos.CENTER);
        firstRow.setPadding(new Insets(2,2,2,2));
        firstRow.setSpacing(4);

        this.getChildren().addAll(firstRow, listProductsView, lblError);
    }

    private void setTriggers() {
        btAddProduct.setOnAction((ActionEvent event) -> {
            doAddProduct();
        });

    }

    private void doAddProduct() {
        String id = getInputProductId();

        try {
            model.addProduct(Integer.parseInt(id));
            clearInput();
        } catch (ShoppingCartException e) {
            showError(e.getMessage());
        }
        catch (NumberFormatException e) {
            showError("it is not a number");
        }
    }



    private void showError(String msg) {
        lblError.setText(msg);
    }



    private String getInputProductId() {
        return txtInputId.getText().trim();
    }

    private void clearInput() {
        txtInputId.setText("");
    }

   
    
}
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                
       ShoppingCart model = new ShoppingCart("Bruno");
       ShoppingCartUI view = new ShoppingCartUI(model);
       model.addObservers(view);
        //podemos na mesma janela compor varias "visualizacoes"
        //se necessario
        BorderPane window = new BorderPane();
        window.setCenter(view);
        Scene scene = new Scene(window, 300, 250);
        primaryStage.setTitle("Shopping Cart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

```

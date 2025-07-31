package model.dao;

public class ProductDao {
    // (*)싱글톤
    private ProductDao() {}
    private static final ProductDao instance = new ProductDao();
    public static ProductDao getInstance() {
        return instance;
    }

    /*
    public boolean productAdd() {

    }

    public ArrayList<ProductDto> productPrint() {

    }

    public ArrayList<ProductDto> productPrint(int num) {

    }

    public boolean productUpdate() {

    }

    public boolean productDelete() {

    }

    public boolean cartAdd() {

    }

    public ArrayList<ProductDto> cartPrint() {

    }

    public boolean cartDelete() {

    }
    */
}

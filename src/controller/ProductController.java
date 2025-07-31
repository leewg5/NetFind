package controller;

public class ProductController {
    // (*)싱글톤
    private ProductController() {}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance() {
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

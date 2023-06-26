package bean;

public class Product {

	// 商品idを格納する変数
	private String productId;
	// 商品名を格納する変数
	private String product;
	// 価格を格納する変数
	private int price;
	// 在庫数を格納する変数
	private int stock;
	// 写真を格納する変数
	private String image_url;


	// コンストラクタ
	public Product() {
		productId = null;
		product= null;
		price = 0;
		stock = 0;
		image_url= null;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProduct() {
		return product;
	}


	public void setProduct(String product) {
		this.product = product;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


}

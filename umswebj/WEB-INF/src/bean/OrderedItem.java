package bean;

public class OrderedItem {
	// インスタンス変数
	private String product;
	private int amount;
	private int price;

	// コンストラクタ
	public OrderedItem() {
		this.product = null;
		this.amount = 0;
		this.price = 0;
	}

	// アクセスメソッド
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}

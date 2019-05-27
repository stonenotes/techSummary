package cn.itcast.entity;

public class Food {

	private int id;// INT PRIMARY KEY AUTO_INCREMENT, -- ����
	private String foodName;// VARCHAR(20), -- ������
	private int foodType_id;// INT, -- ������ϵ, ����ֶ�
	private double price;// DOUBLE, -- �۸�
	private double mprice;// DOUBLE, -- ��Ա�۸�
	private String remark;// VARCHAR(200), -- ���
	private String img;// VARCHAR(100) -- ͼƬ
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getFoodType_id() {
		return foodType_id;
	}
	public void setFoodType_id(int foodTypeId) {
		foodType_id = foodTypeId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getMprice() {
		return mprice;
	}
	public void setMprice(double mprice) {
		this.mprice = mprice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	
}

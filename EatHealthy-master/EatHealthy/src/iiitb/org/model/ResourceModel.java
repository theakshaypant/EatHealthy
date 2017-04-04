package iiitb.org.model;

//setters and getters for every variables
public class ResourceModel {

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCalorie() {
		return calorie;
	}
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getSodium() {
		return sodium;
	}
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	public double getTotalFat() {
		return totalFat;
	}
	public void setTotalFat(double totalFat) {
		this.totalFat = totalFat;
	}
	private double calorie;
	private double carbohydrate;
	private double protein;
	private double sodium;
	private double totalFat;
	
	

}

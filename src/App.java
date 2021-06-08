import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class App {

	enum Pizza_Size {
		Personal, Medium, Large
	};

	enum Toppings_Meats {
		Pepperoni, Groundbeef, Bacon, Chicken, PhillyCheeseSteak
	};

	enum Toppings_Veggies {
		ExtraCheese, Jalapenos, GreenPeppers, Artichoke, Olives, Onions
	};

	enum Drink_Size {
		Medium, Large, Family
	};

	record Order(boolean delivery, Pizza pizza, Drink_Size drink) {
	}

	record Pizza(Pizza_Size size, HashSet<Toppings_Meats> meats, HashSet<Toppings_Veggies> veggies) {
	}

	public static void main(String[] args) {
		// initialize menu prices
		HashMap<Pizza_Size, Double> pizza_size_prices = new HashMap<>();
		pizza_size_prices.put(Pizza_Size.Personal, 9.99);
		pizza_size_prices.put(Pizza_Size.Medium, 12.99);
		pizza_size_prices.put(Pizza_Size.Large, 14.99);

		HashMap<Drink_Size, Double> DrinksPrice = new HashMap<>();
		DrinksPrice.put(Drink_Size.Medium, 1.99);
		DrinksPrice.put(Drink_Size.Large, 2.99);
		DrinksPrice.put(Drink_Size.Family, 4.99);

		HashMap<Toppings_Meats, Double> MeatToppingPrice = new HashMap<>();
		MeatToppingPrice.put(Toppings_Meats.Pepperoni, 0.50);
		MeatToppingPrice.put(Toppings_Meats.Groundbeef, 1.00);
		MeatToppingPrice.put(Toppings_Meats.Bacon, 0.75);
		MeatToppingPrice.put(Toppings_Meats.Chicken, 1.00);
		MeatToppingPrice.put(Toppings_Meats.PhillyCheeseSteak, 1.50);

		HashMap<Toppings_Veggies, Double> VeggieToppingPrice = new HashMap<>();
		VeggieToppingPrice.put(Toppings_Veggies.ExtraCheese, 0.50);
		VeggieToppingPrice.put(Toppings_Veggies.Jalapenos, 0.50);
		VeggieToppingPrice.put(Toppings_Veggies.GreenPeppers, 0.75);
		VeggieToppingPrice.put(Toppings_Veggies.Artichoke, 1.20);
		VeggieToppingPrice.put(Toppings_Veggies.Olives, 1.00);
		VeggieToppingPrice.put(Toppings_Veggies.Onions, 0.75);
		
		

	}

}
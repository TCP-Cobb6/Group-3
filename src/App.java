import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class App {

	enum Pizza_Size {
		Personal, Medium, Large
	};

	enum Drink_Size {
		Medium, Large, Family
	};

	enum Toppings {
		Pepperoni, Ground_beef, Bacon, Chicken, Philly_Cheesesteak, Extra_Cheese, Jalapenos, Green_peppers, Artichoke,
		Olives, Onions
	}

	record Pizza(Pizza_Size Size, HashSet<Toppings> Toppings) {
	}

	record Order(boolean Delivery, Pizza Pizza, Drink_Size Drink) {
	}

	// TODO Add the rest of the necessary data structures
	// TODO Fill in data structures

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		HashMap<Pizza_Size, Double> pizza_size_prices = new HashMap<>();
		
		pizza_size_prices.put(Pizza_Size.Personal, 9.99);
		pizza_size_prices.put(Pizza_Size.Medium, 12.99);
		pizza_size_prices.put(Pizza_Size.Large, 14.99);
		
		// TODO Print greeting
		// TODO Ask for name
	//	System.out.print("What is your name? ");
	//	String user = in.next();

		// TODO Print menu and current order

		// System.out.println(Toppings.Artichoke.toString());

		Pizza_Size.values();
		Toppings.values();
		Drink_Size.values();

		System.out.println("MENU ITEMS:");
		System.out.println("PIZZA SIZES:");
		
		for (Pizza_Size PizzaSize : Pizza_Size.values()) {
			Double pizzaPrice = pizza_size_prices.get(PizzaSize);
			System.out.printf("%3.2f %s ", pizzaPrice, PizzaSize.toString());
		}

		System.out.println("MENU ITEMS:");
		System.out.println("TOPPINGS:");

		for (Toppings topping : Toppings.values()) {
			// Double toppingPrice = topping_prices.get(ToppingSize);
			System.out.printf(topping.toString());
		}

		System.out.println("MENU ITEMS:");
		System.out.println("DRINK SIZES:");

		for (Drink_Size DrinkSize : Drink_Size.values()) {
			//Double drinkPrice = drinl_prices.get(DrinkSize);
			System.out.printf(DrinkSize.toString());
		}

		
		
		// TODO Take order
		// 1. Gather input
		// 2. Change order based on input
		// 3. Print new order
		// 4. Return to step 1 or finish order

		// TODO Calculate and print summary

	}

}

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

		public String toString() {
			return String.format(
					"Size: %s Toppings: %s",
					Size.toString(),
					Toppings.stream().map(x -> x.toString()).reduce((acc, x) -> acc + ", " + x).orElse(""));
		}
	}

	record Order(boolean Delivery, Pizza Pizza, Drink_Size Drink) {
		public String toString() {
			return String.format(
					"Delivery: %s%nPizza: %s%nDrink: %s",
					Delivery ? "Yes" : "No",
					Pizza.toString(),
					Drink.toString());
		}
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
		System.out.print("What is your name? ");
		// String user = in.next();

		Order order = new Order(false, new Pizza(Pizza_Size.Large, new HashSet()), Drink_Size.Family);
		order.Pizza.Toppings.add(Toppings.Artichoke);
		order.Pizza.Toppings.add(Toppings.Pepperoni);
		System.out.println();
		System.out.println(order.toString());

		// TODO Print menu and current order

		// System.out.println(Toppings.Artichoke.toString());

		Pizza_Size.values();
		Toppings.values();
		Drink_Size.values();

		System.out.println("MENU ITEMS:");
		System.out.println("PIZZA SIZES:");
		for (Pizza_Size PizzaSize : Pizza_Size.values()) {
			System.out.printf("%.2f %s%n", pizza_size_prices.get(PizzaSize), PizzaSize.toString());
		}

		System.out.println();
		System.out.println("TOPPINGS:");

		for (Toppings topping : Toppings.values()) {
			System.out.println(topping.toString());

		}

		System.out.println();
		System.out.println("DRINK SIZES:");

		for (Drink_Size DrinkSize : Drink_Size.values()) {
			System.out.println(DrinkSize.toString());
		}

		// TODO Take order
		// 1. Gather input
		// 2. Change order based on input
		// 3. Print new order
		// 4. Return to step 1 or finish order

		// TODO Calculate and print summary
		
		// initialize the running total
		double total = 0;
		
		// add the cost of the pizza size
		total += pizza_size_prices.get(order.Pizza.Size);
		
		// add the cost of each topping
		for (Toppings topping : order.Pizza.Toppings) {
//			total += pizza_topping_prices.get(topping);
		}
		
		// add the cost of the drink size
//		total += drink_size_prices.get(order.Drink);
		
		// print the result
		System.out.printf("Total cost: %.2f \n", total);

	}

}

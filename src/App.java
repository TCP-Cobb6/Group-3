import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

// TODO Add "special topping of the day"

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

	record Pizza(Pizza_Size size, HashSet<Toppings_Meats> meats, HashSet<Toppings_Veggies> veggies) {

		public String toString() {
			String pizzaDetails = " Size Selected: " + size.toString() + "\n";

			pizzaDetails += " Veggies Selected: \n";
			for (Toppings_Veggies topping : veggies) {
				pizzaDetails += "  " + topping.toString() + ", ";
			}
			pizzaDetails += "\n";

			pizzaDetails += " Meats Selected: \n";
			for (Toppings_Meats topping : meats) {
				pizzaDetails += "  " + topping.toString() + ", ";
			}
			pizzaDetails += "\n";

			return pizzaDetails;
		}
	}

	record Order(boolean delivery, Pizza pizza, Drink_Size drink) {

		public String toString() {

			String completeOrder = "Delivery Option: \n ";

			completeOrder += (delivery ? "Yes" : "No") + "\n";

			completeOrder += "Pizza: \n";
			completeOrder += pizza.toString();

			completeOrder += "Drink Selection: \n ";
			completeOrder += drink.toString() + " Size\n";

			return completeOrder;
		}

	}

	static void printMenu(HashMap<Pizza_Size, Double> pizzaPrices,
			HashMap<Toppings_Veggies, Double> veggiePrices,
			HashMap<Toppings_Meats, Double> meatPrices,
			HashMap<Drink_Size, Double> drinkPrices) {
		System.out.println("MENU ITEMS:");
		System.out.println("PIZZA SIZES:");

		for (Pizza_Size PizzaSize : Pizza_Size.values()) {
			Double pizzaPrice = pizzaPrices.get(PizzaSize);
			System.out.printf(" %s %.2f \n", PizzaSize.toString(), pizzaPrice);
		}

		System.out.println("VEGGIE PIZZA TOPPINGS:");

		for (Toppings_Veggies topping : Toppings_Veggies.values()) {
			Double toppingPrice = veggiePrices.get(topping);
			System.out.printf(" %s %.2f \n", topping.toString(), toppingPrice);
		}

		System.out.println("MEAT PIZZA TOPPINGS:");

		for (Toppings_Meats topping : Toppings_Meats.values()) {
			Double toppingPrice = meatPrices.get(topping);
			System.out.printf(" %s %.2f \n", topping.toString(), toppingPrice);
		}

		System.out.println("DRINK SIZES:");

		for (Drink_Size drinkSize : Drink_Size.values()) {
			Double drinkPrice = drinkPrices.get(drinkSize);
			System.out.printf(" %s %.2f \n", drinkSize.toString(), drinkPrice);
		}
	}

	public static void main(String[] args) {

		//// Startup Preparation ////

		Scanner in = new Scanner(System.in);

		// initialize menu prices
		HashMap<Pizza_Size, Double> pizza_size_prices = new HashMap<>();
		pizza_size_prices.put(Pizza_Size.Personal, 9.99);
		pizza_size_prices.put(Pizza_Size.Medium, 12.99);
		pizza_size_prices.put(Pizza_Size.Large, 14.99);

		HashMap<Toppings_Veggies, Double> VeggieToppingPrice = new HashMap<>();
		VeggieToppingPrice.put(Toppings_Veggies.ExtraCheese, 0.50);
		VeggieToppingPrice.put(Toppings_Veggies.Jalapenos, 0.50);
		VeggieToppingPrice.put(Toppings_Veggies.GreenPeppers, 0.75);
		VeggieToppingPrice.put(Toppings_Veggies.Artichoke, 1.20);
		VeggieToppingPrice.put(Toppings_Veggies.Olives, 1.00);
		VeggieToppingPrice.put(Toppings_Veggies.Onions, 0.75);
		
		HashMap<Toppings_Meats, Double> MeatToppingPrice = new HashMap<>();
		MeatToppingPrice.put(Toppings_Meats.Pepperoni, 0.50);
		MeatToppingPrice.put(Toppings_Meats.Groundbeef, 1.00);
		MeatToppingPrice.put(Toppings_Meats.Bacon, 0.75);
		MeatToppingPrice.put(Toppings_Meats.Chicken, 1.00);
		MeatToppingPrice.put(Toppings_Meats.PhillyCheeseSteak, 1.50);
		
		HashMap<Drink_Size, Double> DrinksPrice = new HashMap<>();
		DrinksPrice.put(Drink_Size.Medium, 1.99);
		DrinksPrice.put(Drink_Size.Large, 2.99);
		DrinksPrice.put(Drink_Size.Family, 4.99);

		// Greet customer
		System.out.println("Welcome to Pizza Machine!");
		System.out.print("May I have a name for this order? ");
		// String user = in.next();

		// Print menu
		System.out.println();
		printMenu(pizza_size_prices, VeggieToppingPrice, MeatToppingPrice, DrinksPrice);

		//// Take order ////

		// 1. Gather input
		// 2. Change order based on input
		// 3. Print new order
		// 4. Return to step 1 or finish order

		System.out.print("Enter a number to add (or remove) an item to your order: ");

		Order order = new Order(false, new Pizza(Pizza_Size.Large, new HashSet(), new HashSet()), Drink_Size.Family);

		order.pizza.veggies.add(Toppings_Veggies.Artichoke);

		order.pizza.meats.add(Toppings_Meats.Pepperoni);

		System.out.println();

		System.out.println(order.toString());

		//// Calculate and print order summary ////

		// initialize the running total
		double total = 0;

		// add the cost of the pizza size
		total += pizza_size_prices.get(order.pizza.size);

		// add the cost of each veggie topping
		for (Toppings_Veggies topping : order.pizza.veggies) {
			total += VeggieToppingPrice.get(topping);
		}

		// add the cost of each meat topping
		for (Toppings_Meats topping : order.pizza.meats) {
			total += MeatToppingPrice.get(topping);
		}

		// add the cost of the drink size
		total += DrinksPrice.get(order.drink);

		// print the result
		System.out.printf("Total cost: %.2f \n", total);

	}

}

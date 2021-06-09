import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

// TODO Add "special topping of the day"

public class App {

	enum Pizza_Size {
		Personal, Medium, Large
	};

	enum Toppings_Meats {
		Pepperoni, GroundBeef, Bacon, Chicken, PhillyCheeseSteak
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

	static void printMenu(HashMap<Pizza_Size, Double> pizzaPrices, HashMap<Toppings_Veggies, Double> veggiePrices,
			HashMap<Toppings_Meats, Double> meatPrices, HashMap<Drink_Size, Double> drinkPrices) {
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

		// get handle to user input
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
		MeatToppingPrice.put(Toppings_Meats.GroundBeef, 1.00);
		MeatToppingPrice.put(Toppings_Meats.Bacon, 0.75);
		MeatToppingPrice.put(Toppings_Meats.Chicken, 1.00);
		MeatToppingPrice.put(Toppings_Meats.PhillyCheeseSteak, 1.50);

		HashMap<Drink_Size, Double> DrinksPrice = new HashMap<>();
		DrinksPrice.put(Drink_Size.Medium, 1.99);
		DrinksPrice.put(Drink_Size.Large, 2.99);
		DrinksPrice.put(Drink_Size.Family, 4.99);

		// initialize user input options
		HashMap<String, Pizza_Size> pizzaSizeChoices = new HashMap<>();
		pizzaSizeChoices.put("1", Pizza_Size.Personal);
		pizzaSizeChoices.put("2", Pizza_Size.Medium);
		pizzaSizeChoices.put("3", Pizza_Size.Large);

		HashMap<String, Toppings_Veggies> VeggieChoices = new HashMap<>();
		VeggieChoices.put("12", Toppings_Veggies.ExtraCheese);
		VeggieChoices.put("13", Toppings_Veggies.Jalapenos);
		VeggieChoices.put("14", Toppings_Veggies.GreenPeppers);
		VeggieChoices.put("15", Toppings_Veggies.Artichoke);
		VeggieChoices.put("16", Toppings_Veggies.Olives);
		VeggieChoices.put("17", Toppings_Veggies.Onions);

		HashMap<String, Toppings_Meats> MeatChoices = new HashMap<>();
		MeatChoices.put("7", Toppings_Meats.Pepperoni);
		MeatChoices.put("8", Toppings_Meats.GroundBeef);
		MeatChoices.put("9", Toppings_Meats.Bacon);
		MeatChoices.put("10", Toppings_Meats.Chicken);
		MeatChoices.put("11", Toppings_Meats.PhillyCheeseSteak);

		HashMap<String, Drink_Size> DrinkSizeChoices = new HashMap<>();
		DrinkSizeChoices.put("4", Drink_Size.Medium);
		DrinkSizeChoices.put("5", Drink_Size.Large);
		DrinkSizeChoices.put("6", Drink_Size.Family);

		Order order = new Order(false, new Pizza(Pizza_Size.Large, new HashSet(), new HashSet()), Drink_Size.Family);

		order.pizza.veggies.add(Toppings_Veggies.Artichoke);

		order.pizza.meats.add(Toppings_Meats.Pepperoni);

		// Greet customer
		System.out.println("Welcome to Pizza Machine!");
		System.out.print("May I have a name for this order? ");
		// String user = in.next();

		// Print menu
		System.out.println();

		//// Take order ////

		while (true) {
			printMenu(pizza_size_prices, VeggieToppingPrice, MeatToppingPrice, DrinksPrice);

			System.out.println("""
					Enter
					- a number to add (or remove) an item to your order
					- 'finish' to check out or
					- 'cancel' to leave without buying anything
					""");
			System.out.print("? ");
			String input = in.next();

			if (input.toLowerCase().startsWith("f")) {
				break;
			} else if (input.toLowerCase().startsWith("c")) {
				return;
			}

			switch (input) {

			case "1":
				order.pizza.size = Pizza_Size.Personal;
				break;
			case "2":
				order.pizza.size = Pizza_Size.Medium;
				break;
			case "3":
				order.pizza.size = Pizza_Size.Large;
				break;
			case "4":
				order.drink = Drink_Size.Medium;
				break;
			case "5":
				order.drink = Drink_Size.Large;
				break;
			case "6":
				order.drink = Drink_Size.Family;
				break;
			case "7":
				if (order.pizza.Toppings.contains(Toppings.Pepperoni)) {
					order.pizza.Toppings.remove(Toppings.Pepperoni);
				} else {
					order.pizza.Toppings.add(Toppings.Pepperoni);
				}
				break;
			case "8":
				if (order.pizza.Toppings.contains(Toppings.Ground_beef)) {
					order.pizza.Toppings.remove(Toppings.Ground_beef);
				} else {
					order.pizza.Toppings.add(Toppings.Ground_beef);
				}
				break;
			case "9":
				if (order.pizza.Toppings.contains(Toppings.Bacon)) {
					order.pizza.Toppings.remove(Toppings.Bacon);
				} else {
					order.pizza.Toppings.add(Toppings.Bacon);
				}
				break;
			case "10":
				if (order.pizza.Toppings.contains(Toppings.Chicken)) {
					order.pizza.Toppings.remove(Toppings.Chicken);
				} else {
					order.pizza.Toppings.add(Toppings.Chicken);
				}
				break;
			case "11":
				if (order.pizza.Toppings.contains(Toppings.Philly_Cheesesteak)) {
					order.pizza.Toppings.remove(Toppings.Philly_Cheesesteak);
				} else {
					order.pizza.Toppings.add(Toppings.Philly_Cheesesteak);
				}
				break;
			case "12":
				if (order.pizza.Toppings.contains(Toppings.Extra_Cheese)) {
					order.pizza.Toppings.remove(Toppings.Extra_Cheese);
				} else {
					order.pizza.Toppings.add(Toppings.Extra_Cheese);
				}
				break;
			case "13":
				if (order.pizza.Toppings.contains(Toppings.Jalapenos)) {
					order.pizza.Toppings.remove(Toppings.Jalapenos);
				} else {
					order.pizza.Toppings.add(Toppings.Jalapenos);
				}
				break;
			case "14":
				if (order.pizza.Toppings.contains(Toppings.Green_peppers)) {
					order.pizza.Toppings.remove(Toppings.Green_peppers);
				} else {
					order.pizza.Toppings.add(Toppings.Green_peppers);
				}
				break;
			case "15":
				if (order.pizza.Toppings.contains(Toppings.Artichoke)) {
					order.pizza.Toppings.remove(Toppings.Artichoke);
				} else {
					order.pizza.Toppings.add(Toppings.Artichoke);
				}
				break;
			case "16":
				if (order.pizza.Toppings.contains(Toppings.Olives)) {
					order.pizza.Toppings.remove(Toppings.Olives);
				} else {
					order.pizza.Toppings.add(Toppings.Olives);
				}
				break;
			case "17":
				if (order.pizza.Toppings.contains(Toppings.Onions)) {
					order.pizza.Toppings.remove(Toppings.Onions);
				} else {
					order.pizza.Toppings.add(Toppings.Onions);
				}
				break;
			default:
				// code block
			}

			System.out.println(order.toString());
		}

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

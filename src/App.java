import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;

public class App {

	static final double TAX = .06;

	enum Pizza_Size {
		Personal, Medium, Large
	};

	enum Toppings_Meats {
		Pepperoni, Ground_Beef, Bacon, Chicken, Philly_Cheesesteak
	};

	enum Toppings_Veggies {
		Extra_Cheese, Jalapenos, Green_Peppers, Artichoke, Olives, Onions
	};

	enum Drink_Size {
		Medium, Large, Family
	};

	enum Special_Topping_Oftheday {
		Crab, Jelly_Beans, Crickets, Gold_Leaf
	}

	record Pizza(Pizza_Size size, HashSet<Toppings_Veggies> veggies, HashSet<Toppings_Meats> meats,
			Special_Topping_Oftheday specialTopping, boolean hasSpecial) {

		public String toString() {
			String pizzaDetails = " Size Selected: \n  " + size.toString() + "\n";

			pizzaDetails += " Veggies Selected: \n  ";
			for (Toppings_Veggies topping : veggies) {
				pizzaDetails += topping.toString() + ", ";
			}
			pizzaDetails += veggies.isEmpty() ? "none" : "";
			pizzaDetails += "\n";

			pizzaDetails += " Meats Selected: \n  ";
			for (Toppings_Meats topping : meats) {
				pizzaDetails += topping.toString() + ", ";
			}
			pizzaDetails += meats.isEmpty() ? "none" : "";
			pizzaDetails += "\n";

			pizzaDetails += hasSpecial ? " " + specialTopping.toString() + " selected!\n" : "";

			return pizzaDetails;
		}
	}

	record Order(boolean delivery, Pizza pizza, Drink_Size drink) {

		public String toString() {

			String completeOrder = "";

			completeOrder += (delivery ? "Delivery" : "Pickup") + "\n";

			completeOrder += "Pizza: \n";
			completeOrder += pizza.toString();

			completeOrder += "Drink Selection: \n ";
			completeOrder += drink.toString() + " Size\n";

			return completeOrder;
		}

	}
	
	record Prices(HashMap<Pizza_Size, Double> pizza, HashMap<Toppings_Veggies, Double> veggie,
			HashMap<Toppings_Meats, Double> meat, HashMap<Drink_Size, Double> drink,
			double specialTopping) {}

	static void printMenu(Prices prices, Special_Topping_Oftheday specialTopping) {
		// prepare to print number labels
		int i = 1;

		System.out.println();
		System.out.println("MENU ITEMS:");
		System.out.println("PIZZA SIZES:");

		for (Pizza_Size PizzaSize : Pizza_Size.values()) {
			Double pizzaPrice = prices.pizza.get(PizzaSize);
			System.out.printf(" %2d %s %.2f \n", i, PizzaSize.toString(), pizzaPrice);
			i++;
		}

		System.out.println("VEGGIE PIZZA TOPPINGS:");

		for (Toppings_Veggies topping : Toppings_Veggies.values()) {
			Double toppingPrice = prices.veggie.get(topping);
			System.out.printf(" %2d %s %.2f \n", i, topping.toString(), toppingPrice);
			i++;
		}

		System.out.println("MEAT PIZZA TOPPINGS:");

		for (Toppings_Meats topping : Toppings_Meats.values()) {
			Double toppingPrice = prices.meat.get(topping);
			System.out.printf(" %2d %s %.2f \n", i, topping.toString(), toppingPrice);
			i++;
		}

		System.out.println("DRINK SIZES:");

		for (Drink_Size drinkSize : Drink_Size.values()) {
			Double drinkPrice = prices.drink.get(drinkSize);
			System.out.printf(" %2d %s %.2f \n", i, drinkSize.toString(), drinkPrice);
			i++;
		}

		System.out.println("Special Topping of the Day:");
		System.out.printf(" %2d %s %.2f \n", i, specialTopping.toString(), prices.specialTopping);
	}

	static double calculateCost(Order order, Prices prices) {
		// initialize the running total
		double total = 0;

		// add the cost of the pizza size
		total += prices.pizza.get(order.pizza.size);

		// add the cost of each veggie topping
		for (Toppings_Veggies topping : order.pizza.veggies) {
			total += prices.veggie.get(topping);
		}

		// add the cost of each meat topping
		for (Toppings_Meats topping : order.pizza.meats) {
			total += prices.meat.get(topping);
		}

		// add the cost of the drink size
		total += prices.drink.get(order.drink);

		// add the cost of the special topping
		total += order.pizza.hasSpecial ? prices.specialTopping : 0;

		return total;
	}

	static Order changeDelivery(Order order) {
		return new Order(!order.delivery, order.pizza, order.drink);
	}

	static Order changePizzaSize(Order order, Pizza_Size newSize) {
		return new Order(order.delivery, new Pizza(newSize, order.pizza.veggies, order.pizza.meats,
				order.pizza.specialTopping, order.pizza.hasSpecial), order.drink);
	}

	static Order changeVeggieTopping(Order order, Toppings_Veggies newVeggie) {
		if (order.pizza.veggies.contains(newVeggie)) {
			order.pizza.veggies.remove(newVeggie);
		} else {
			order.pizza.veggies.add(newVeggie);
		}
		return order;
	}

	static Order changeMeatTopping(Order order, Toppings_Meats newMeat) {
		if (order.pizza.meats.contains(newMeat)) {
			order.pizza.meats.remove(newMeat);
		} else {
			order.pizza.meats.add(newMeat);
		}
		return order;
	}

	static Order changeDrinkSize(Order order, Drink_Size newSize) {
		return new Order(order.delivery, order.pizza, newSize);
	}

	static Order changeHasSpecial(Order order) {
		return new Order(order.delivery, new Pizza(order.pizza.size, order.pizza.veggies, order.pizza.meats,
				order.pizza.specialTopping, !order.pizza.hasSpecial), order.drink);
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
		VeggieToppingPrice.put(Toppings_Veggies.Extra_Cheese, 0.50);
		VeggieToppingPrice.put(Toppings_Veggies.Jalapenos, 0.50);
		VeggieToppingPrice.put(Toppings_Veggies.Green_Peppers, 0.75);
		VeggieToppingPrice.put(Toppings_Veggies.Artichoke, 1.20);
		VeggieToppingPrice.put(Toppings_Veggies.Olives, 1.00);
		VeggieToppingPrice.put(Toppings_Veggies.Onions, 0.75);

		HashMap<Toppings_Meats, Double> MeatToppingPrice = new HashMap<>();
		MeatToppingPrice.put(Toppings_Meats.Pepperoni, 0.50);
		MeatToppingPrice.put(Toppings_Meats.Ground_Beef, 1.00);
		MeatToppingPrice.put(Toppings_Meats.Bacon, 0.75);
		MeatToppingPrice.put(Toppings_Meats.Chicken, 1.00);
		MeatToppingPrice.put(Toppings_Meats.Philly_Cheesesteak, 1.50);

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
		VeggieChoices.put("4", Toppings_Veggies.Extra_Cheese);
		VeggieChoices.put("5", Toppings_Veggies.Jalapenos);
		VeggieChoices.put("6", Toppings_Veggies.Green_Peppers);
		VeggieChoices.put("7", Toppings_Veggies.Artichoke);
		VeggieChoices.put("8", Toppings_Veggies.Olives);
		VeggieChoices.put("9", Toppings_Veggies.Onions);

		HashMap<String, Toppings_Meats> MeatChoices = new HashMap<>();
		MeatChoices.put("10", Toppings_Meats.Pepperoni);
		MeatChoices.put("11", Toppings_Meats.Ground_Beef);
		MeatChoices.put("12", Toppings_Meats.Bacon);
		MeatChoices.put("13", Toppings_Meats.Chicken);
		MeatChoices.put("14", Toppings_Meats.Philly_Cheesesteak);

		HashMap<String, Drink_Size> DrinkSizeChoices = new HashMap<>();
		DrinkSizeChoices.put("15", Drink_Size.Medium);
		DrinkSizeChoices.put("16", Drink_Size.Large);
		DrinkSizeChoices.put("17", Drink_Size.Family);

		final double specialToppingPrice = 3.0;
		
		Prices prices = new Prices(pizza_size_prices, VeggieToppingPrice, MeatToppingPrice, DrinksPrice, specialToppingPrice);

		// initialize default order
		Random rand = new Random();
		Special_Topping_Oftheday[] specials = Special_Topping_Oftheday.values();
		Order order = new Order(false, new Pizza(Pizza_Size.Medium, new HashSet(), new HashSet(),
				specials[rand.nextInt(specials.length)], false), Drink_Size.Medium);

		// Greet customer
		System.out.println("Welcome to Pizza Machine!");
		System.out.print("May I have a name for this order? ");
		String user = in.next();

		//// Take order ////

		while (true) {
			printMenu(prices, order.pizza.specialTopping);

			System.out.println();

			System.out.println("Current Order");
			System.out.println("-------------");
			System.out.println(order.toString());
			System.out.printf("Current total: %5.2f", calculateCost(order, prices));
			
			System.out.println();

			System.out.println("""
					Enter
					* a number to add (or remove) an item to your order
					* 'd' to choose whether you want delivery or pickup
					* 'finish' to check out or
					* 'cancel' to leave without buying anything""");
			System.out.print("? ");
			String input = in.next();

			if (input.toLowerCase().startsWith("f")) {
				break;
			} else if (input.toLowerCase().startsWith("c")) {
				System.out.println("Bye! Have a nice day. <3");
				return;
			}

			switch (input) {

			case "d":
				order = changeDelivery(order);
				break;
			case "1":
				order = changePizzaSize(order, Pizza_Size.Personal);
				break;
			case "2":
				order = changePizzaSize(order, Pizza_Size.Medium);
				break;
			case "3":
				order = changePizzaSize(order, Pizza_Size.Large);
				break;
			case "4":
				changeVeggieTopping(order, Toppings_Veggies.Extra_Cheese);
				break;
			case "5":
				changeVeggieTopping(order, Toppings_Veggies.Jalapenos);
				break;
			case "6":
				changeVeggieTopping(order, Toppings_Veggies.Green_Peppers);
				break;
			case "7":
				changeVeggieTopping(order, Toppings_Veggies.Artichoke);
				break;
			case "8":
				changeVeggieTopping(order, Toppings_Veggies.Olives);
				break;
			case "9":
				changeVeggieTopping(order, Toppings_Veggies.Onions);
				break;
			case "10":
				changeMeatTopping(order, Toppings_Meats.Pepperoni);
				break;
			case "11":
				changeMeatTopping(order, Toppings_Meats.Ground_Beef);
				break;
			case "12":
				changeMeatTopping(order, Toppings_Meats.Bacon);
				break;
			case "13":
				changeMeatTopping(order, Toppings_Meats.Chicken);
				break;
			case "14":
				changeMeatTopping(order, Toppings_Meats.Philly_Cheesesteak);
				break;
			case "15":
				order = changeDrinkSize(order, Drink_Size.Medium);
				break;
			case "16":
				order = changeDrinkSize(order, Drink_Size.Large);
				break;
			case "17":
				order = changeDrinkSize(order, Drink_Size.Family);
				break;
			case "18":
				order = changeHasSpecial(order);
				break;
			default:
				// code block
			}
		}

		//// Calculate and print order summary ////

		// initialize the running total
		double total = calculateCost(order, prices);

		System.out.println();

		System.out.println("Final Order for " + user);
		System.out.println("-----------------------------");
		System.out.println(order.toString());

		// print the result
		System.out.printf("      Cost: %5.2f \n", total);
		System.out.printf("       Tax: %5.2f \n", total * TAX);
		System.out.printf("Total Cost: %5.2f \n", total * (1.0 + TAX));
		
		System.out.println();

		System.out.printf("Thank you for eating at Pizza Machine, %s. We hope to see you again soon!", user);

	}
}

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
			return String.format("Size: %s Toppings: %s", Size.toString(),
					Toppings.stream().map(x -> x.toString()).reduce((acc, x) -> acc + ", " + x).orElse(""));
		}
	}

	record Order(boolean Delivery, Pizza Pizza, Drink_Size Drink) {
		public String toString() {
			return String.format("Delivery: %s%nPizza: %s%nDrink: %s", Delivery ? "Yes" : "No", Pizza.toString(),
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

		HashMap<String, Pizza_Size> pizzaSizeChoices = new HashMap<>();
		pizzaSizeChoices.put("1", Pizza_Size.Personal);
		pizzaSizeChoices.put("2", Pizza_Size.Medium);
		pizzaSizeChoices.put("3", Pizza_Size.Large);
		
		HashMap<String, Drink_Size> DrinkSizeChoices = new HashMap<>();
			DrinkSizeChoices.put("4", Drink_Size.Medium);
			DrinkSizeChoices.put("5", Drink_Size.Large);
			DrinkSizeChoices.put("6", Drink_Size.Family);

		HashMap<String, Toppings> ToppingsChoices = new HashMap<>();
		 ToppingsChoices.put("7", Toppings.Pepperoni);
		 ToppingsChoices.put("8", Toppings.Ground_beef);
		 ToppingsChoices.put("9", Toppings.Bacon);
		 ToppingsChoices.put("10", Toppings.Chicken);
		 ToppingsChoices.put("11", Toppings.Philly_Cheesesteak);
		 ToppingsChoices.put("12", Toppings.Extra_Cheese);
		 ToppingsChoices.put("13", Toppings.Jalapenos);
		 ToppingsChoices.put("14", Toppings.Green_peppers);
		 ToppingsChoices.put("15", Toppings.Artichoke);
		 ToppingsChoices.put("16", Toppings.Olives);	
		 ToppingsChoices.put("17", Toppings.Onions);	
		
		
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

		while (true) {
			System.out.println("What would you like to have");
			String Input = in.next();

			switch (Input) {

			case "1":
				order.Pizza.Size = Pizza_Size.Personal;
				break;
			case "2":
				 order.Pizza.Size = Pizza_Size.Medium;
				break;
			case "3":
				 order.Pizza.Size = Pizza_Size.Large;
				break;
			case "4":
				order.Drink = Drink_Size.Medium;
				break;
			case "5":
				order.Drink = Drink_Size.Large;
				break;
			case "6":
				order.Drink = Drink_Size.Family;
				break;
			case "7":
			if 	(order.Pizza.Toppings.contains(Toppings.Pepperoni)) {
				order.Pizza.Toppings.remove(Toppings.Pepperoni);
			}
			else {
				order.Pizza.Toppings.add(Toppings.Pepperoni);
			}
				break;
			case "8":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Ground_beef)) {
				order.Pizza.Toppings.remove(Toppings.Ground_beef);
			}
			else {
				order.Pizza.Toppings.add(Toppings.Ground_beef);
			}
				break;
			case "9":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Bacon)) {
					order.Pizza.Toppings.remove(Toppings.Bacon);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Bacon);
				}
				break;
			case "10":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Chicken)) {
					order.Pizza.Toppings.remove(Toppings.Chicken);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Chicken);
				}
				break;
			case "11":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Philly_Cheesesteak)) {
					order.Pizza.Toppings.remove(Toppings.Philly_Cheesesteak);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Philly_Cheesesteak);
				}
				break;
			case "12":
				if 	(order.Pizza.Toppings.contains(Toppings.Extra_Cheese)) {
					order.Pizza.Toppings.remove(Toppings.Extra_Cheese);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Extra_Cheese);
				}
				break;
			case "13":
				if 	(order.Pizza.Toppings.contains(Toppings.Jalapenos)) {
					order.Pizza.Toppings.remove(Toppings.Jalapenos);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Jalapenos);
				}
				break;
			case "14":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Green_peppers)) {
					order.Pizza.Toppings.remove(Toppings.Green_peppers);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Green_peppers);
				}
				break;
			case "15":
				if 	(order.Pizza.Toppings.contains(Toppings.Artichoke)) {
					order.Pizza.Toppings.remove(Toppings.Artichoke);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Artichoke);
				}
				break;
			case "16":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Olives)) {
					order.Pizza.Toppings.remove(Toppings.Olives);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Olives);
				}
				break;
			case "17":
				 	if 	(order.Pizza.Toppings.contains(Toppings.Onions)) {
					order.Pizza.Toppings.remove(Toppings.Onions);
				}
				else {
					order.Pizza.Toppings.add(Toppings.Onions);
				}
				break;
			default:
				// code block
			}
		}

		System.out.println(order.toString());
	}

	// TODO Calculate and print summary

	// initialize the running total
	double total = 0;

	// add the cost of the pizza size
	total+=pizza_size_prices.get(order.Pizza.Size);

	// add the cost of each topping
	for(
	Toppings topping:order.Pizza.Toppings)
	{
//			total += pizza_topping_prices.get(topping);
	}

	// add the cost of the drink size
//		total += drink_size_prices.get(order.Drink);

	// print the result
	System.out.printf("Total cost: %.2f \n",total);

}

}

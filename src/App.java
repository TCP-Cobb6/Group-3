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

	record Pizza(Pizza_Size Size, HashSet<Toppings_Meats> meats, HashSet<Toppings_Veggies> veggies) {

		public String toString() {
			String pizzaDetails = Size.toString() + "\n";

			pizzaDetails += "Pizza Toppings Selected: \n ";
			for (Toppings topping : userToppings) {
				pizzaDetails = pizzaDetails + topping.toString() + ", "  + "\n";
			}

			return pizzaDetails;
		}
	}

	record Order(boolean delivery, Pizza pizza, Drink_Size drink) {

		public String toString() {
			// String completeOrder = Delivery.toString();

			String completeOrder = "Delivery Option: \n ";
					
			completeOrder += (Delivery ? "Yes" : "No") + "\n";

			completeOrder += "Pizza Size Selected: \n ";
			completeOrder += Pizza.toString() + "\n";

			completeOrder += "Drink Selection: \n ";
			completeOrder += Drink.toString() + "\n ";

			return completeOrder;
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

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
		
		// TODO Print greeting
		// TODO Ask for name
		// System.out.print("What is your name? ");
		// String user = in.next();

		// TODO Print menu and current order

		// System.out.println(Toppings.Artichoke.toString());


		System.out.println("MENU ITEMS:");
		System.out.println("PIZZA SIZES:");

		for (Pizza_Size PizzaSize : Pizza_Size.values()) {
			Double pizzaPrice = pizza_size_prices.get(PizzaSize);
			System.out.printf("%s %.2f \n ", PizzaSize.toString(), pizzaPrice);
		}

		System.out.println("PIZZA TOPPINGS:");

		for (Toppings topping : Toppings.values()) {
			// Double toppingPrice = topping_prices.get(ToppingSize);
			//System.out.printf("%s %.2f \n ", topping.toString(), toppingPrice);
		}

		System.out.println("DRINK SIZES:");

		for (Drink_Size DrinkSize : Drink_Size.values()) {
			// Double drinkPrice = drinl_prices.get(DrinkSize);
			// System.out.printf("%s %.2f \n ", DrinkSize.toString(), drinkPrice);
		}

		// TODO Take order
		// 1. Gather input
		// 2. Change order based on input
		// 3. Print new order
		// 4. Return to step 1 or finish order

		// TODO Calculate and print summary

		Order order = new Order(false, new Pizza(Pizza_Size.Large, new HashSet()), Drink_Size.Family);
		
		order.pizza.veggies.add(Toppings_Veggies.Artichoke);
		
		order.pizza.meats.add(Toppings_Meats.Pepperoni);
		
		System.out.println();
		
		System.out.println(order.toString());
		
		
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

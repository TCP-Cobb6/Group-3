
import java.util.HashMap;
import java.util.HashSet;

public class Pizza_Machine {


enum Pizza_Size{ Personal, Medium, Large};


enum Toppings_Meats{
Pepperoni,
Groundbeef,
Bacon,Chicken,
PhillyCheeseSteak};	


enum Toppings_Veggie {
ExtraCheese,
Jalapenos,
GreenPeppers,
Artichoke,
Olives,
Onions};

enum Drink_Size { Medium, Large, Family
	};
	enum Drink_Type {Grape, Orange};

	record Drink(Drink_Size Size, Drink_Type Flavor) {}
	
	record Order(boolean Dellivery, String Pizza, String Drink) {}
	record pizza (Pizza_Size Size,HashSet<Toppings_Meats> ToppingsOrder,HashSet <Toppings_Veggie>VeggieToppings) {}
	record drink (Drink_Size Size,HashSet<Drink_Size>  Drinks) {}
	public static void main(String[] args) 
	{
		HashMap<Pizza_Size, Double> pizza_size_prices = new HashMap<>();
pizza_size_prices.put(Pizza_Size.Personal, 9.99);
pizza_size_prices.put(Pizza_Size.Medium, 12.99);
pizza_size_prices.put(Pizza_Size.Large, 14.99);

HashMap<Drink_Size,Double> DrinksPrice = new HashMap<>();
DrinksPrice.put(Drink_Size.Medium,(1.99));
DrinksPrice.put(Drink_Size.Large,(2.99));
DrinksPrice.put(Drink_Size.Family,(4.99));





HashMap<Toppings_Meats, Double> MeatToppingPrice = new HashMap<>();
MeatToppingPrice.put(Toppings_Meats.Pepperoni, (0.50));
MeatToppingPrice.put(Toppings_Meats.Groundbeef, (1.00));
MeatToppingPrice.put(Toppings_Meats.Bacon, (0.75));
MeatToppingPrice.put(Toppings_Meats.Chicken, (1.00));
MeatToppingPrice.put(Toppings_Meats.PhillyCheeseSteak, (1.50));

HashMap<Toppings_Veggie, Double> VeggieToppingPrice = new HashMap<>();
VeggieToppingPrice.put(Toppings_Veggie.ExtraCheese, (0.50));
VeggieToppingPrice.put(Toppings_Veggie.Jalapenos, (0.50));
VeggieToppingPrice.put(Toppings_Veggie.GreenPeppers, (0.75));
VeggieToppingPrice.put(Toppings_Veggie.Artichoke, (1.20));
VeggieToppingPrice.put(Toppings_Veggie.Olives, (1.00));
VeggieToppingPrice.put(Toppings_Veggie.Onions, (0.75));



	}

}
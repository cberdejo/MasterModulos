package tema4;

import java.util.Arrays;

public class EjSplit {

		public static void main(String [] args) {
			System.out.println("aaabadddb".matches("[a-d]*"));
			System.out.println("abac".matches("[ab]{3}(c|d)"));
			System.out.println("hhsd9".matches("[a-z]{1,4}\\d"));

			
			System.out.println("54763381S".matches("[0-9]{8}[[A-Z]&&[^IOU]]"));
			System.out.println("5-10-2014".matches("\\d{1,2}-\\d{1,2}-\\d{4}"));
			System.out.println("20/05-2005".matches("([0-9]|0[1-9]|[1-2][0-9]|3[0-1])(-|/)([0-9]|0[1-9]|1[0-2])(-|/)(\\d{4})"));
			System.out.println("-3.45E-2".matches("(\\+|-)?[0-9]+\\.?[0-9]*((e|E)(\\+|-)?)?[0-9]*"));

			
			
			String [] items1 = "hola fst todos".split("\\s");
			System.out.println(Arrays.toString(items1));
			String [] items2 = "juan garcia;17..,carpintero".split("[;.,]+");
			System.out.println(Arrays.toString(items2));
			String [] items3 ="20/5-2014".split("(-|/)");
			System.out.println(Arrays.toString(items3));

		}
}

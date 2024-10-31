package tema5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Posiciones{
	public static void main(String[] args) {
		Map<String,List<Integer>> mPos = new TreeMap<>();
		for (int i = 0; i < args.length; i++) {
			String palabra = args[i];
			/*
			if (mPos.containsKey(palabra)) {
				mPos.get(palabra).add(i);
			} else {
				List<Integer> lista = new ArrayList<>();
				lista.add(i);
				mPos.put(palabra, lista);
			}*/
			List<Integer> lista = mPos.get(palabra);
			if (lista == null) {
				lista = new ArrayList<>();
				mPos.put(palabra, lista);
			}
			lista.add(i);

			// mPos.computeIfAbsent(palabra, key -> new ArrayList<>()).add(i);
		}
		for (Map.Entry<String,List<Integer>> entrada : mPos.entrySet()) {
			String clave= entrada.getKey();
			List<Integer> lPos = entrada.getValue();
			System.out.println(clave + ":\t" + lPos);
		}
	}	
}
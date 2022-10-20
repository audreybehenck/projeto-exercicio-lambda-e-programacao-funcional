package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;
import model.services.FuncionarioService;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite o caminho completo do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Funcionario> list = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Funcionario(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}

			System.out.println("Insira o salário: ");
			double sal = sc.nextDouble();

			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> emails = list.stream().filter(f -> f.getSalario() > sal).map(f -> f.getEmail()).sorted(comp)
					.collect(Collectors.toList());

			System.out.println("Email das pessoas com salário superior à $ 2000.00: ");
			emails.forEach(System.out::println);
			
			
			FuncionarioService fs = new FuncionarioService();
			
			double sum = fs.filteredSum(list);
			System.out.println("Soma dos salários das pessoas cujo nome começa com 'M': $" + String.format("%.2f", sum));
			

		}

		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
	}
}
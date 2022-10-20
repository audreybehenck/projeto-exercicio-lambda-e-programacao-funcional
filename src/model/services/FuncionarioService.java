package model.services;

import java.util.List;

import entities.Funcionario;

public class FuncionarioService {

	public double filteredSum(List<Funcionario> list) {
		double sum = 0.0;
		for (Funcionario f : list) {
			if (f.getNome().charAt(0) == 'M') {
				sum += f.getSalario();
			}
		}
		return sum;
	}
}
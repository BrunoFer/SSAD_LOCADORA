package br.edu.ifsudestemg.locadora.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifsudestemg.locadora.entity.Cliente;
import br.edu.ifsudestemg.locadora.entity.Diretor;
import br.edu.ifsudestemg.locadora.entity.Emprestimo;
import br.edu.ifsudestemg.locadora.entity.Exemplar;
import br.edu.ifsudestemg.locadora.entity.Filme;
import br.edu.ifsudestemg.locadora.entity.ItemEmprestimo;
import br.edu.ifsudestemg.locadora.gerencia.Gerenciador;

public class ClienteFuncoes {

	private BufferedReader retornoCliente = new BufferedReader(
			new InputStreamReader(System.in));

	Diretor diretor;
	Filme filme;
	Cliente cliente;
	Exemplar exemplar;
	Emprestimo emprestimo;
	ItemEmprestimo itemEmprestimo;
	List<Filme> filmes;

	public int menu() throws IOException {

		int op;
		do {
			System.out.println("1 - Gerenciar DIRETOR");
			System.out.println("2 - Gerenciar FILME");
			System.out.println("3 - Gerenciar CLIENTE");
			System.out.println("4 - Gerenciar LOCAÇÃO");
			System.out.println("0 - Finalizar programa");
			String opcao = retornoCliente.readLine();
			try {
				op = Integer.parseInt(opcao);
			} catch (NumberFormatException e) {
				System.out.println("Digite uma opção válida!");
				op = 6;
			}
		} while (op < 0 || op > 5);
		return op;
	}

	public int menuDiretor() throws IOException {
		int op;
		do {
			System.out.println("1 - Cadastro de um DIRETOR");
			System.out.println("2 - Associa DIRETOR a um FILME");
			System.out.println("3 - Pesquisa FILMES por DIRETOR");
			System.out.println("4 - Exclusao de um DIRETOR");
			System.out.println("5 - Voltar ao menu principal");

			String opcao = retornoCliente.readLine();
			try {
				op = Integer.parseInt(opcao);
			} catch (NumberFormatException e) {
				System.out.println("Digite uma opção válida!");
				op = 6;
			}
		} while (op < 0 || op > 5);
		return op;
	}

	public int menuFilme() throws IOException {
		int op;
		do {
			System.out.println("1 - Cadastro de um FILME");
			System.out.println("2 - Cadastro de um EXEMPLAR de um Filme");
			System.out.println("3 - Pesquisa FILME por ID");
			System.out.println("4 - Pesquisa FILME por NOME");
			System.out.println("5 - Pesquisa DIRETORES por FILME");
			System.out.println("6 - EXEMPLARES DISPONIVEIS por FILME");
			System.out.println("7 - Pesquisa FILMES por GENERO e ANO");
			System.out.println("8 - Voltar ao menu principal");
			String opcao = retornoCliente.readLine();
			try {
				op = Integer.parseInt(opcao);
			} catch (NumberFormatException e) {
				System.out.println("Digite uma opção válida!");
				op = 9;
			}
		} while (op < 0 || op > 8);
		return op;
	}

	public int menuCliente() throws IOException {
		int op;
		do {
			System.out.println("1 - Cadastro de um CLIENTE");
			System.out.println("2 - Pesquisa CLIENTE por ID");
			System.out.println("3 - Pesquisa CLIENTE por NOME");
			System.out.println("4 - Voltar ao menu principal");
			String opcao = retornoCliente.readLine();
			try {
				op = Integer.parseInt(opcao);
			} catch (NumberFormatException e) {
				System.out.println("Digite uma opção válida!");
				op = 5;
			}
		} while (op < 0 || op > 4);
		return op;
	}

	public int menuEmprestimo() throws IOException {
		int op;
		do {
			System.out.println("1 - EMPRÉSTIMO de EXEMPLARES");
			System.out.println("2 - Devolução");
			System.out.println("3 - Voltar ao menu principal");
			String opcao = retornoCliente.readLine();
			try {
				op = Integer.parseInt(opcao);
			} catch (NumberFormatException e) {
				System.out.println("Digite uma opção válida!");
				op = 4;
			}
		} while (op < 0 || op > 3);
		return op;
	}

	public int id() throws IOException {
		int id;
		do {
			String retorno = retornoCliente.readLine();
			try {
				id = Integer.parseInt(retorno);
			} catch (NumberFormatException e) {
				id = 0;
				System.out.println("ID incorreto. Digite um número válido!");
			}
		} while (id == 0);
		return id;
	}

	public void cadastroDiretor(Gerenciador g) throws IOException {
		diretor = new Diretor();
		System.out.println("Nome do diretor:");
		String retorno = retornoCliente.readLine();
		diretor.setNome(retorno);
		g.addDiretor(diretor);
		System.out.print("DIRETOR inserido!");
	}

	public void associaDiretorFilme(Gerenciador g) throws IOException {
		System.out.println("ID do FILME:");
		filme = new Filme();
		int idFilme = id();
		System.out.println("ID do DIRETOR:");
		diretor = new Diretor();
		int idDiretor = id();
		if (!g.associa(idDiretor, idFilme))
			System.out.println("Não foi possível associar o filme ao diretor!");
	}

	public void filmesDiretor(Gerenciador g) throws IOException {
		System.out.println("ID do DIRETOR:");
		diretor = new Diretor();
		diretor = g.buscaDiretor(id());
		if (diretor != null) {
			filmes = g.buscaFilmes(diretor);
			if (!filmes.isEmpty()) {
				for (Filme f : filmes) {
					System.out.println("Id: " + f.getId() + "\nNome: "
							+ f.getNome() + "\nGênero: " + f.getGenero()
							+ "\nAno de Lançamento: " + f.getAnoLancamento()
							+ "\nDuração: " + f.getDuracao());
				}
			} else {
				System.out
						.println("Não existe FILMES associados a esse diretor!");
			}
		} else {
			System.out.println("Não possui DIRETOR com esse ID!");
		}
	}

	public void deletaDiretor(Gerenciador g) throws IOException {
		System.out.println("Id do DIRETOR:");
		diretor = new Diretor();
		diretor = g.buscaDiretor(id());
		if (diretor != null)
			g.delDiretor(diretor);
		else
			System.out.println("ID de DIRETOR incorreto!");
	}

	public void cadastroFilme(Gerenciador g) throws IOException {
		filme = new Filme();
		System.out.println("Nome do filme:");
		String retorno = retornoCliente.readLine();
		filme.setNome(retorno);
		System.out.println("Ano de lançamento:");
		retorno = retornoCliente.readLine();
		int anoLancamento;
		try {
			anoLancamento = Integer.parseInt(retorno);
		} catch (NumberFormatException e) {
			anoLancamento = 0;
		}
		filme.setAnoLancamento(anoLancamento);
		System.out.println("Gênero:");
		retorno = retornoCliente.readLine();
		filme.setGenero(retorno);
		System.out.println("Duração do filme:");
		retorno = retornoCliente.readLine();
		filme.setDuracao(retorno);
		System.out.println("valor para locação:");
		retorno = retornoCliente.readLine();
		float valorLocacao;
		try {
			valorLocacao = Float.parseFloat(retorno);
			filme.setValorLocacao(valorLocacao);
			filme = g.addFilme(filme);
			System.out.print("FILME inserido!");
		} catch (NumberFormatException e) {
			System.out.println("Dados incorretos. Filme NÃO cadastrado!");
		}
		if (filme == null)
			System.out.println("Dados incorretos. Filme NÃO cadastrado!");
	}

	public void exemplarFilme(Gerenciador g) throws IOException {
		exemplar = new Exemplar();
		System.out.println("ID do FILME referente ao exemplar:");
		int idf = id();
		System.out.println("Data da aquisição:");
		String retorno = retornoCliente.readLine();
		exemplar.setDataAquisicao(retorno);
		exemplar.setEmprestado(false);
		g.addExemplar(exemplar, idf);
	}

	public void filmeID(Gerenciador g) throws IOException {
		System.out.println("ID do FILME pesquisado:");
		filme = new Filme();
		filme = g.buscaFilme(id());
		if (filme != null) {
			System.out.println("Nome: " + filme.getNome() + "\nGênero: "
					+ filme.getGenero() + "\nAno de Lançamento: "
					+ filme.getAnoLancamento() + "\nDuração: "
					+ filme.getDuracao());
		} else {
			System.out.println("Não existe filme com esse ID!");
		}
	}

	public void filmeNome(Gerenciador g) throws IOException {
		System.out.println("NOME do FILME pesquisado:");
		filme = new Filme();
		filme = g.buscaFilme(retornoCliente.readLine());
		if (filme != null) {
			System.out.println("Id: " + filme.getId() + "\nNome: "
					+ filme.getNome() + "\nGênero: " + filme.getGenero()
					+ "\nAno de Lançamento: " + filme.getAnoLancamento()
					+ "\nDuração: " + filme.getDuracao());
		} else {
			System.out.println("Não existe filme com esse NOME!");
		}
	}

	public void diretoresFilme(Gerenciador g) throws IOException {
		System.out.println("ID do FILME:");
		List<Diretor> diretores;
		filme = new Filme();
		int id = id();
		if (id != 0) {
			filme = g.buscaFilme(id);
			if (filme != null) {
				diretores = g.buscaDiretores(filme);
				if (!diretores.isEmpty())
					for (Diretor d : diretores) {
						System.out.println("Id: " + d.getId() + "\nNome: "
								+ d.getNome());
					}
				else
					System.out.println("Não existe diretores para este livro!");
			} else {
				System.out.println("Não existe filme com esse ID!");
			}
		} else {
			System.out.println("ID invalido!");
		}
	}

	public void exemplaresDispFilme(Gerenciador g) throws IOException {
		System.out.println("NOME do FILME:");
		filme = new Filme();
		filme = g.buscaFilme(retornoCliente.readLine());
		if (filme != null)
			System.out.println("Total de exemplares: "
					+ g.totalExemplares(filme));
		else
			System.out.println("Não existe FILME com esse nome!");
	}

	public void filmeGeneroAno(Gerenciador g) throws IOException {
		System.out.println("Gênero:");
		String genero = retornoCliente.readLine();
		System.out.println("Ano do lançamento:");
		try {
			int ano = Integer.parseInt(retornoCliente.readLine());
			filmes = g.buscaFilmes(genero, ano);
			if (!filmes.isEmpty()) {
				for (Filme f : filmes) {
					System.out.println("Id: " + f.getId() + "\nNome: "
							+ f.getNome() + "\nGênero: " + f.getGenero()
							+ "\nAno de Lançamento: " + f.getAnoLancamento()
							+ "\nDuração: " + f.getDuracao());
				}
			} else {
				System.out
						.println("Não existe filme com para esse gênero e ano!");
			}
		} catch (NumberFormatException e) {
			System.out
					.println("Ano de lancamento incorreto! Voltando ao menu...");
		}
	}

	public void cadastroCliente(Gerenciador g) throws IOException {
		cliente = new Cliente();
		System.out.println("Nome do cliente:");
		String retorno = retornoCliente.readLine();
		cliente.setNome(retorno);
		System.out.println("Data de nascimento:");
		retorno = retornoCliente.readLine();
		cliente.setDataNascimento(retorno);
		System.out.println("E-mail:");
		retorno = retornoCliente.readLine();
		cliente.setEmail(retorno);
		System.out.println("Endereço:");
		retorno = retornoCliente.readLine();
		cliente.setEndereco(retorno);
		g.addCliente(cliente);
		System.out.print("CLIENTE inserido!");
	}

	public void clienteID(Gerenciador g) throws IOException {
		System.out.println("ID do cliente:");
		cliente = new Cliente();
		cliente = g.buscaCliente(id());
		if (cliente != null) {
			System.out.println("Nome: " + cliente.getNome()
					+ "\nData de nascimento: " + cliente.getDataNascimento()
					+ "\nE-mail: " + cliente.getEmail() + "\nEndereço: "
					+ cliente.getEndereco());
		} else {
			System.out.println("Não existe cliente com esse ID!");
		}
	}

	public void clienteNome(Gerenciador g) throws IOException {
		System.out.println("NOME do	CLIENTE:");
		cliente = new Cliente();
		cliente = g.buscaCliente(retornoCliente.readLine());
		if (cliente != null) {
			System.out.println("Id: " + cliente.getId()
					+ "\nData de nascimento: " + cliente.getDataNascimento()
					+ "\nE-mail: " + cliente.getEmail() + "\nEndereço: "
					+ cliente.getEndereco());
		} else {
			System.out.println("Não existe cliente com esse NOME!");
		}
	}

	public String dataAtual() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataAtual = formatador.format(data);
		return dataAtual;
	}

	public void cadastroEmprestimo(Gerenciador g) throws IOException {
		String sair;
		emprestimo = new Emprestimo();
		int valorTotal = 0;
		ItemEmprestimo item;
		List<ItemEmprestimo> itens = new ArrayList<ItemEmprestimo>();

		emprestimo.setDataEmprestimo(dataAtual());
		System.out.println("Endereço para entrega:");
		String enderecoEntrega = retornoCliente.readLine();
		emprestimo.setEnderecoEntrega(enderecoEntrega);

		do {
			System.out.println("Id do filme:");
			int idFilme = id();
			System.out.println("Valor do empréstimo:");
			float valorEmprestimo = Float.parseFloat(retornoCliente.readLine());
			item = g.addItem(idFilme, valorEmprestimo);
			if (item == null) {
				System.out
						.println("Filme não possui exemplares ou estão todos emprestados!");
			} else {
				itens.add(item);
			}
			System.out.println("Escolher mais filmes? S/N");
			sair = retornoCliente.readLine();
			valorTotal += valorEmprestimo;
		} while (sair.equals("n") || sair.equals("N"));
		emprestimo.setValorTotal(valorTotal);

		System.out.println("Id do cliente:");
		cliente = new Cliente();
		cliente = g.buscaCliente(id());
		if (cliente != null) {
			System.out.println("Confirmar o empréstimo? S/N ");
			sair = retornoCliente.readLine();
			if (sair.equals("S") || sair.equals("s")) {
				g.finalizaProcessoLocacao(cliente, emprestimo, itens);
				System.out.print("EMPRÉSTIMO cadastrado!");
			} else {
				System.out.println("Locação cancelada!");
			}
		} else {
			System.out.println("ID de cliente inexistente!");
		}
	}

	public void terminaEmprestimo(Gerenciador g) throws IOException {
		System.out.println("Id do EMPRÉSTIMO:");
		emprestimo = g.buscaEmprestimo(id());
		if (emprestimo != null) {
			g.devolucaoFilmes(emprestimo, dataAtual());
		} else {
			System.out
					.println("Registro de EMPRÉSTIMO inexistente com esse ID!");
		}
	}
}

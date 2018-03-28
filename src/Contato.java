import java.util.ArrayList;
import java.util.Scanner;

public class Contato {
	
	private ArrayList<String> name = new ArrayList();
	private ArrayList<String> nickName = new ArrayList();
	private ArrayList<String> telefone = new ArrayList();
	
	UsoComum u = new UsoComum();
	BDAgenda bd = new BDAgenda();
	Telefone t = new Telefone();
	
	public void incluiNovoContato(){
		String name;
		String nickName;
		String telefone;
		
		bd.setName(this.name);
		bd.setNickName(this.nickName);
		t.setTelefone(this.telefone);
		do{
			name = leiaNomeApelido("Informe o nome do novo contato (<ENTER> para desistir):");
			if (!name.trim().isEmpty()){
				if (!bd.gravaNome(name)){
					nickName = leiaNomeApelido("Informe o apelido do novo contato (<ENTER> para deixar em branco):");
					bd.gravaApelido(nickName);
					do{
						t.incluiTelefone(bd.sizeBD()-1);
					}while(u.escolha("\nDeseja incluir outro telefone? ([S]im / [N]ão)",'S','N',' ') != 'N');
				}else{
					System.err.println("Contato já existente na agenda!!!");
					incluiNovoContato();
				}
			}
		}while(u.escolha("\nDeseja incluir outro contato? ([S]im / [N]ão)",'S','N',' ') != 'N');
		this.name = bd.getName();
		this.nickName = bd.getNickName();
		this.telefone = t.getTelefone();
	}
	
	public void pesquisaContato(){
		char parametro;
		String pesquisado;
		String encontrados;
		
		bd.setName(this.name);
		bd.setNickName(this.nickName);
		bd.setTelefone(this.telefone);
		
		do{
			parametro = u.escolha("\nInforme o parâmetro de pesquisa: [N]ome/Apelido / [T]elefone / [S]air",'N','T','S');
			if (parametro == 'N'){
				pesquisado = leiaNomeApelido("Informe parte do nome ou do apelido para pesquisa (<ENTER> para desistir):");
				if (!pesquisado.trim().isEmpty()){
					encontrados = bd.pesquisaNomeApelido(pesquisado);
					if (encontrados.length()-1 > 0){
						if(u.escolha("\nDeseja editar ou excluir alguma informação do(s) contato(s) listado(s)? ([S]im / [N]ão)",'S','N',' ') == 'S'){
							editaContato(encontrados);
						}
					}else{
						System.err.println("Nenhum resultado encontrado!!");
					}
				}
			}else{
				if (parametro == 'T'){
					System.out.println("Implementar pesquisa por telefone");
				}
			}
		}while(u.escolha("\nDeseja realizar outra pesquisa? ([S]im / [N]ão)",'S','N',' ') != 'N');
		
		this.name = bd.getName();
		this.nickName = bd.getNickName();
		this.telefone = t.getTelefone();
	}
	
	public String leiaNomeApelido(String msg){
		String nome = " ";
		Scanner leia = new Scanner(System.in);
		System.out.println(msg);
		try {
			nome = leia.nextLine();
			if (!nome.trim().isEmpty()){
				for (int i=0; i<nome.length();i++){
					if (!(Character.isLetter(nome.charAt(i)) || Character.isDigit(nome.charAt(i)) || (nome.charAt(i) == ' '))){
						throw new Exception(nome.charAt(i)+" << É CARACTER NÃO PERMITIDO.");
					}
				}
			}
		}catch(Exception e){
        	System.err.println("Entrada inválida!! >> "+e.getMessage()+" Informe novamente!!");
        	nome = leiaNomeApelido(msg);
		}
		nome = nome.toUpperCase();
		return nome;
	}
	
	public void editaContato(String encontrados){
		String codContato, alteracao;
		char opcao;

		codContato = u.localizaCodigo("Informe um código de contato que conste no resultado apresentado: <[S]air>",encontrados);

		if (!codContato.equalsIgnoreCase("S")){
			System.out.println("Escolha uma opção para o contato abaixo:");
			bd.listaNomeEncontrado(Integer.parseInt(codContato));
			opcao = opcaoEdicao();
			switch (opcao){
			case 'N':
				alteracao = leiaNomeApelido("Informe novo nome para o contato (<ENTER> para desistir):");
				if (!alteracao.trim().isEmpty()){
					if (bd.alteraNome(Integer.parseInt(codContato), alteracao)){
						System.err.println("Contato já existente na agenda. Alteração não realizada!!!");
					}
				}else{
					System.err.println("Alteração não realizada!!!");
				}
				break;
			case 'A':
				alteracao = leiaNomeApelido("Informe novo apelido para o contato (<ENTER> para desistir):");
				if (!alteracao.trim().isEmpty()){
					bd.alteraApelido(Integer.parseInt(codContato), alteracao);
				}else{
					System.err.println("Alteração não realizada!!!");
				}
				break;
			case 'E':
				if(u.escolha("\nConfirma exclusão? ([S]im / [N]ão)",'S','N','N') == 'S'){
					bd.excluiContato(Integer.parseInt(codContato));
					System.out.println("Exclusão realizada com sucesso!!!");
				}else{
					System.err.println("Exclusão não realizada!!!");
				}
				break;
			case 'S':
				System.out.println("Nenhuma alteração realizada!!!");
				break;
			}
			
		}
	}
	
	private char opcaoEdicao(){
		char opcao='S';
		Scanner leia = new Scanner(System.in);
		System.out.println("\n\n[N] para alteração do Nome");
		System.out.println("[A] para alteração do Apelido");
		System.out.println("[E] para Excluir o contato");
		System.out.println("[S] para Sair");
		try {
			opcao = leia.next().charAt(0);
			opcao = Character.toUpperCase(opcao);
			if (opcao != 'N' && opcao != 'A' && opcao != 'E' && opcao != 'S'){
				throw new Exception("OPÇÃO INEXISTENTE");
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Informe novamente");
        	opcao = opcaoEdicao();
		}
		return opcao;
	}
	
	public ArrayList<String> getName() {
		return this.name;
	}

	public void setName(ArrayList<String> name) {
		this.name = name;
	}

	public ArrayList<String> getNickName() {
		return this.nickName;
	}

	public void setNickName(ArrayList<String> nickName) {
		this.nickName = nickName;
	}

	public ArrayList<String> getTelefone() {
		return this.telefone;
	}

	public void setTelefone(ArrayList<String> telefone) {
		this.telefone = telefone;
	}

}
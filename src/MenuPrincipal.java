import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> name = new ArrayList();
		ArrayList<String> nickName = new ArrayList();
		ArrayList<String> telefone = new ArrayList();
		char itemMenu;
		
		Contato c = new Contato();
		BDAgenda bd = new BDAgenda();
		Telefone t = new Telefone();
		
		System.out.println(">>> Sistema de Gerenciamento de Agenda <<<");
		
		do{
			itemMenu = leiaMenu();
			switch (itemMenu){
			case 'P':
				c.setName(name);
				c.setNickName(nickName);
				c.setTelefone(telefone);
				c.pesquisaContato();
				name = c.getName();
				nickName = c.getNickName();
				telefone = c.getTelefone();
				break;
			case 'I':
				c.setName(name);
				c.setNickName(nickName);
				c.setTelefone(telefone);
				c.incluiNovoContato();
				name = c.getName();
				nickName = c.getNickName();
				telefone = c.getTelefone();
				break;
			case 'L':
				bd.setName(name);
				bd.setNickName(nickName);
				bd.setTelefone(telefone);
				bd.listaAgenda();
				break;
			}
		}while(itemMenu == 'P' || itemMenu == 'I' || itemMenu == 'L');
		
		System.err.println(">>> Sistema Encerrado <<<");
	}
	
	public static char leiaMenu(){
		char itemMenu;
		Scanner leia = new Scanner(System.in);
		System.out.println("\nInforme a opção desejada:");
		System.out.println("[P]esquisar");
		System.out.println("[I]ncluir");
		System.out.println("[L]ista Agenda");
		System.out.println("[S]air");
		try {
			itemMenu = leia.next().charAt(0);
			itemMenu = Character.toUpperCase(itemMenu);
			if (itemMenu != 'P' && itemMenu != 'I' && itemMenu != 'L' && itemMenu != 'S'){
				throw new Exception("OPÇÃO INEXISTENTE");
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Informe novamente");
        	itemMenu = leiaMenu();
		}
		return itemMenu;
	}

}

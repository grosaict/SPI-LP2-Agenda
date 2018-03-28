import java.util.ArrayList;
import java.util.Scanner;

public class Telefone {

	private ArrayList<String> name = new ArrayList();
	private ArrayList<String> telefone = new ArrayList();
	
	UsoComum u = new UsoComum();
	BDAgenda bd = new BDAgenda();
	
	public void incluiTelefone(int indContato){
		String ind;
		String telefone;
		
		bd.setName(this.name);
		bd.setTelefone(this.telefone);

		ind = Integer.toString(indContato);
		
		telefone = leiaTelefone("Informe o(s) telefone(s) para o contato:");
		
		if (!telefone.trim().isEmpty()){
			telefone = ind + ";" + telefone;
			bd.gravaTelefone(telefone);
		}else{
			telefone = " ";
		}
		
		this.name = bd.getName();
		this.telefone = bd.getTelefone();
	}
	
	public String leiaTelefone(String msg){
		String telefone;
		String nroFone;
		char tpFone;
		
		Scanner leia = new Scanner(System.in);
		
		System.out.println(msg);
		tpFone = leiaTipoTelefone();
		
		if (tpFone != 'S'){
			System.out.print("Informe o nro do telefone: ");
        	nroFone = leiaNroTelefone(tpFone);
        	if (!nroFone.trim().isEmpty()){
        		telefone = tpFone + ";" + nroFone;
        	}else{
        		telefone = " ";
    			System.err.println("Cadastro de telefone(s) encerrado!!");
        	}
		}else{
			telefone = " ";
			System.err.println("Cadastro de telefone(s) encerrado!!");
		}
		return telefone;
	}

	public char leiaTipoTelefone(){
		char tpFone;
		Scanner leia = new Scanner(System.in);
		System.out.println("Informe o tipo de telefone: (<S> para sair)");
		System.out.println("[F]ixo - [C]elular - [T]rabalho");
		try {
			tpFone = leia.next().charAt(0);
			tpFone = Character.toUpperCase(tpFone);
			if (tpFone != 'F' && tpFone != 'C' && tpFone != 'T' && tpFone != 'S'){
				throw new Exception("OPÇÃO INEXISTENTE");
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Informe novamente!!");
        	tpFone = leiaTipoTelefone();
		}
		return tpFone;
	}

	public String leiaNroTelefone(char tpFone){
		String nroFone;
		
		Scanner leia = new Scanner(System.in);
		
		try {
			nroFone = leia.nextLine();
			for (int i=0; i<nroFone.length();i++){
				if (!(Character.isDigit(nroFone.charAt(i)) || (nroFone.charAt(i) == '-') || (nroFone.charAt(i) == ')') || (nroFone.charAt(i) == '(') || (nroFone.charAt(i) == ' '))){
					throw new Exception(nroFone.charAt(i)+" << É CARACTER NÃO PERMITIDO");
				}
			}
		}catch(Exception e){
        	System.err.println("Entrada inválida!! >> "+e.getMessage()+". Informe novamente!!");
        	nroFone = leiaNroTelefone(tpFone);
		}
		return nroFone;
	}
	
	public ArrayList<String> getName() {
		return this.name;
	}

	public void setName(ArrayList<String> name) {
		this.name = name;
	}

	public ArrayList<String> getTelefone() {
		return this.telefone;
	}

	public void setTelefone(ArrayList<String> telefone) {
		this.telefone = telefone;
	}
}
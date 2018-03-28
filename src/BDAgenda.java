import java.util.ArrayList;
import java.lang.String;

public class BDAgenda {

	private ArrayList<String> name = new ArrayList();
	private ArrayList<String> nickName = new ArrayList();
	private ArrayList<String> telefone = new ArrayList();
	
	public boolean gravaNome(String name){
		boolean existe=false;
		if (!this.name.isEmpty()){
			for (int i=0;i<this.name.size();i++){
				if (name.equals(this.name.get(i))){
					existe = true;
					i = this.name.size()-1;
				}
			}
		}
		if (!existe){
			this.name.add(name);
		}
		return existe;
	}
	
	public void gravaApelido(String nickName){
		this.nickName.add(nickName);
	}
	
	public void excluiContato(int codContExcl){
		int encontrado, separador;
		
		this.name.remove(codContExcl);
		this.nickName.remove(codContExcl);
		
		for (int i=0;i<this.telefone.size();i++){
			separador = this.telefone.get(i).indexOf(';',0);
			encontrado = Integer.parseInt(this.telefone.get(i).substring(0, separador));
			if (encontrado == codContExcl){
				excluiTelefone(i);
				i--;
			}else{
				if (encontrado > codContExcl){
					atualizaIndiceTelefone(i, encontrado, separador);
				}
			}
		}
	}
	
	public void excluiTelefone(int codTelExcl){
		this.telefone.remove(codTelExcl);
	}
	
	public void atualizaIndiceTelefone(int codTelAtualizado, int codContAnt, int separador){
		String registroAtualizado;
		int codContAtual;
		
		codContAtual = codContAnt - 1;
		registroAtualizado = codContAtual + this.telefone.get(codTelAtualizado).substring(separador,(this.telefone.get(codTelAtualizado).length()));
		this.telefone.set(codTelAtualizado, registroAtualizado);
	}
	
	public boolean alteraNome(int indice, String name){
		boolean existe=false;
		if (!this.name.isEmpty()){
			for (int i=0;i<this.name.size();i++){
				if (name.equals(this.name.get(i))){
					existe = true;
					i = this.name.size()-1;
				}
			}
		}
		if (!existe){
			this.name.set(indice, name);
		}
		return existe;
	}
	
	public void alteraApelido(int indice, String nickName){
		this.nickName.set(indice,nickName);
	}
	
	public void gravaTelefone(String telefone){
		this.telefone.add(telefone);
	}
	
	public String pesquisaNomeApelido(String nomeApelido){
		String encontrados=";";
		
		System.out.println("[cod] Nome (Apelido)\n      Telefones [cod] tipo nro");
		
		for (int i=0;i<this.name.size();i++){
			if (this.name.get(i).contains(nomeApelido)){
				encontrados = encontrados + i + ";";
				listaNomeEncontrado(i);
			}else{
				if(this.nickName.get(i).contains(nomeApelido)){
					encontrados = encontrados + i + ";";
					listaNomeEncontrado(i);
				}
			}
		}
		return encontrados;
	}
	
	public void listaNomeEncontrado(int encontrado){
		System.out.println("["+(encontrado+1)+"] "+this.name.get(encontrado)+"   ("+this.nickName.get(encontrado)+")");
		listaTelefoneContato(encontrado);
	}
	
	public String listaTelefoneContato(int contato){
		String foneEncontrado=";";
		int encontrado, separador1, separador2;
		
		for (int i=0;i<this.telefone.size();i++){
			separador1 = this.telefone.get(i).indexOf(';',0);
			separador2 = this.telefone.get(i).indexOf(';',separador1+1);
			encontrado = Integer.parseInt(this.telefone.get(i).substring(0, separador1));
			if (encontrado == contato){
				foneEncontrado = foneEncontrado + i + ";";
				if(this.telefone.get(i).substring(separador1+1,separador2).equals("F")){
					System.out.println("  ["+(i+1)+"] Fixo     "+this.telefone.get(i).substring(separador2+1,this.telefone.get(i).length()));
				}else{
					if(this.telefone.get(i).substring(separador1+1,separador2).equals("C")){
						System.out.println("  ["+(i+1)+"] Celular  "+this.telefone.get(i).substring(separador2+1,this.telefone.get(i).length()));
					}else{
						System.out.println("  ["+(i+1)+"] Trabalho "+this.telefone.get(i).substring(separador2+1,this.telefone.get(i).length()));
					}
				}
			}
		}
		return foneEncontrado;
	}
	
	public void listaAgenda(){
		if (this.name.isEmpty()){
			System.err.println("Agenda vazia!!!");
		}else{
			System.out.println("[cod] Nome (Apelido)\n      Telefones");
			for (int i=0;i<this.name.size();i++){
				System.out.println("["+(i+1)+"] "+this.name.get(i)+"   ("+this.nickName.get(i)+")");
				listaTelefoneContato(i);
			}
		}
	}
	
	public int sizeBD(){
		return this.name.size();
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
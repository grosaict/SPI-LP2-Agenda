import java.util.Scanner;
import java.lang.String;

public class UsoComum {

	public char escolha(String msg, char opc1, char opc2, char opc3){
		char opcao;
		Scanner leia = new Scanner(System.in);
		System.out.println(msg);
		try {
			opcao = leia.next().charAt(0);
			opcao = Character.toUpperCase(opcao);
			if (opcao != opc1 && opcao != opc2 && opcao != opc3){
				throw new Exception("OPÇÃO INEXISTENTE");
			}
		}catch(Exception e){
        	System.err.println(">> "+e.getMessage()+" << Informe novamente");
        	opcao = escolha(msg, opc1, opc2, opc3);
		}
		return opcao;
	}
	
	public String localizaCodigo(String msg, String encontrados){
		boolean encontrado = false;
		String codSelecionado="S", codAtual=" ";
		int separador1, separador2=0;
		
		Scanner leia = new Scanner(System.in);
		System.out.println(msg);
		
		try {
			codSelecionado = leia.nextLine();
			if (!codSelecionado.equalsIgnoreCase("S")){
				for (int i=0; separador2<(encontrados.length()-1);i++){
					separador1 = encontrados.indexOf(';',i);
					separador2 = encontrados.indexOf(';',separador1+1);
					codAtual = Integer.toString(Integer.parseInt(encontrados.substring(separador1+1, separador2))+1);
					if (codSelecionado.equals(codAtual)){
						codSelecionado = Integer.toString(Integer.parseInt(codAtual)-1);
						separador2 = encontrados.length();
						encontrado = true;
					}
				}
				if (!encontrado){
					throw new Exception("Código não localizado. Informe novamente!!");
				}
			}
		}catch(Exception e){
        	System.err.println(e.getMessage());
        	codSelecionado = localizaCodigo(msg, encontrados);
		}
		return codSelecionado;
	}
}

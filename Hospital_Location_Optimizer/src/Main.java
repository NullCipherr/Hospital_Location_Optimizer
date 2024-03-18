/*
    O código realiza uma implementação da classe Main que lê um arquivo de entrada, cria um objeto FacilityLocationProblem e 
    executa o método executar() para resolver o problema de localização de instalações.
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        // Leitura do arquivo de entrada
        ArrayList<String> lines = new ArrayList<>() ;
        
        // Lê o arquivo grafo1.txt e armazena cada linha em um ArrayList chamado lines
        try (BufferedReader br = new BufferedReader(new FileReader("src/grafo1.txt"))) 
        {
            String line ;
            
            while ((line = br.readLine()) != null) 
            {
                lines.add(line) ;
                
            }
        } 
        catch (FileNotFoundException e) 
        {
            // Se o arquivo não for encontrado, imprime uma mensagem de erro e o stack trace da exceção
            System.out.println("Arquivo de entrada não encontrado ... ") ;
            e.printStackTrace() ;
        }
        
        // Imprime o conteúdo do arquivo lido
        System.out.println("Arquivo.txt : " + lines) ;
        
        // Cria um objeto FacilityLocationProblem com as informações lidas do arquivo
        FacilityLocationProblem flp = new FacilityLocationProblem(lines);
        
        // Executa o algoritmo para resolver o problema da localização de instalações
        flp.executar() ;
    }
}

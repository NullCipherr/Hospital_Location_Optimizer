/*
        O código define uma classe FacilityLocationProblem que implementa um algoritmo para encontrar a 
    melhor localização para um hospital em um grafo, utilizando a excentricidade dos vértices e o algoritmo de coloração de grafos.
    
        O construtor recebe uma lista de strings contendo as linhas lidas do arquivo. Essas linhas contêm as informações do grafo, onde a 
    primeira linha representa o número de vértices no grafo e as demais linhas representam as arestas do grafo, com o primeiro valor sendo 
    o número do vértice e os valores subsequentes sendo os vértices adjacentes e seus pesos.

        O método executar() constrói o grafo a partir das linhas lidas do arquivo e, em seguida, calcula a excentricidade de cada vértice no grafo 
    usando uma busca em profundidade. Em seguida, encontra o vértice com a menor excentricidade, que é escolhido como a melhor localização para 
    o hospital.

        Em seguida, utiliza o algoritmo de coloração de grafos para atribuir uma cor a cada vértice, onde o vértice escolhido como a melhor localização 
    é atribuído a cor 0 e, para cada vértice do grafo, a cor é determinada baseada na cor dos seus vértices adjacentes. Por fim, o método imprime o 
    grafo com as cores atribuídas a cada vértice.
*/

import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.List ;

public class FacilityLocationProblem 
{
    private List<String> lines ;
    
    /**
     * Constrói um problema de localização de instalações a partir das linhas lidas do arquivo.
     * @param lines : Lista de strings contendo as linhas lidas do arquivo.
    */
    public FacilityLocationProblem(ArrayList<String> li)
    {
        // Verifica se a lista de linhas 'li' não é nula antes de atribuí-la ao membro da classe 'lines'.
        if (li == null) 
        {
            throw new IllegalArgumentException("A lista de linhas não pode ser nula !! ") ;
        }
        else
        {
            this.lines = li ;
        }
    }
    
    
    
    // O método executar() implementa um algoritmo para encontrar a melhor localização para um hospital em um grafo, utilizando a excentricidade dos vértices e o algoritmo de coloração de grafos.
    public void executar() 
    {

        // Construção do grafo a partir das linhas lidas do arquivo
        // O número de linhas do arquivo representa o número de vértices no grafo
        int n = lines.size() ;
        
        // Criando uma matriz de adjacência para representar o grafo
        int[][] graph = new int[n][n] ;
        
        // Preenchendo a matriz de adjacência com valores iniciais de infinito, exceto na diagonal principal que é 0
        for (int i = 0; i < n; i++) 
        {
            Arrays.fill(graph[i], Integer.MAX_VALUE) ;
            graph[i][i] = 0 ;
        }
        
        // Exibindo o grafo construído
        System.out.println ("\n") ;
        desenharGrafo(graph) ;
        
        // Adicionando as arestas ao grafo
        // A primeira linha do arquivo não é uma aresta, por isso começa em 1
        for (int i = 1; i < n; i++) 
        {
            // Cada linha do arquivo contém a lista de adjacência de um vértice
            String[] parts = lines.get(i).split(" ") ;
            
            // O índice do vértice é i-1, já que a primeira linha do arquivo foi ignorada         
            int u = i - 1 ;
            
            // Adicionando todas as arestas do vértice atual
            for (int j = 0; j < parts.length; j += 2) 
            {
                // As arestas são representadas por um par (vértice, peso)
                if (j + 1 < parts.length) 
                {
                    int v = Integer.parseInt(parts[j]) ;
                    int w = Integer.parseInt(parts[j + 1]) ;
                    graph[u][v] = w ;
                    graph[v][u] = w ;
                }
            }
        }

        // Cálculo da excentricidade de cada vértice
        // Para cada vértice no grafo, calcula-se sua excentricidade
        int[] excentricity = new int[n] ;
        
        for (int i = 0; i < n; i++) 
        {
            // Inicializa a excentricidade com o menor valor possível, já que queremos encontrar o máximo
            excentricity[i] = Integer.MIN_VALUE ;
            
            // Inicializa um vetor de visitados para a busca em profundidade
            boolean[] visited = new boolean[n] ;
            
            // Realiza uma busca em profundidade a partir do vértice atual para calcular a excentricidade
            dfs(graph, i, 0, excentricity, visited) ;
        }

        // Encontra o vértice com a menor excentricidade, que será escolhido como a melhor localização para o hospital
        int bestVertex = -1 ;
        int bestExcentricity = Integer.MAX_VALUE ;
        
        for (int i = 0; i < n; i++) 
        {
            if (excentricity[i] < bestExcentricity) 
            {
                bestVertex = i ;
                bestExcentricity = excentricity[i] ;
            }
        }

        // Coloração do grafo
        // Utiliza o algoritmo de coloração de grafos para atribuir uma cor a cada vértice
        
        // Cria um vetor color[] que armazena a cor de cada vértice
        int[] color = new int[n] ;
        
        // Inicialmente todos os vértices são atribuídos com a cor -1
        Arrays.fill(color, -1) ;
        
        // O vértice escolhido como melhor localização é atribuído a cor 0
        color[bestVertex] = 0 ;
        
        // Para cada vértice do grafo, a cor é determinada baseada na cor dos seus vértices adjacentes
        for (int i = 0; i < n; i++) 
        {
            // Se o vértice ainda não foi colorido, calcula-se a sua cor
            if (color[i] == -1) 
            {
                // Cria um vetor usedColors[] que armazena as cores utilizadas pelos vértices adjacentes a i
                boolean[] usedColors = new boolean[n] ;
                
                // Percorre os vértices adjacentes a i e marca as cores já utilizadas
                for (int j = 0; j < n; j++) 
                {
                    // Se o vértice i é adjacente ao vértice j, e j já foi colorido, marca a cor de j como utilizada
                    if (graph[i][j] != Integer.MAX_VALUE && color[j] != -1) 
                    {
                        usedColors[color[j]] = true ;
                    }
                }
                
                // Define a cor do vértice i como a primeira cor não utilizada pelos vértices adjacentes a i
                int j = 0 ;
                
                while (usedColors[j]) 
                {
                    j++ ;
                }
                
                color[i] = j ;
            }
        }

        // Coloração do grafo
        int[] colors = coloracaoGulosa(graph) ;

        // Exibindo a coloração do grafo
        System.out.println("\nColoração do grafo: \n") ;
        for (int i = 0; i < n; i++) 
        {
            System.out.println(i + ": " + colors[i]) ;
        }

        // Exibir a melhor localização para o hospital
        System.out.println("\nA melhor localização para o hospital é o vértice " + bestVertex + ".\n") ;
        
    }

    
    
    /**
        O método Depth-first search(DFS) realiza uma busca de profundidade no grafo a partir de um vértice inicial, atualizando a excentricidade de cada vértice visitado.
        
        * @param graph : Matriz de adjacência do grafo.
        * @param u : Vértice inicial de busca.
        * @param dist : Distância percorrida até o momento na busca.
        * @param excentricity : Vetor de excentricidades dos vértices do grafo.
        * @param visited : Vetor de booleanos que indica se um vértice já foi visitado ou não.
        * @return : O método não possui retorno explícito, mas atualiza o vetor de excentricidades.
    */
    private void dfs(int[][] graph, int u, int dist, int[] excentricity, boolean[] visited) 
    {
        // Marca o vértice u como visitado
        visited[u] = true ;
        
        // Atualiza a excentricidade do vértice u com a distância percorrida até ele
        excentricity[u] = Math.max(excentricity[u], dist) ;
        
        // Para cada vértice adjacente a u, verifica se já foi visitado e se existe aresta entre u e v
        for (int v = 0; v < graph.length; v++) 
        {
            // Se v ainda não foi visitado e existe aresta entre u e v
            if (graph[u][v] != Integer.MAX_VALUE && !visited[v]) 
            {
                // Chama recursivamente o método dfs para visitar v, adicionando a distância percorrida até v à distância acumulada
                dfs(graph, v, dist + graph[u][v], excentricity, visited) ;
            }
        }
    }

    
    
    /** 
     * O método desenharGrafo() desenha uma matriz de adjacência representando um grafo.
     * @param graph : Grafo a ser representado.
    */
    public void desenharGrafo(int[][] graph) 
    {
        // Obtém o número de vértices do grafo
        int n = graph.length ;

        // Imprime os rótulos das colunas
        System.out.print("  ") ;
        
        // Percorre as colunas e imprime os rótulos
        for (int i = 0; i < n; i++) 
        {
            // Imprime o índice da coluna
            System.out.printf("%2d ", i) ;
        }
        
        // Pula uma linha
        System.out.println() ;

        // Imprime uma linha separadora
        System.out.print("  ") ;
        
        // Percorre as colunas e imprime os traços para separar as colunas
        for (int i = 0; i < n; i++) 
        {
            System.out.print("---") ;
        }
        
        // Pula uma linha
        System.out.println() ;

        // Imprime a matriz de adjacência
        for (int i = 0; i < n; i++) 
        {
            // Imprime o índice da linha seguido de "|"
            System.out.printf("%2d|", i) ;
            
            // Percorre as colunas e imprime o valor da matriz
            for (int j = 0; j < n; j++) 
            {
                // Verifica se não há conexão
                if (graph[i][j] == Integer.MAX_VALUE) 
                {
                    // Imprime um traço para indicar a ausência de conexão
                    System.out.print("  -") ;
                }
                else // Há conexão
                {
                    // Imprime o valor da conexão
                    System.out.printf("%3d", graph[i][j]) ;
                }
            }
            
            // Pula uma linha para a próxima linha da matriz
            System.out.println() ;
        }
    }
    
    /** 
     * Implementação do algoritmo de coloração gulosa
     * @param graph : Matriz de adjacência para determinar quais vértices estão conectados e, portanto, precisam ter cores diferentes.
     * @return : Array com as cores atribuídas a cada vértice.
    */
    private int[] coloracaoGulosa(int[][] graph) 
    {
        // Obtém o número de vértices do grafo
        int n = graph.length ;
        
        // Cria um array para armazenar as cores atribuídas a cada vértice e o preenche com -1 para indicar que ainda não foi atribuída nenhuma cor
        int[] colors = new int[n] ;
        Arrays.fill(colors, -1) ;
        
        // Para cada vértice do grafo, obtém os seus vértices adjacentes e atribui a menor cor disponível
        for (int i = 0; i < n; i++) 
        {
            // Cria um array para armazenar as cores já utilizadas pelos vértices adjacentes
            boolean[] usedColors = new boolean[n] ;
            
            // Para cada vértice adjacente, verifica se já foi atribuída alguma cor e armazena no array usedColors
            for (int j = 0; j < n; j++) 
            {
                // Se houver uma aresta entre os vértices i e j e o vértice j já tiver sido colorido, armazena a cor utilizada no array usedColors
                if (graph[i][j] != Integer.MAX_VALUE && colors[j] != -1) 
                {
                    usedColors[colors[j]] = true ;
                }
            }

            // Atribui a menor cor disponível ao vértice i
            int color ;      
            for (color = 0; color < n; color++) 
            {
                // Verifica se a cor atual já foi utilizada pelos vértices adjacentes
                if (!usedColors[color]) 
                {
                    break ;
                }
            }
            
            // Atribui a cor escolhida ao vértice i
            colors[i] = color ;
        }
        
        // Retorna o array com as cores atribuídas a cada vértice
        return colors ;
    }
}


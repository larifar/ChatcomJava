# O que é
- Test Driven Development : tradução Desenvolvimento Orientado a Testes
- Um dos pilares de XP
- Pequenos ciclos de repetição: primeiro cria-se teste, depois funcionalidade, quando passa refatora
- Código limpo, coeso e menos acoplado
# Ciclo de Desenvolvimento
- Red -> Green -> Refactor
	- Escreve-se teste que falha
	- Adiciona funcionalidade
	- Faz teste passar
	- Refatora o código da nova funcionalidade
	- Escreve próximo teste
# Vantagens
- Feedback rápido
- Mais segurança
- Código mais limpo, simples e funcional
- Maior produtividade
- Código em pedaços -> aplicação flexível
- Evita-se bugs -> menor tempo de desenvolvimento
- Integração contínua
- Documentação-> Suíte de Testes
# Por onde começar:
- Entender conceitos antes da prática.
- Bottom-up: entender o que deve ser feito antes de como deve ser feito. 
- Cultivando flow: não perca tempo escrevendo descrições detalhadas. Escreva o teste. Usar => pelos menos 2 de Given/When/Then
- Automatizar testes: sempre rodar todos testes, priorizar testes unitários. Mas praticar os 3: unitários, integração, ponta a ponta
- Usar asserts: básico de testes unitários -> evita-se falsos true/false
# Test-Driven Development by Kent Back

## Parte 1:
- Princípios:
	- Escreva um teste automatizado que falhe antes de escrever qualquer código
	- Remova duplicação
- Ritmo:
	1. Adicionar um teste rapidamente. 
	2. Rodar todos os testes e ver o mais novo falhando. 
	3. Fazer uma pequena mudança. 
	4. Rodar todos os testes e ver todos funcionando. 
	5. Refatorar para remover duplicações.
- Surpresas:
  - Como cada teste pode cobrir um pequeno aumento de funcionalidade 
  - Quão pequenas e feias as mudanças podem ser para fazer os novos testes 
  - Com frequência os testes são executados 
  - De quantos pequeninos passos as refatorações são compostas
- Qual conjunto de testes, quando passarem, demonstrará a presença de código que estamos confiantes que irá calcular o relatório correta mente?
- Falha é progresso. Agora temos uma medida concreta da falha. Isso é melhor que apenas saber vagamente que estamos falhando.
- Passos pequenos, até achar o passo certo.
- Primeiro teste:
  - Fizemos uma lista dos testes que sabíamos que precisávamos ter funcionando 
  - Contamos uma história com um trecho de código sobre como queríamos 
  - Ignoramos os detalhes do JUnit por enquanto. 
  - Fizemos o teste compilar com stubs. 
  - Fizemos o teste rodar, cometendo pecados horríveis. 
  - Gradualmente, generalizamos o código que está funcionando, substituindo constantes por variáveis 
  - Adicionamos itens à nossa lista de tarefas em vez de resolvê-los todos de uma vez.
- **O objetivo é código limpo que funciona.** Dividir e conquistar. Primeiro resolveremos a parte do “que funciona” do problema. Então resolveremos a parte do “código limpo”.
- Triangulação: generalização do código quando >2 exemplos. Usar quando não ter ideia de como fazer. Variação de respostas (asserts) -> encontra generalização
- Os passos pequeninos estão parecendo restritivos? Dê passos maiores. Está se sentindo um pouco inseguro? Dê passos menores.
## Padrões:
- Teste Isolado: execução de teste não deve afetar outro teste. São independentes de uma ordem.
- Lista de Testes: antes de começar escrever uma lista de todos teste que sabe irá precisar desenvolver.
  1. Exemplos de cada operação que precisa ser implementada
  2. Depois para aquelas operações que já não existem, coloque a versão nula daquela operação na lista
  3. Liste todas refatorações que acha que deve fazer para limpar o código no final da sessão
	 Conforme você faz os testes rodarem, a implementação implicará em novos
	 testes. Escreva os testes no final da lista. Da mesma forma com refatorações.
- Teste primeiro.
- Assert first: qual a resposta certa e como vou verificar? Primeiro escreva a resposta que deve devolver e pense na funcionalidade depois.
- Test Data: use dados que façam teste fáceis de ler e seguir.
- Evident Data: escreva para um leitor não apenas para um computador. Inclua resultados esperados e reais no próprio teste e tente fazer seu relacionamento evidente.
### Padrões de Barra Vermelha
- One Step Test: pegue um teste da lista que irá te ensinar algo e que você confia que consiga implementar.
- Starter Test: one step test, um teste que você aprende algo mas que irá funcionar rapidamente.
- Learning Test: ao usar uma biblioteca externa/código de terceiro pela primeira vez, garanta com um teste para entender como ele funciona
- Another Test: transforme ideias tangenciais à tarefa atual em um teste a ser feito depois na lista de testes.
- Regression Test: um bug aconteceu, algo ficou mal projetado ou não foi testado, pense como eu poderia ter detectado isso antes e escreva o teste para capturar esse erro.
- Do Over: não tenha medo de apagar e começar do zero
### Padrões de Teste
- Child Test: dividir teste grande em pequenos casos
- Mock Object: usar mock para gerar desacoplamento de código, gerando um comportamento esperado, que seja rápido, prevísivel e legível
- Self Shunt: própria classe de teste implementa a interface usada no teste, permitindo verificar interações de forma clara e local. Interfaces devem ser minímas e específicas
- Crash Test Dummy: testar comportamentos difíceis de reproduzir pode ser feito usando objetos simulados que provocam o erro intencionamente.
- Broken Test: quando for trabalhar sozinho, deixe um teste quebrado ao terminar, para conseguir retomar raciocínio mais rapidamente.
### Padrões de Barra Verde
- Fake it til you make it: retorne uma constante, para criar base para implementação, gradualmente substitua por lógicas mais complexas a medida que avança.
- Triangulate: crie múltiplos exemplos antes de abstrair a implementação.
- Obvous Implementation: se souber claramente como fazer operação simples implemente, mas esteja preparado para refatorar quando necessário.
- One To Many: comece implementando operação simples e depois expanda gradualmente para coleções
### Padrões xUnit
- Assertion: faça asserções claras que envolvam booleans
- Fixture: pode-se usar setUp() dentro da classe de teste, mas tem o tradeoff de leitor tem que lembrar métodos e objetos.
- Fixture externa: uso tearDown() para limpeza aconteça independente de falhas
- Test Method: nomes de testes devem ter claramente o que aquele teste faz. Os métodos devem ser curtos e diretos : baby steps
- Exception Test: verificar se quando um teste de exceção a exceção não for lançada ele falha.
- All Tests: sempre rodar todos os testes
### Padrões de Projeto
- Command: invocar de operação mais flexível e manipulável usando objetos para representar uma invocação de opração ao invés de invocar diretamete o método
- Value Objects: objeto imutável, evitando problema de inconsistência em referências de objetos
- Null Object: elimina verificações de null, objeto de caso especial que segue mesmo protocolo de um objeto mas não faz ações significativas.
- Template Method: define um template na classe base que permite que subclasses implementem os detalhes, mas sigam o esqueleto
- Pluggable Object: encapsulamento de variações de comportamento em objetos pluggable -> modularização
- Pluggable Selector: subatitui herança rígida ou condicionais por objetos pluggable. Armazenando comportamento em objetos separados que podem ser injetados dinamicamente.
- Factory Method: desacoplamento e flexibilidade
- Imposter: útil em testes em diferentes cenários e precisa substituir objeto pro outro com implemntação similar
- Composite: tratar objetos individuais e composições de objetos de forma uniforme
- Collecting Parameter: agrupar ou acumular resultados de uma operação que ocorre em múltiplos objetos, como num Composite
### Refatoração
- Reconcile Differences: não force união de códigos parecidos, gradualmente faça eles serem iaguais e ai sim junte
- Isolate Change: isole a parte do código que precisa mudar
- Migrate Data: para mudar representação de dados com segurança, duplique temporariamente os dados, só depois de tudo já ter sido migrado que remove o antigo
- Extract Method: A ideia é pegar uma parte lógica de um método maior e transformá-la em um método separado com um nome descritivo
- Inline Method: Consiste em substituir a chamada de um método por seu conteúdo, quando o método for simples ou desnecessário.
- Extract Interface: extrair uma interface das operações comuns de uma classe concreta, permitindo maior flexibilidade e desacoplamento do código.
- Move Method: mover o método para a classe que representa melhor o comportamento que ele realiza, e adaptar os parâmetros caso o método precise interagir com outras partes do código.
- Method Object: simplificar métodos que são longos e complexos, com muitos parâmetros e variáveis locais. Ao transformá-lo em um objeto, você pode organizar melhor as variáveis e a lógica.
- Add Parameter: adicionar um parâmetro permite que o método lide com mais informações
- Method Parameter to Constructor Parameter: Em vez de passar o parâmetro repetidamente, você o passa uma única vez para o construtor e a classe mantém esse valor como uma variável de instância.
##### Livros recomendados:
- Test-Driven Development: By Example. By Kent Beck.
- Growing Object-Oriented Software Guided by Tests. By Steve Freeman and Nat Pryce.

- Links utilizados:
	- https://www.devmedia.com.br/test-driven-development-tdd-simples-e-pratico/18533
	- https://sidhartarezende.medium.com/2-dicas-para-começar-a-usar-tdd-86db358dfcab
	- https://dev-to.translate.goog/marciofrayze/how-i-started-practicing-tdd-aj5?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
    - https://github.com/PauloGoncalvesBH/aprenda-tdd-na-pratica
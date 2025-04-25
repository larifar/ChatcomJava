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
	• Como cada teste pode cobrir um pequeno aumento de funcionalidade 
	• Quão pequenas e feias as mudanças podem ser para fazer os novos testes 
	• Com frequência os testes são executados 
	• De quantos pequeninos passos as refatorações são compostas
- Qual conjunto de testes, quando passarem, demonstrará a presença de código que estamos confiantes que irá calcular o relatório correta mente?
- Falha é progresso. Agora temos uma medida concreta da falha. Isso é melhor que apenas saber vagamente que estamos falhando.
- Passos pequenos, até achar o passo certo.
- Primeiro teste:
	• Fizemos uma lista dos testes que sabíamos que precisávamos ter funcionando 
	• Contamos uma história com um trecho de código sobre como queríamos 
	• Ignoramos os detalhes do JUnit por enquanto. 
	• Fizemos o teste compilar com stubs. 
	• Fizemos o teste rodar, cometendo pecados horríveis. 
	• Gradualmente, generalizamos o código que está funcionando, substituindo constantes por variáveis 
	• Adicionamos itens à nossa lista de tarefas em vez de resolvê-los todos de uma vez.
- **O objetivo é código limpo que funciona.** Dividir e conquistar. Primeiro resolveremos a parte do “que funciona” do problema. Então resolveremos a parte do “código limpo”.
- Triangulação: generalização do código quando >2 exemplos. Usar quando não ter ideia de como fazer. Variação de respostas (asserts) -> encontra generalização
- Os passos pequeninos estão parecendo restritivos? Dê passos maiores. Está se sentindo um pouco inseguro? Dê passos menores.
##### Livros recomendados:
Test-Driven Development: By Example. By Kent Beck.
Growing Object-Oriented Software Guided by Tests. By Steve Freeman and Nat Pryce.

- Links
	- https://www.devmedia.com.br/test-driven-development-tdd-simples-e-pratico/18533
	- https://sidhartarezende.medium.com/2-dicas-para-começar-a-usar-tdd-86db358dfcab
	- https://dev-to.translate.goog/marciofrayze/how-i-started-practicing-tdd-aj5?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
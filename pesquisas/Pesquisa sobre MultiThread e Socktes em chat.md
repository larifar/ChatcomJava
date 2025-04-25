## Multithread
- Thread é um fluxo de controle sequencial
- Processamento assíncrono
## Socket
- Meio de comunicação: conexão cliente/servidor -> cliente informa endereço de IP e porta de servidor
### Princípios OOP
- Herança, Encapsulamento, Polimorfismo e Abstração
- Classes: Server e Cliente - Responsabilidades e comportamentos:
	-  de Server: unidade centralizadora de conexões via socket e envia mensagens para os demais do servidor. Quando cliente se conecta a o servidor, cria uma thread para aquele cliente. Cada cliente tem sua respectiva Thread e Servidor faz gestão. Extends Thread.
	- de Cliente: cada usuário cria instância de Cliente e faz conexão com servidor. Cliente informa endereço do server e porta. Server deve ser executado antes.

##### Links
- https://www.devmedia.com.br/como-criar-um-chat-multithread-com-socket-em-java/33639
## Multithread
- Thread é um fluxo de controle sequencial
- Processamento assíncrono
- Processo: programa em execução. 
- Thread: parte de um processo que executam de forma paralela.
- Todo programa possui pelo menos uma Thread Principal. Thread principal pode chamar quantas threads forem necessárias.
-  Implementação em Java:
	- Classe Thread: deve ser filha de Thread (extends) que possui método run(). O método run é executado em outra thread.
	- Para executar um thread deve criar objeto filho Thread e chamar método start()
	![[]](./imgs/ThreadParalelo.png)
	![[]](./imgs/ThreadPrincipal.png)
	
## Socket
- Meio de comunicação: conexão cliente/servidor -> cliente informa endereço de IP e porta de servidor
- Interface de comunicação entre camada de aplicação e de transporte: permite comunicação entre 2 processos entre máquinas diferentes.
- Conceitos:
	- Processo: programa sendo executado por máquina
	- Endereço IP: número identificador de dispositivo conectado à Internet. IPv4 = 32 bits e IPv6 =128bits.
	- Porta: identifica um socket em uma máquina. Cada socket escuta uma porta no computador. de 16 bits. Portas reservadas: 0 a 1023 (TCP 80-> HTTP), (TCP 21-> FTP). De 1024 a 65535 disponíveis.
	- Para enviar mensagem: endereço IP + porta máquina origem e destino
- Sockets formado por par: Servidor e Cliente.
	- Servidor: espera conexão em uma porta(escuta);
	- Cliente: inicia a comunicação.
- TIPOS:
	- TCP: transporte confiável e cria uma conexão entre processos. Precisa estabelecer conexão antes de enviar pacotes, confiável porque recebe confirmação que chegou ao destino.
	- UDP: transporte por melhor esforço e sem conexão. Basta preparar pacote e enviar. Pode ser perdido.
- Java:
	- UDPSocket:
		- no pacote java.net:
			- DatagramSocket: representa socket UDP, objetos que enviam e recebem mensagens; Um objeto de cliente e outro no servidor.
			- DatagramPacket: representa mensagem que é enviada.
		- UDPServer e UDPCliente.
		![[]](./imgs/UDPClient.png)
		![[]](./imgs/UDPServer.png)
		- Todo socket deve ser fechado depois.
	- SocketTCP:
		- Antes de iniciar comunicação, servidor tem que estar escutando porta para cliente pedir conexão;
		- no pacote java.net:
			- ServerSocket: objeto servidor, ouve e espera conexão. Cria objeto Socket quando aceita conexão.
			- Socket: define objetos que formarão link. Cliente cria objeto socket para poder se comunicar.
		![[]](./imgs/TCPServer.png)
		![[]](./imgs/TCPClient.png)
### Princípios OOP
- Herança, Encapsulamento, Polimorfismo e Abstração
- Classes: Server e Cliente - Responsabilidades e comportamentos:
	-  de Server: unidade centralizadora de conexões via socket e envia mensagens para os demais do servidor. Quando cliente se conecta a o servidor, cria uma thread para aquele cliente. Cada cliente tem sua respectiva Thread e Servidor faz gestão. Extends Thread.
	- de Cliente: cada usuário cria instância de Cliente e faz conexão com servidor. Cliente informa endereço do server e porta. Server deve ser executado antes.
## Multithread com Socket
- Uma thread envia mensagem enquanto outra escuta.
- Chat:
	- Formado por 3 classes:
	- Servidor: possui o serverSocket
	- Cliente: possui o cliente socket
	- EscutaThread: thread que escuta mensagens e exibe na tela
	![[]](./imgs/Servidor.png)
	![[]](./imgs/EscutaThread.png)
	
##### Links
- https://www.devmedia.com.br/como-criar-um-chat-multithread-com-socket-em-java/33639
- https://www.youtube.com/watch?v=sNkqe9Bzwcc&list=PLWh_9yD4xjakBMsK7z4zUlp4fMz0SbNUg&index=6
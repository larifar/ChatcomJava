# Executor
- API do JDK que simplifica execução de tarefas assíncronas
## Instanciando
- ExecutorService executor = Executors.newFixedThreadPool(n);
- ExecutorService executor = Executors.newCachedThreadPool();
- ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue< Runnable>()); // construtor da classe ThreadPoolExecutor
## Atribuindo tarefas
- Executa tarefas Runnable e Callable
- Runnable(não tem retorno):
```
		Runnable runnableTask = () -> { 
		try { 
			TimeUnit.MILLISECONDS.sleep(300); 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 
		};
```
- Callable(tem retorno):
```
Callable<String> callableTask = () -> { 
	TimeUnit.MILLISECONDS.sleep(300); 
	return "Task's execution"; 
};
```
- Para executar tem métodos:
	- execute(runnableTask); -> vazio
	- submit(callableTask); -> retorna um resultado do tipo Future
		- Future< String> future = executor.submit(callableTask);
	- invokeAny(tasks); -> um resultado;
		- executa uma coleção de tarefas e retorna o resultado de uma (se houver uma bem sucedida)
		- String result = executor.invokeAny(callableTasks);
	- invokeAll(tasks); -> resultados do tipo Future
		- executa uma coleção de tarefas e retorna coleção de resultados do tipo Future
## Parar um ExecutorService
- Não é destruído automaticamente, permanece ativo esperando tarefas
- método shutdown() -> para de aceitar novas tarefas e espera threads concluirem para encerrar
- método shutdownNow() -> tenta acabar executor imediatamente e retorna lista de threads inacabadas.
- Boa prática - Recomendação Oracle usar com método awaitTermination():
```
	executorService.shutdown(); 
	try { 
		if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) { 
			executorService.shutdownNow(); }
		} catch (InterruptedException e) 
			 { executorService.shutdownNow(); }
```
	para de aceitar novas tarefas e aguarda tempo para tarefas sejam concluídas
## Interface Future
- Retornada dos métodos submit() e invokeAll()
- Permite obter resultado de tarefa ou verificar status dela
- método get() -> bloqueio especial, retorna resultado; Thread bloqueada até que a tarefa tenha retorno
	- String result = future.get(200, TimeUnit.MILLISECONDS); -> usar tempo limite que após expirado e resultado não retornado lança exceção TimeoutException
- método isDone() -> verifica status da tarefa
- método cancel() -> cancelar execução de tarefa
- método isCancelled() -> verifica status de cancelar
## Interface ScheduleExecutorService
- Executa tarefas após atraso definido e/ou periodicamente
- ExecutorService executor = Executors.newScheduledThreadPool();
- método schedule(runnableOrCallable, delay, tipoUnidadeTempo);
	- executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
	- esse método executa a tarefa depois do delay de 1 segundo
- método scheduleAtFixedRate(runnableOrCallable, delay, rate, tipoUnidadeTempo)
	- executorService.scheduleAtFixedRate(runnableTask, 1, 3, TimeUnit.SECONDS);
	- executa a tarefa depois atraso inicial de 1 segundo e continua executando a cada 3 segundos
	- Se tempo de executar a task for maior que o rate especificado o executor esperará até que a tarefa seja concluída antes de iniciar a próxima
- método scheduleWithFixedDelay(runnableOrCallable, delay, fixed, tipoUnidadeTempo)
	- parecido com o scheduleAtFixedRate(), mas caso o tempo da task for maior, ele ainda sim espera mais o delay fixed. É como se somasse o tempo de execução + delay.
# LinkedBlockingQueue
- Resolver problema de produtor-consumidor simultâneo com acesso concorrente a mesma fila
- _BlockingQueue_ pode ser compartilhado entre threads sem nenhuma sincronização explícita.
## Fila ilimitada
- BlockingQueue< String> blockingQueue = new LinkedBlockingDeque<>();
- Capacidade definida como Integer.MAX_VALUE. Importante que consumidor consuma mesma velocidade que produtores para evitar memória cheia e exceção OutOfMemory
## Fila limitada
- BlockingQueue< String> blockingQueue = new LinkedBlockingDeque<>(capacidade);
- Caso fila cheia-> bloqueiam até que haja espaço na fila. Maior controle de fluxo
## Adicionando elementos à fila
- add() - retorna true se inserção for bem-sucedida, senão lança IllegalStateException
- put() - insere aguardando slote livre na fila
- offer() - retorna true se inserção bem sucedida, senão false
- offer(E e, long timeout, TimeUnit unit) -> tenta inserir dentro de tempo limite especificado
## Recuperando elementos da fila
- take() - aguarda elemento da fila e o remove
- poll(long timout, TimeUnit unit) - recupera e remove do início da fila agurada o tempo de espera. Retorna null depois do tempo.

### Links
- https://www.baeldung.com/java-executor-service-tutorial
- https://www.baeldung.com/java-blocking-queue
# Atendimento ao desafio técnico proposto pela SouthSystem
## Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:

- Cadastrar uma nova pauta;

    - O cadastro de pauta para efeito de teste, e rodando localmente estará disponivel no endereço http://localhost:8080/pauta com o método POST. Deverá estar acompanhado do seguinte JSON para possibilitar o cadastro.

        {
	        "assunto": "Teste de votação"
        }

    - Para consultar uma pauta específica, utilize o endereço: http://localhost:8080/pauta/{id} , com o método GET , onde o{id} é o identificador da pauta que deseja buscar.

    - Para consultar todas as pautas, utilize também com o método GET, o endereço: http://localhost:8080/pauta

- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);

    - Para abrir uma pauta existente, utilize o endereço: http://localhost:8080/pauta/abrir (metodo POST). Deverá estar acompanhado do JSON abaixo para possibilitar o cadastro.

        {
            "id": "1",
            "tempo": "20"
        }

- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);

    - Para votar, utilize o seguinte endereço: http://localhost:8080/voto, com o método POST, e as seguintes informações via JSON

        {
            "cpf": "07069566050",
            "idPauta": "1",
            "opcao": "1" Obs.: para este caso, 1 = Não e 2 = Sim
        }

- Contabilizar os votos e dar o resultado da votação na pauta.

    - Esta opção é disponibilizda no endereço: http://localhost:8080/pauta/enviar/{id} , com o método POST, onde o{id} é o identificador da pauta que deseja buscar.


- É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.
    - A aplicação permite trabalhar com dois bancos de dados: Um em memória para testes e um banco de dados persistente. Para este caso, foi instalado o MySql, mantendo suas portas default. Para alternar entre os bancos de dados, basta trocar o parâmetro spring.profiles.active de test para dev.

### Tarefas bônus

#### Tarefa Bônus 1 - Integração com sistemas externos
Integrar com um sistema que verifique, a partir do CPF do associado, se ele pode votar
- GET https://user-info.herokuapp.com/users/{cpf}
- Caso o CPF seja inválido, a API retornará o HTTP Status 404 (Not found). Você pode usar geradores de CPF para gerar CPFs válidos;
- Caso o CPF seja válido, a API retornará se o usuário pode (ABLE_TO_VOTE) ou não pode (UNABLE_TO_VOTE) executar a operação

    - A Serviço que contabiliza os votos, implementa esta funcionalidade.

#### Tarefa Bônus 2 - Mensageria e filas
Classificação da informação: Uso Interno
O resultado da votação precisa ser informado para o restante da plataforma, isso deve ser feito preferencialmente através de mensageria. Quando a sessão de votação fechar, poste uma mensagem com o resultado da votação.

    - Para atender esta demanda, foi utilizado o ActiveMQ e para testar o serviço, o mesmo deve ser instalado. Caso não seja feito, apenas não haverá notificação. Mais detalhes da instalação, podem ser visto no endereço https://mmarcosab.medium.com/mensageria-responsa-com-jms-e-active-mq-f1cb6fd6dc35

    OBS.: Onde é criada a fila no exemplo de instalação, utilize como nome fila "fila.voto".

#### Tarefa Bônus 3 - Performance
Imagine que sua aplicação possa ser usada em cenários que existam centenas de milhares de votos. Ela deve se comportar de maneira performática nesses cenários;
- Testes de performance são uma boa maneira de garantir e observar como sua aplicação se comporta.

    - Para o atendimento deste requisito, será necessário que a aplicação spring-boot-admin e Actuator(disponibilizada em: https://github.com/hebertrocha/spring-boot-admin) esteja rodando em paralelo com a aplicação. Ela dá um perfil completo sobre a saúde da aplicação em um
    dashbord. Com as duas aplicações rodando, basta acessar o endereço: http://localhost:8081/ para que a aplicação mostre o dashbord


#### Tarefa Bônus 4 - Versionamento da API
Como você versionaria a API da sua aplicação? Que estratégia usar?

Eu utilizaria a abordagem de versionamento no PATH, onde a versão é especificada após a base URL. Esta abordagem é a mais utilizada e considerada dev-friendly.

#### Tarefa Bônus 5 - Documentação da API
A API foi documentada com o Swagger. Para utilizar, basta acessar o endereço base da aplicação (no caso http://localhost:8080/) a página swagger-ui.html.
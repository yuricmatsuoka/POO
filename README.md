### Diagrama de Classes
```mermaid
classDiagram
    class Usuario {
        -String nome
        -String email
        -String senha
        +registrar()
        +login()
        +atualizarPerfil()
    }

    class Produto {
        -String nome
        -double preco
        -int quantidade
        -String descricao
        -String categoria
        +adicionarProduto()
        +atualizarProduto()
        +removerProduto()
    }

    class Carrinho {
        -Map<Produto, int> itens
        +adicionarProduto(Produto p, int quantidade)
        +removerProduto(Produto p)
        +calcularTotal()
        +finalizarCompra()
    }

    class Pedido {
        -Usuario usuario
        -List<Produto> produtos
        -String status
        -Date data
        +atualizarStatus()
        +cancelarPedido()
    }

    class Entrega {
        -Pedido pedido
        -String endereco
        -String horario
        -String status
        +atualizarStatus()
        +agendarEntrega()
    }

    Usuario "1" --> "many" Pedido
    Pedido "1" --> "many" Produto
    Pedido "1" --> "1" Entrega
    Carrinho "1" --> "many" Produto
    Usuario "1" --> "1" Carrinho
Diagramas de Sequência
Fluxo de Compra
sequenceDiagram
    participant Usuario
    participant Carrinho
    participant Produto
    participant Pedido
    participant Entrega

    Usuario->>Produto: Buscar Produto
    Usuario->>Carrinho: Adicionar Produto ao Carrinho
    Usuario->>Carrinho: Verificar Carrinho
    Usuario->>Carrinho: Finalizar Compra
    Carrinho->>Pedido: Criar Pedido
    Pedido->>Entrega: Agendar Entrega
    Usuario->>Entrega: Acompanhar Status da Entrega
Cadastro de Usuário
sequenceDiagram
    participant Usuario
    participant Sistema

    Usuario->>Sistema: Preencher Formulário de Cadastro
    Sistema->>Usuario: Validar Informações
    Sistema->>Usuario: Confirmar Cadastro
Casos de Uso
Caso de Uso: Registrar Usuário
Ator Principal: Usuário

Descrição: O usuário se registra no aplicativo fornecendo suas informações pessoais.

Fluxo Principal:

O usuário acessa a tela de registro.
O usuário preenche o formulário de registro com nome, email e senha.
O sistema valida as informações fornecidas.
O sistema confirma o registro e cria uma nova conta.
Fluxo Alternativo:

O sistema detecta informações inválidas e solicita correções.
Caso de Uso: Realizar Compra
Ator Principal: Usuário

Descrição: O usuário realiza a compra de produtos adicionados ao carrinho.

Fluxo Principal:

O usuário adiciona produtos ao carrinho.
O usuário verifica os itens no carrinho.
O usuário finaliza a compra.
O sistema cria um pedido e agenda a entrega.
O usuário acompanha o status da entrega.
Fluxo Alternativo:

O sistema notifica o usuário sobre a falta de estoque de algum produto

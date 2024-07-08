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

### Diagrama de Sequência: Fluxo de Compra

```mermaid
sequenceDiagram
    participant Usuario
    participant Carrinho
    participant Produto
    participant Pedido
    participant Entrega

    Usuario->>+Produto: Buscar Produto
    Produto-->>-Usuario: Exibir Produto
    Usuario->>+Carrinho: Adicionar Produto ao Carrinho
    Carrinho-->>-Usuario: Produto Adicionado
    Usuario->>+Carrinho: Verificar Carrinho
    Carrinho-->>-Usuario: Exibir Itens do Carrinho
    Usuario->>+Carrinho: Finalizar Compra
    Carrinho-->>-Usuario: Exibir Resumo da Compra
    Usuario->>+Pedido: Criar Pedido
    Pedido-->>-Usuario: Pedido Criado
    Pedido->>+Entrega: Agendar Entrega
    Entrega-->>-Pedido: Entrega Agendada
    Usuario->>+Entrega: Acompanhar Status da Entrega
    Entrega-->>-Usuario: Status da Entrega

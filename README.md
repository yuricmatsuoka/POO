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

[16:52, 7/8/2024] +55 62 9196-6827: Diagramas de Sequência
Fluxo de Compra
[16:53, 7/8/2024] +55 62 9196-6827: sequenceDiagram
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

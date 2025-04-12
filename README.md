# Tutorial de Uso da API de Personagens

Este tutorial mostra como usar todos os endpoints da API de personagens com exemplos de JSON.

## 1. Criar um Personagem

**POST** `/api/personagens`

```json
{
    "nome": "Gandalf",
    "nomeAventureiro": "Mago Cinzento",
    "classe": "MAGO",
    "level": 1,
    "forca": 6,
    "defesa": 4
}
```

## 2. Adicionar Item Mágico ao Personagem

**POST** `/api/personagens/{id}/adicionar-item`

Exemplo de Arma:
```json
{
    "nome": "Cajado Místico",
    "tipo": "ARMA",
    "forca": 8,
    "defesa": 0
}
```

Exemplo de Amuleto:
```json
{
    "nome": "Amuleto da Sabedoria",
    "tipo": "AMULETO",
    "forca": 3,
    "defesa": 2
}
```

Exemplo de Armadura:
```json
{
    "nome": "Armadura Élfica",
    "tipo": "ARMADURA",
    "forca": 0,
    "defesa": 7
}
```

## 3. Buscar um Personagem

**GET** `/api/personagens/{id}`

## 4. Buscar Amuleto de um Personagem

**GET** `/api/personagens/{id}/amuleto`

## 5. Listar Todos os Personagens

**GET** `/api/personagens`

## 6. Atualizar um Personagem

**PUT** `/api/personagens/{id}`

```json
{
    "nome": "Gandalf, o Branco",
    "nomeAventureiro": "Mago Branco",
    "classe": "MAGO",
    "level": 2,
    "forca": 7,
    "defesa": 3
}
```

## 7. Remover um Item do Personagem

**DELETE** `/api/personagens/{id}/remover-item/{itemId}`

## 8. Remover um Personagem

**DELETE** `/api/personagens/{id}`

## Regras Importantes

1. A soma de força e defesa deve ser menor ou igual a 10 pontos.
2. Cada tipo de item (ARMA, AMULETO, ARMADURA) tem seus próprios atributos de força e defesa.
3. Um personagem pode ter múltiplos itens mágicos.
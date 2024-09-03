Sistema de login
    SuperClasse Usuario
    subclasse de Usuario Administrador
    método- validação de dados (nome e senha)

Escolha de Personagem
    SuperClasse Personagem
    subclasses personagem 1 e 2
    método+ Escolher personagens (2)
    método+ Jogar

Jogo
    renderizar inimigo (Fase)
    renderizar jogador
    jogador passa de fase ou morre
    jogador passa as tres fases e ganha

---------Jogo--------------

Inimigo
    SuperClasse Inimigo
    atributo Tiro (objeto)
    método- Atirar
    método- tomarTiro
    método- destruir Inimigo
    atributo- vida
    atributo- imgAp
    atributo- tamanho
    subclasses inimigo 1, 2 e o Boss

Usuario
    atributo- nome
    atributo- senha
    atributo- score
    atributo- personagem(objeto)

Administrador
    Personagem método- modificarPersonagem()

Personagem
    atributo- vida
    atributo- velMov
    atributo- Tiro
    atributo- x
    atributo- y
    atributo- altura
    atributo- largura
    retangulo hitboxP(x,y,altura,largura)
    
interface Tiro
    atributo- vel 
    atributo- dano
    metodo protected Atira
elemento
Animar o tiro
implementar a hitbox

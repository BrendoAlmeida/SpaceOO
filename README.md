Sistema de login  
    SuperClasse Usuario  
    subclasse de Usuario Administrador  
    método- validação de dados (nome e senha)  

Hitbox
    posição x e y
    altura e largura

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
Hitbox   
    atributo- x  
    atributo- y  
    atributo- altura  
    atributo- largura  
    retangulo hitboxP(x,y,altura,largura)  
    
interface Inimigo  
    método- Atirar  
    método- tomarTiro  
    método-  morrer  
    atributo- vida  
    atributo- imgAp  
    atributo- tamanho  
    atributo- hitbox
    
Inimigo 1
    Implementa inimigo
    Atributo- : Tiro
Inimigo 2
    Implementa inimigo
    Atributo- : Tiro
BOSS
    Implementa inimigo
    Atributo- : Tiro

Usuario  
    atributo- nome  
    atributo- senha  
    atributo- score  

Administrador  
    Personagem método- modificarPersonagem()  
    
interface Personagem  
    atributo- vida  
    atributo- velMov 
    atributo- hitbox
    método- Atirar
    método estático- tomarTiro

Personagem 1
    Implementa Personagem
    atributo - Tiro
Personagem 2
    Implementa Personagem
    atributo - Tiro

Tiro  
    atributo- vel  
    atributo- dano  
    metodo protected Atira  
    atributo- hitbox
    
elemento  
Animar o tiro  
implementar a hitbox  

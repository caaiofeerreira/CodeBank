Desafio - Alura.
Vamos implementar uma aplicação para controlar nossa conta bancária, seja ela virtual ou não.

🔨 Objetivos do projeto
Criar um cabeçalho inicial com os dados do cliente (Nome, Tipo da Conta e Saldo)
Criar um menu que descreve as operações.
* O menu deve ter quatro opções: a de entrada de valor, saída de valor, consulta de saldo e finalização da aplicação.
Lembre-se que para fazer a saída de valores, é necessário ter saldo suficiente.
O menu deve aparecer continuamente até que o usuário digite a opção para sair.
Caso ele digite qualquer opção que não seja correta, deve apresentar a mensagem de opção inválida.
Usaremos a classe Scanner para fazer a leitura da opção do usuário.

* Implementei ao menu a opção PIX.

MENU:

** DIGITE SUA OPÇÃO **

        1 - Consultar Saldo
        2 - Sacar
        3 - Depositar
        4 - Pix
        5 - Sair

Ao escolher a opção PIX, deve fornecer a chave-pix, valor e confirmar(S/N).
Qualquer transação feita, o saldo é atualizado.
Escolhendo a opção 5 (sair), aparece o histórico de transações feitas.

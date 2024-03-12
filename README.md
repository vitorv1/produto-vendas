# Vendas

## #️⃣ SOBRE  

Nesse projeto é apresentado um sistema de gerenciamento de cadastro de cliente, como também alteração, consulta, e exclusão, assim também se aplica para produto. Contudo é possível gerenciar vendas da mesma forma utilizando-se de cliente e produto.



## ⚒️ FERRAMENTAS

As tecnologias utilizadas no projeto são: linguagem Java juntamente com o framework Spring Boot, o banco de dados utilizado no projeto é o MySql, gerenciado pelo Spring Data. O projeto é desenvolvido para desponibilizar APIs Rest com Spring Web

- Java 17

- Spring Boot 3.1.2

- MySql 8

- Maven

## 🔵 COMO UTILIZAR

```bash
 #Clonar o repositório na sua máquina
 #Abra seu CMD e digite o sequinte código:
 $ git clone https://github.com/vitorvieirah/produto-vendas.git
`````

## 

```bash
Agora basta abrir o projeto em sua IDE de preferência, vale ressaltar que é nescessário ter a SGBD MySQL instalada em sua máquina, caso não tenha basta seguir este link:

- WINDOWS: https://dicasdeprogramacao.com.br/como-instalar-o-mysql-no-windows/

- LINUX: https://www.linkedin.com/pulse/instalação-e-configuração-do-mysql-linux-mint-20-ubuntu-yenny-delgado/?originalSubdomain=pt

- MAC: https://blog.cod3r.com.br/configuracao-de-ambiente-do-mysql-para-mac/
````
##

```bash
##Configurando o banco de dados
Para que o projeto consiga se conectar com o banco de dados é nescessário apenas configurar seu usuário e senha, basta entrar no arquivo "application.properties", nesse sequinte caminho: "\produto-vendas\src\main\resources\application.properties": 
````
![print application](https://github.com/vitorvieirah/produto-vendas/assets/108897429/f3f05f8a-998a-41a9-a7c0-03fffc4c30e7)

```bash
Depois de entrar no arquivo basta mudar o valor dos comandos "spring.datasource.username" e "spring.datasource.password", basta colocar o seu usuário e senha, para o programa se conectar ao banco automáticamente.
````

![print config](https://github.com/vitorvieirah/produto-vendas/assets/108897429/a19a2849-5fd9-4051-a2d7-4fa9fe3e1d81)

##

```bash
##Mandando requisições
Para mandar as requisições para as APIs recomendo a utilização de um software, o "Postman" ou "Insomnia", mas claro, fique livre para utilizar o de sua preferência.
Link para dowload:
 - POSTMAN: https://www.postman.com/downloads/
 - INSOMNIA: https://insomnia.rest/download
````
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/25425825-862a3d00-b7d2-4d0f-a465-65b2578ab0fd?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D25425825-862a3d00-b7d2-4d0f-a465-65b2578ab0fd%26entityType%3Dcollection%26workspaceId%3Dfdeb1307-5fde-4e9b-93de-f0c36a70627b)
## ⚠️LEMBRETE

```bash
Lembrando que é nescessário estar conectado a internet para utilizar o projeto, para assim o Maven conseguir baixar as dependências corretamente.
````




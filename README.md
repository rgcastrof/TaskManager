# Gerenciador de Tarefas

Gerenciador de tarefas Desktop construído com Javafx. Projeto para o curso de Java do Capacita Brasil.

## Como rodar o projeto
Requer banco de dados PostgreSQL instalado no sistema. Antes de executar a aplicação
é necessário ter o banco de dados PostgreSQL rodando para fazer a conexão. Edite o arquivo em:
```bash
./external/configuration/database.properties
```
e altere os parametros com seus dados do banco, por padrão, a aplicação usa a porta 5432, exemplo:
```bash
jdbc.url=jdbc:postgresql://localhost:5432/mydatabase
jdbc.username=myusername
jdbc.password=mypassword
```
Após a conexão ser estabelecida, a aplicação estará pronta para uso.
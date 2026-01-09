# Análise de Feedback

Serviço responsável por receber os feedbacks dos alunos e analisá-los. 

Caso a nota seja menor que 5, um email é enviado para o administrador.

Foi desenvolvido para ser utilizado como uma função lambda na AWS. É necessário configurar um e-mail no SES da AWS.


## Instalação

1 - Clone o repositório:
```bash
git clone https://github.com/raclug/feedback-analysis.git
cd feedback-analysis
```

2 - Gere o artefato jar:
```bash
./mvnw clean package
```

3 - Faça o deploy na AWS Lambda:

- Crie uma nova função Lambda na AWS.

- Faça o upload do arquivo `target/avalidacao-analise-service-0.0.1-SNAPSHOT-aws.jar`

- Configure o handler como `org.springframework.cloud.function.adapter.aws.FunctionInvoker`

- Adicione as variáveis de ambiente necessárias: `EMAIL_FROM e EMAIL_TO` com os e-mails configurados no SES.

- Configure um private endpoint para o serviço Email.

- Na role da lambda adicine uma política com permissão para enviar e-mails via SES.


## Execução

A execução dessa lambda é feita via invocação da lambda de gravação de feedbacks.

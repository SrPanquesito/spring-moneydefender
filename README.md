# spring-moneydefender
[Serverside] Proyecto sobre educación financiera.

RPC como intermediario entre Clientside web app y base de datos en SQL.

PD. No pude hacer el setup de las variables de entorno. Agregar application.properties con las siguientes propiedades en ```src/main/resources``` 
PDD. En ambiente de producción solo agregar las propiedades al entorno.
```
> Base de Datos (BDD)
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://***{{DIRECCION IP}}***:3306/***{{NOMBRE DE LA BDD}}***?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
spring.datasource.username=***{{USUARIO BDD}}***
spring.datasource.password=***{{CONTRA BDD}}***
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.datasource.initialization-mode=always
spring.jpa.show-sql=true

> Conexión a email
spring.mail.host=***{{HOST MAIL}}*** *Ejemplo: gator1234.hostgator.com*
spring.mail.port=465
spring.mail.username=***{{USUARIO MAIL}}***
spring.mail.password=***{{CONTRA MAIL}}***

> Propiedades extras para email
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.socketFactory.port = 465
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory

> Json Web Token (JWT) tiempo de expiración entre requests
jwt.expiration.time=900000
```
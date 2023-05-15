# Diagrama de Secuencia para la Creación de Cuentas en la API

La siguiente secuencia representa el flujo de una solicitud POST al endpoint `/api/accounts` en nuestra aplicación:

```plaintext
Cliente  
  |
  | POST /api/accounts (con payload)
  v
Controlador REST (AccountsController)
  |
  | validarFormatoEmail(payload.email)
  v
Validador de Email
  | (devuelve: válido o no)
  v
Controlador REST (AccountsController)
  |
  | crearCuenta(payload)
  v
Servicio de Cuentas
  |
  | guardarCuenta(datosCuenta, payload.phones)
  v
Repositorio de Cuentas
  |
  | (operación en base de datos: guardar cuenta y teléfonos relacionados)
  v
Base de Datos (Tablas de Cuentas y Teléfonos)
  |
  | (respuesta: cuenta guardada, teléfonos guardados)
  v
Repositorio de Cuentas
  |
  | (respuesta: cuenta con teléfonos)
  v
Servicio de Cuentas
  |
  | (devuelve: cuenta con teléfonos)
  v
Controlador REST (AccountsController)
  |
  | (devuelve: respuesta HTTP con datos de la cuenta)
  v
Cliente
```

En esta secuencia:

1. El cliente hace una solicitud POST al endpoint /api/accounts con los datos de la cuenta y del teléfono.
2. El controlador REST valida el formato del email. Si el formato del email es inválido, devuelve inmediatamente una respuesta HTTP 400 (Solicitud Incorrecta).
3. Si el email es válido, el controlador REST llama al servicio de cuentas para crear la cuenta.
4. El servicio de cuentas se comunica con el repositorio de cuentas para realizar la operación en la base de datos. Esta operación incluye guardar la cuenta y los teléfonos relacionados en la base de datos.
5. La respuesta de la operación en la base de datos se propaga de nuevo al servicio de cuentas.
6. Luego, el servicio de cuentas devuelve los datos de la cuenta (incluyendo los teléfonos) al controlador REST.
7. Finalmente, el controlador REST devuelve una respuesta HTTP al cliente, incluyendo los datos de la cuenta (con los teléfonos), el código de estado, y cualquier otra información requerida.

# Script de creación de BD
```
drop table if exists accounts CASCADE;
drop table if exists phones CASCADE;
create table accounts (id binary(255) not null, created_at timestamp not null, email varchar(50) not null, is_active boolean not null, last_login timestamp, name varchar(50) not null, password varchar(255) not null, token binary(255) not null, updated_at timestamp, primary key (id));
create table phones (id binary(255) not null, area_code integer not null, country_code integer not null, number varchar(50) not null, account_id binary(255), primary key (id));
alter table phones add constraint FKn94qtbcblybh4xuljygyvvdfv foreign key (account_id) references accounts;
```
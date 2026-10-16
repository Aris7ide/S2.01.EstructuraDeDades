# Exercici 1 - Òptica

## 📌 Enunciat del exercici
### Una óptica, llamada “Cul d'Ampolla”, quiere informatizar la gestión de los clientes/as y ventas de gafas.

En primer lugar, la óptica quiere saber cuál es el proveedor de cada una de las gafas. En concreto quiere saber de cada proveedor :
- El nombre
- La dirección (calle, número, piso, puerta, ciudad, código postal y país)
- Teléfono
- Fax
- NIF.

La política de compras de la óptica se basa en que las gafas de una marca se comprarán a un único proveedor (así podrá sacar mejores precios), pero pueden comprar gafas de varias marcas a un proveedor. De las gafas quiere saber:
- La marca.
- La graduación de cada uno de los cristales.
- El tipo de montura (flotante, pasta o metálica).
- El color de la montura.
- El color de cada cristal.
- El precio. 
De los clientes/as desea almacenar:
- El nombre.
- La dirección postal.
- El teléfono.
- El correo electrónico.
- La fecha de registro.
- Cuando llega un/a cliente/a nuevo, almacenar el/la cliente/a que le ha recomendado el establecimiento (siempre que alguien le haya recomendado).
- Nuestro sistema deberá indicar quién ha sido el empleado/a que ha vendido cada anteojo.

## ✨ Funcionalitats
- MySQL, Docker

## 🛠 Tecnologies
- **Llenguatge**: Java 25
- **IDE**: IntelliJ IDEA

## Excecution
- He installado Docker, configurado atraves del terminal de IntelliJ
- Tambièn he installado MySQL Workbench para ver las tablas creadas
- He creado la clase Provider y la tabla en MySQL Workbench
- he añadido la dependancy de mySQL y he creado la clase DatabaseConnection con las instrucciones para connectarse a la tabla.
- he creado ProviderRepository para traducir las informaciones de la clase Provider en MySQL
- he creado un test de ProviderRepository para probar que el provider se guarde
- como con Provider he creado la glasse Glasses, el repository GlassesRepository y la tabla en MySQL workbench
- hago lo mismo con Client y ClientRepository
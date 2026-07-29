# Vehicle Management System

A Java / Spring Boot backend for managing a vehicle fleet, with a small
built-in website and full Swagger (OpenAPI) documentation.

## Where the Java basics live

All eight core OOP concepts are used for a real reason in the model layer
(`src/main/java/com/fleetos/vms/model`), not as separate exercises:

| Concept        | Where |
|----------------|-------|
| Class          | `Vehicle`, `Car`, `Truck`, `ElectricCar` |
| Object         | created in `VehicleService` (`new Car(...)`, etc.) |
| Constructor    | every class in `model/` — protected no-arg for JPA + a real parameterized one |
| Encapsulation  | private fields in `Vehicle`, guarded `setFuelLevel()` |
| Inheritance    | `Car extends Vehicle`, `Truck extends Vehicle`, `ElectricCar extends Vehicle` |
| Polymorphism   | `vehicle.start()` in `VehicleService.start()` — resolved per real object |
| Abstraction    | `Vehicle` is `abstract`, with abstract `start()` / `fuelEfficiency()` |
| Interface      | `Chargeable`, implemented only by `ElectricCar` |

## Run it

Requires **Java 17+** and **Maven** (or use the included wrapper if you add one).

```bash
mvn spring-boot:run
```

Then open:

- **Website:** http://localhost:8080/
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **Raw OpenAPI spec:** http://localhost:8080/v3/api-docs
- **H2 database console:** http://localhost:8080/h2-console
  (JDBC URL: `jdbc:h2:mem:vmsdb`, user: `sa`, blank password)

The app seeds 3 starter vehicles on boot (a Car, a Truck, and an
ElectricCar) so both the website and Swagger have data immediately.

## API summary

| Method | Endpoint                          | Description                          |
|--------|------------------------------------|---------------------------------------|
| GET    | `/api/vehicles`                   | list the fleet                        |
| GET    | `/api/vehicles/{id}`              | get one vehicle                       |
| GET    | `/api/vehicles/stats`             | fleet totals                          |
| POST   | `/api/vehicles/car`               | add a Car                             |
| POST   | `/api/vehicles/truck`             | add a Truck                           |
| POST   | `/api/vehicles/electric-car`      | add an ElectricCar                    |
| POST   | `/api/vehicles/{id}/start`        | polymorphic start()                   |
| POST   | `/api/vehicles/{id}/stop`         | power off                             |
| PATCH  | `/api/vehicles/{id}/fuel`         | update fuel level                     |
| POST   | `/api/vehicles/{id}/charge`       | charge battery (Chargeable only)      |
| DELETE | `/api/vehicles/{id}`              | remove a vehicle                      |

## Project structure

```
src/main/java/com/fleetos/vms/
  VmsApplication.java        # entry point
  config/OpenApiConfig.java  # Swagger metadata
  config/DataSeeder.java     # starter data
  model/                     # Vehicle, Car, Truck, ElectricCar, Chargeable
  dto/                       # request payloads
  repository/                # Spring Data JPA
  service/                   # business logic (object creation, polymorphism)
  controller/                # REST endpoints + Swagger annotations
  exception/                 # 404 / validation handling
src/main/resources/
  application.properties
  static/index.html          # the website, calls the API via fetch()
```

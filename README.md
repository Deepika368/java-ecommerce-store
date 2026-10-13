# Nova Store — Java E-Commerce Website

A beginner-friendly e-commerce demo built with **Java 17, Spring Boot, Thymeleaf, HTML, and CSS**. It is designed to be imported into Eclipse and is suitable for learning and portfolio practice.

## Features

- Product listing with sample products
- Search by product name or category
- Filter by category
- Add products to a session-based cart
- Increase/decrease quantities and remove items
- Checkout form with basic browser validation
- Demo order confirmation and order total
- Responsive layout for desktop and mobile

## Important limitations

This is a learning/demo project. Products are stored in Java code, the cart is stored in the browser session, and orders are **not** saved in a database. Checkout does not process real payments. Do not use this as a production store. A later version can add MySQL, user login, stock management, and a payment provider's test mode.

## Requirements

- JDK 17 or newer
- Eclipse IDE for Enterprise Java and Web Developers, or Eclipse IDE for Java Developers with Maven support
- Internet connection on the first run so Maven can download dependencies

## Run in Eclipse (step by step)

1. Install JDK 17. Check in a terminal with `java -version`.
2. Extract the ZIP file to a folder you can find easily.
3. Open Eclipse.
4. Choose **File → Import**.
5. Select **Maven → Existing Maven Projects**, then click **Next**.
6. For **Root Directory**, browse to the extracted `Eclipse_Ecommerce_Java_Project` folder.
7. Make sure `pom.xml` is selected, then click **Finish**.
8. Wait for Maven to download dependencies. If needed, right-click the project and choose **Maven → Update Project**.
9. In Package Explorer, open `src/main/java` → `com.deepu.ecommerce`.
10. Right-click `EcommerceApplication.java` → **Run As → Java Application**. If Eclipse offers Spring Boot App, that is also fine.
11. Wait for the console to show that the application started on port 8080.
12. Open a browser and visit: `http://localhost:8080`

## How to use the website

1. Search for a product, such as `jeans`, or choose a category.
2. Click **+ Add** to add a product to your cart.
3. Open **Cart** to change quantities or remove products.
4. Click **Continue to checkout**, fill in the demo details, and place the order.
5. The confirmation page shows a demo order number. No payment is taken.

## Project structure

```text
Eclipse_Ecommerce_Java_Project/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/deepu/ecommerce/
        │   ├── EcommerceApplication.java
        │   ├── controller/StoreController.java
        │   └── model/
        │       ├── Product.java
        │       └── CartItem.java
        └── resources/
            ├── application.properties
            ├── static/style.css
            └── templates/
                ├── index.html
                ├── cart.html
                ├── checkout.html
                └── success.html
```

## What the main files do

- `EcommerceApplication.java`: starts the Spring Boot application.
- `Product.java`: defines a product's ID, name, category, price, description, and icon.
- `CartItem.java`: holds a product and its selected quantity.
- `StoreController.java`: handles website routes, product search, cart actions, and demo checkout.
- `templates/*.html`: the pages users see in their browser.
- `static/style.css`: controls colors, layout, spacing, and responsive design.
- `pom.xml`: lists Java libraries and tells Maven how to build the application.

## Common problems

- **`java` is not recognized:** install JDK 17 and configure `JAVA_HOME`/PATH, then restart Eclipse.
- **Port 8080 is already in use:** close the other app using port 8080, or change `server.port=8080` in `application.properties` to `8081` and visit `http://localhost:8081`.
- **Dependencies do not download:** check your internet connection, then right-click the project → **Maven → Update Project**.
- **A template cannot be found:** ensure the HTML files are inside `src/main/resources/templates`, not in the Java source folder.

## GitHub upload

1. Sign in at https://github.com and create a new repository named `java-ecommerce-store`.
2. Extract this project ZIP and upload the project files/folders to the repository. Include `pom.xml`, `README.md`, and `src`.
3. Do not upload passwords, API keys, or private credentials. This demo has none.
4. In the repository page, check that the README is displayed and the Java source files are visible.

## Possible future improvements

- MySQL database and product/order tables
- Registration and login
- Admin product management
- Stock quantity and validation
- Order history
- Unit tests and input validation
- Payment gateway sandbox integration

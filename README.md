# TreeLogic

**TreeLogic** is a web application for creating, viewing, and managing binary search trees (BST).
This project allows users to input a list of numbers, generate a binary search tree, and view previously created trees. 
The application uses a modern and user-friendly interface with responsive design.

## Features

- **Input Numbers**: Enter a list of numbers to generate a binary search tree.
- **Process Numbers**: Visualize the tree structure in JSON format.
- **View Previous Trees**: Retrieve and display previously generated trees.
- **User-Friendly Interface**: Simple and modern UI with responsive design.

## Technologies Used

- **Spring Boot**: For creating the REST API and handling server-side logic.
- **JavaScript**: For handling dynamic content and user interactions.
- **CSS**: For styling the application with a modern look and feel.
- **HTML**: For the structure of web pages.
- **H2 Database**: For storing and retrieving tree data.

## Getting Started

To get started with **TreeLogic**, follow these instructions:

### Prerequisites

- Java 22
- Maven (for building the project)
- A web browser

### Installation

1. **Clone the repository**:

    ```bash
    git clone https://github.com/janeilchantelle/TreeLogicApplication.git
    ```

2. **Navigate to the project directory**:

    ```bash
    cd your-repo-name once saved on your local machine. 
    ```

3. **Build the project**:

    ```bash
    mvn clean install
    ```

4. **Run the application**:

    ```bash
    mvn spring-boot:run
    ```

5. **Open your web browser** and go to `http://localhost:8080/input-form`.

### Usage

1. **Enter Numbers**: Go to `http://localhost:8080/input-form` to input a list of numbers.
2. **Process Numbers**: Submit the numbers to generate the binary search tree. The result will be displayed in JSON format on the same page.
3. **View Previous Trees**: Click on the link to `View Previous Trees` to see a list of previously created trees.
4. Click on any tree in the list to view its details.

### Endpoints

- **POST** `/process-numbers`: Processes the list of numbers and generates a tree.
- **GET** `/view-trees`: Displays a list of previously generated trees.
- **GET** `/get-tree?id={id}`: Retrieves a specific tree by its ID.

### Project Structure

- **`src/main/java/com/keyin/TreeLogic`**: Contains the main application code.
  - **`controller`**: Contains the Spring MVC controllers.
  - **`model`**: Contains the data models.
  - **`repository`**: Contains the repository interfaces for database access.
  - **`service`**: Contains the service classes with business logic.
- **`src/main/resources`**: Contains application configuration files.
  - **`application.properties`**: Configuration file for Spring Boot.
- **`src/main/resources/static`**: Contains static resources (CSS, JS, HTML files).

## Contributing

If you'd like to contribute to **TreeLogic**, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add some feature'`).
5. Push to the branch (`git push origin feature/YourFeature`).
6. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- **Spring Boot**: For the easy setup and configuration.
- **H2 Database**: For the lightweight and in-memory database.

---


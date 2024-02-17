# Weather API App

## Getting Started

Welcome to the Weather API App. This Java-based application provides real-time weather information through a graphical user interface. Designed for use in the Visual Studio Code environment, it offers a seamless experience for getting up-to-date weather data.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java Development Kit (JDK) - Version 11 or above
- Visual Studio Code
- Java Extension Pack for Visual Studio Code

## Folder Structure

The project is organized as follows:

- `src`: Contains the source code files for the application.
- `lib`: A directory for the project's dependencies. Add any required libraries here.
- `bin`: The default directory for compiled output files, where `.class` files will be generated.

> **Note:** You can customize the folder structure by modifying the `.vscode/settings.json` file.

## Setting Up the Project

1. **Clone the Repository**: Begin by cloning this repository to your local machine.
2. **Open the Project**: Open the folder in Visual Studio Code.
3. **Install Dependencies**: Use the `JAVA PROJECTS` view in VSCode to manage your dependencies. Detailed instructions can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Running the Application

Follow these steps to run the Weather API App:

1. Open the `src` directory in your terminal or command prompt.
2. Compile the Java files with `javac -d bin src/*.java` to generate `.class` files in the `bin` folder.
3. Run the application using `java -cp bin AppLauncher`.

## Using the Weather API App

Upon launching, the Weather App GUI appears, allowing you to:

- **Search for Weather**: Simply enter a location in the search bar. You can now initiate the search by pressing the enter key or clicking the search button, making it more user-friendly. The app will retrieve and display the current weather information for the specified location.

## Contributing

Contributions are welcome! To contribute, please fork the repository and use a feature branch. Pull requests are highly appreciated.

## License

This project is licensed under the [MIT License](LICENSE.md) - see the LICENSE file for details.

## Acknowledgments

- Thanks to the Visual Studio Code team for the excellent development environment.
- Weather data is provided by [Open Meteo](https://open-meteo.com/), an open-source weather API, ensuring accurate and up-to-date information.

---

For additional guidance on writing Java code in Visual Studio Code, refer to the official [Visual Studio Code Java Tutorial](https://code.visualstudio.com/docs/java/java-tutorial).

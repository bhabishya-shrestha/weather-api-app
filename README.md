# Weather API App

## Getting Started

Welcome to the Weather API App. This application is designed to provide real-time weather information using a Java-based graphical user interface. It's built to run in the Visual Studio Code environment, leveraging its powerful Java support. This guide will help you set up and run the application on your local machine.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java Development Kit (JDK) - Version 11 or above
- Visual Studio Code
- Java Extension Pack for Visual Studio Code

## Folder Structure

The project is organized as follows:

- `src`: Contains the source code files for the application.
- `lib`: A directory for the project's dependencies. Add any required libraries here.
- `bin`: The default directory for compiled output files. This is where your `.class` files will be generated after compilation.

> **Note:** To customize the folder structure, modify the `.vscode/settings.json` file accordingly.

## Setting Up the Project

1. **Clone the Repository**: Start by cloning this repository to your local machine.
2. **Open the Project**: Open the folder in Visual Studio Code.
3. **Install Dependencies**: Navigate to the `JAVA PROJECTS` view in VSCode to manage your project's dependencies. For detailed instructions, visit [this link](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Running the Application

To run the Weather API App, follow these steps:

1. Navigate to the `src` directory in your terminal or command prompt.
2. Compile the Java files using the command `javac -d bin src/*.java` to generate the `.class` files in the `bin` folder.
3. Run the application with `java -cp bin AppLauncher`.

## Using the Weather API App

Upon launching, the Weather App GUI will appear. Here's how to use it:

- **Search for Weather**: Enter a location in the search bar and press enter to retrieve and display the weather information.

## Contributing

Contributions are welcome! If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## License

This project is licensed under the [MIT License](LICENSE.md) - see the LICENSE file for details.

## Acknowledgments

- Thanks to the Visual Studio Code team for the excellent Java development environment.
- Weather data provided by [Open MeteoOpen, an open-source weather API].

---

For more information on how to write Java code in Visual Studio Code, refer to the official [Visual Studio Code Java Tutorial](https://code.visualstudio.com/docs/java/java-tutorial).


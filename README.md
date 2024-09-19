IRCTC Flight Search Automation:
This project automates the process of searching for flights on the IRCTC Air website using Selenium WebDriver. It allows users to select the browser, enter origin and destination cities, select the travel date, and search for available flights. The project also captures a screenshot of the search results.

Prerequisites:
Java Development Kit (JDK) 8 or higher
Selenium WebDriver
Browser drivers (ChromeDriver, GeckoDriver, etc.)
Eclipse IDE or any other Java IDE

Install dependencies: 
Ensure you have Maven installed.
Navigate to the project.

Configure WebDriverManager: 
WebDriverManager will automatically manage the browser drivers for you.

Usage:
Run the IrctcFlightSearchAutomation class.
Enter the browser name (chrome/firefox) when prompted.
The script will open the IRCTC flight search page and perform the following actions:
- Select Hyderabad as the origin city.
- Select Pune as the destination city.
- Select today’s date as the travel date.
- Choose “Business” class.
- Click the search button.
The script will then verify the search results and display the number and name of available flights.
 A screenshot of the search results will be saved in the project directory.

Code Overview:
Driver Setup: Initializes the WebDriver based on the user’s browser choice.
Flight Search: Automates the process of entering flight search details and clicking the search button.
Result Verification: Verifies if the search results match the input criteria.
Screenshot Capture: Takes a screenshot of the search results and saves it to a file.

Dependencies:
Selenium WebDriver: For browser automation.
Selenium Support: Offers additional support classes.
WebDriverManager: For managing browser drivers.

Notes:
Ensure you have the necessary browser installed (Chrome or Firefox) before running the program.
The Screenshot will be stored in the project directory.

COE528- Final Project | by Jonathan - Last Updated Aug 15, 2024

My final project consists of 14 classes and 2 packages called BankingApplication and GUI. Here is a brief description of what each classes do: 

The BankingApplication Package contains the following 10 classes which includes methods to: 

Banking: does depositing, withdrawing and making online purchases .
User: an abstract class that contains roles, username and password that are Strings.
Customer: implements User class. A customer can login, logout, make online purchases, deposit money and withdraw from the Bank. 
Manager: implements User class. The Manager can add customers, delete customers, login and logout.
CustomerFile: contains an ArrayList which can add, delete, get customer, get username, get role and get password from customer objects.
CustomerLevels: returns 3 levels that can be “Silver”, “gold” and “Platinum”. 
LevelTypes: an interface that contains methods named setAccountlevel and getAccountLevel. This implements the state design pattern. 
SilverLevel: implements LevelType class and returns “Silver”.
GoldLevel: implements LevelType class and returns “Gold”.
PlatinumLevel: implements LevelType class and returns “Platinum”.

The GUI Package contains the following 4 java class with fxml classes: The 4 java classes are: 

BankAccoutMainFXML: right-click on this class and run the file to run the entire code.
LoginPageController (login scene): can throw errors and log into the other two scenes. 
CustomerController (customer scene): can throw errors and allow customers to perform tasks. 
MangerController (manger scene): Can throw errors and allow the manager to perform task. 

# StateLevelAndroidAppDevelopment

Project : Blubox  

Activities of app:
	registration -> Login -> Home(services) -> Products (Products) -> product page

a) used shared preferences to store user data (userbio.class)
b)used recycler views and custom adapter classes to display data in a list.

Working app classes usage sequence:
1. MainActivity.class
	1.Displays a splash screen and redircts user to registration page if not logged in
	2.redirects the old user 
2. Registration.class :
 	Registers the user and store the data for future logging
3. Login.class ;
	1. helps the user to login into the app
	2. retrieves the stored data from shared preferences
	3. redirects the user to Home page
4. Home.class ;
	1.displays the categories using recycler view
	uses ServiceAdapter class to bind data to recycler view.
5. Product.class
	1.parses the json file in re/raw/product.json
	2.retrieves the data and shows the products of a category
	3. uses ProductAdapter.class to bind data to recycler view
6. profile.class
	1.displays product info



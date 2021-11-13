so to Run this Project we can follow bellow steps - 

1. get the project from Github / Gmail (Zip)
2. import project into IDE
3. update maven packages
4. Run the Application as spring boot Application

5. API's Should benahve as follows
	I) http://localhost:8080/storeurl?url=yahoo.com
		The API storeurl shall take a URL as a parameter and save that into a local variable of appropriate type, with a unique short key and a count(usage count) initialized to 0. 

	II) http://localhost:8080/get?url=google.com
		The API get shall take a URL as a parameter and return the unique short key after incrementing the usage count.

	III) http://localhost:8080/count?url=google.com
		The API count shall take a URL as a parameter and should return the latest usage count.

	IV) http://localhost:8080/list?page=1&size=2
		The API list should return all urls and counts with pagination. The return value is in JSON.
	
	V) http://localhost:8080/checkPagination?page=1&size=2 (Added just to check pagination clearly)
		The API list should Add 100 Recored in list and return all urls and counts with pagination. The return value is in JSON.

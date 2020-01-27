# Best_Deal_Store
a curriculumn project for CSP584

## BestDeal:

![image](https://user-images.githubusercontent.com/48393773/73146949-b11ee080-407a-11ea-8122-7f1917c7efbe.png)



## The following is the high-level description for BestDeal website:
* The intent is to build servlet-based web application that will allow customers to place orders online from BestDeal website
* Currently, the retailer has the products categories:

1. TV
2. Sound Systems
3. Phones
4. Laptops
5. Voice Assistant
6. Fitness Watches
7. Smart Watches
8. Headphones
9. Wireless Plan
(1). Basic: Unlimited talk, text and data.
(2). Premium: The Basic plan with added music and video streaming.
(3). Ultimate: The Premium plan with added speed and performance during peak hours.
    
* The intent is to build servlet-based web application that will allow customers to place orders online from BestDeal website
* The store has a StoreManager, Customers, and Salesmen The retailers sells different types of products
* The StoreManager can Add/Delete/Update products Salesman can create Customer accounts and can
* Add/Delete/Update customers’ orders

* Every product has Name, Price, Description and might have accessories that could be bought separately.
* When a product is selected for a view, all accessories associated with that product must be displayed below the product      horizontally.

* Retailer offers warranty that can be purchased by the customer for every console
* The customer must be able to create an account online

* The customer must be able to place an order online, check the status of the order, or cancel the order.
* The customer will pay using a credit card

* Some of the products may have retailer special-discounts Some of the products may have manufacturer rebates

* Customer shall be able to shop online to buy one or multiple items in the same session from the BestDeal online retailer.
* In the same session, the customer must be able to add or remove items from the shopping cart
* When the customer chooses to check out:

* The customer will enter personal information (Name, Address, Credit Card, etc.)
* The customer will be provided with a confirmation number and a delivery date (2 weeks after the order date) that the customer 
* can use to cancel an order at a later timer, though it must be 5 business days before the delivery date.

* Hard-code at least 5 values of every product category available in the store in a HashMap , plaintext file, serialized file, or xml file while implementing and testing your application.

### Login and Register functions
![image](https://user-images.githubusercontent.com/48393773/73148543-90f31f80-4082-11ea-8321-f6bf5a4c8745.png)
![image](https://user-images.githubusercontent.com/48393773/73148545-93557980-4082-11ea-9652-fb714f9a82c5.png)
![image](https://user-images.githubusercontent.com/48393773/73148547-96506a00-4082-11ea-8fe7-384ebbddbd3b.png)
![image](https://user-images.githubusercontent.com/48393773/73148549-98b2c400-4082-11ea-8dd4-8edcfd63978a.png)
### Make an order
![image](https://user-images.githubusercontent.com/48393773/73148552-9b151e00-4082-11ea-9594-f6d87a022796.png)
![image](https://user-images.githubusercontent.com/48393773/73148556-9cdee180-4082-11ea-96d1-a6655138fc4f.png)
![image](https://user-images.githubusercontent.com/48393773/73148559-9fd9d200-4082-11ea-8693-e89a9f9bb427.png)
![image](https://user-images.githubusercontent.com/48393773/73148563-a23c2c00-4082-11ea-8af9-8475e21854e2.png)

### Cancel an order
![image](https://user-images.githubusercontent.com/48393773/73148565-a405ef80-4082-11ea-9c02-42f967841961.png)
![image](https://user-images.githubusercontent.com/48393773/73148567-a5cfb300-4082-11ea-9d51-53b45806a760.png)
![image](https://user-images.githubusercontent.com/48393773/73148568-a8320d00-4082-11ea-9e02-819f79d3d8db.png)
![image](https://user-images.githubusercontent.com/48393773/73148570-aa946700-4082-11ea-8cd6-8151209b9a0c.png)

### Add products
![image](https://user-images.githubusercontent.com/48393773/73148579-ae27ee00-4082-11ea-9f02-7a5668d4895d.png)
![image](https://user-images.githubusercontent.com/48393773/73148588-aff1b180-4082-11ea-84cb-711ad6421aa2.png)
![image](https://user-images.githubusercontent.com/48393773/73148594-b2540b80-4082-11ea-8742-8edf299132da.png)


## Extend high level requirements for the online retailer to add the following features:
* All accounts login information must be stored in SQL database (MySQL)
* All Customers transactions/orders must be stored in SQL database (MySQL)
* All order updates to insert/delete/update orders must be reflected in the MySQL database; not only the HashMap objects
* Customer must be able to submit product reviews

* Product reviews must be stored in NoSQL database (MongoDB) Add Trending & Data Analytics feature (detailed below)

* All new code added for MySQL shall be placed in a class called MySQLDataStoreUtilities.java
* All new code added for MongoDB shall be placed in a class called MongoDBDataStoreUtilities.java

### Required Functionalities:

#### to use MySQL and MongoDB database engines to support the following functionalities.
#### Use MySQL to store all accounts login information
#### Use MySQL to store All Customers transactions/orders

##### to allow the customer to write and submit a Product Review online that has the following form:
1. ProductModelName: Samsung Galaxy 6
2. ProductCategory: phone
3. ProductPrice: $499
4. RetailerName: SmartPortables
5. RetailerZip: 60616
6. RetailerCity: Chicago
7. RetailerState: IL
8. ProductOnSale: Yes
9. ManufacturerName: Samsung
10. ManufacturerRebate: Yes
11. UserID: whksa8
12. UserAge: 24
13. UserGender: Male
14. UserOccupation: accountant
15. ReviewRating: 4
16. ReviewDate: 12/15/2015
17. ReviewText: It has excellent video/audio clarity, however, it overheats after 5 hours of use

##### add Trending link on the left navigation bar that the user can use to see trends for sold products
##### Once the user clicks the Trending, the user must be presented with :
1. Top five most liked products
2. Top five zip-codes where maximum number of products sold
3. Top five most sold products regardless of the rating

### Product Review Form:
##### The product review Form has the following fields:
1. ProductModelName: Samsung Galaxy 6
2. ProductCategory: phone
3. ProductPrice: $499
4. RetailerName: SmartPortables
5. RetailerZip: 60616
6. RetailerCity: Chicago
7. RetailerState: IL
8. ProductOnSale: Yes
9. ManufacturerName: Samsung
10. ManufacturerRebate: Yes
11. UserID: whksa8
12. UserAge: 24
13. UserGender: Male
14. UserOccupation: accountant
15. ReviewRating: 4
16. ReviewDate: 12/15/2015
17. ReviewText: It has excellent video/audio clarity , however, it overheats after 5 hours of use

### Check Register table
![image](https://user-images.githubusercontent.com/48393773/73148832-e845bf80-4083-11ea-8d83-bae25f2631b9.png)
![image](https://user-images.githubusercontent.com/48393773/73148834-ea0f8300-4083-11ea-9464-90815f2ebcbb.png)
![image](https://user-images.githubusercontent.com/48393773/73148828-e5e36580-4083-11ea-9cf3-84e42632d961.png)

### Add products
![image](https://user-images.githubusercontent.com/48393773/73148837-ebd94680-4083-11ea-851b-8b71eafd382d.png)
![image](https://user-images.githubusercontent.com/48393773/73148838-eda30a00-4083-11ea-8b81-ef3678084823.png)
![image](https://user-images.githubusercontent.com/48393773/73148839-ef6ccd80-4083-11ea-9c02-a3134522efde.png)
![image](https://user-images.githubusercontent.com/48393773/73148844-f4318180-4083-11ea-84b2-be269da130e1.png)

### cutomer order operations
![image](https://user-images.githubusercontent.com/48393773/73148849-f98ecc00-4083-11ea-8cad-e222d686236e.png)
![image](https://user-images.githubusercontent.com/48393773/73148851-fb588f80-4083-11ea-9a77-1c7e0bd8c62e.png)
![image](https://user-images.githubusercontent.com/48393773/73148853-fd225300-4083-11ea-87ba-77b12bbd1a6b.png)

### history
![image](https://user-images.githubusercontent.com/48393773/73148855-ff84ad00-4083-11ea-8212-6093e726afbc.png)

### start MongoDB 
![image](https://user-images.githubusercontent.com/48393773/73148859-03b0ca80-4084-11ea-98a6-eb2e392a25b1.png)
![image](https://user-images.githubusercontent.com/48393773/73148861-057a8e00-4084-11ea-828b-09bb8ec99fa0.png)
![image](https://user-images.githubusercontent.com/48393773/73148866-07445180-4084-11ea-9818-50dfdab3ccff.png)


### Add product reviews
![image](https://user-images.githubusercontent.com/48393773/73148875-0b706f00-4084-11ea-900e-b974536499c3.png)
![image](https://user-images.githubusercontent.com/48393773/73148869-090e1500-4084-11ea-96fe-59cbd4a6296d.png)
![image](https://user-images.githubusercontent.com/48393773/73148879-0d3a3280-4084-11ea-955c-bb339029ef86.png)



## Add the Inventory and Sales Reports links that are accessible only to the Store Manager
##### Under the Inventory link, the store manager shall be able to:

1. Generate a table of all products and how many items of every product currently available in the store; list only product name, price, how many items of that product available
2. Generate a Bar Chart that shows the product names and the total number of items available for every product
3. Generate a table of all products currently on sale
4. Generate a table of all products currently that have manufacturer rebates

##### Under the Sales Report link, the store manager shall be able to:

1. Generate a table of all products sold and how many items of every product sold; list only product name, product price, number of items sold, and total sales of every product sold
2. Generate a Bar Chart that shows the product names and the total sales for every product
3. Generate a table of total daily sales transactions; that is, you list the dates and total sales for every day-date

![image](https://user-images.githubusercontent.com/48393773/73149124-7bcbc000-4085-11ea-9730-1240472bd3ef.png)
![image](https://user-images.githubusercontent.com/48393773/73149127-7d958380-4085-11ea-97ae-d858f0c2d32b.png)
![image](https://user-images.githubusercontent.com/48393773/73149128-7ff7dd80-4085-11ea-9e9c-566f5b7a9178.png)
![image](https://user-images.githubusercontent.com/48393773/73149130-81c1a100-4085-11ea-98cd-6c3f219e0eb8.png)
![image](https://user-images.githubusercontent.com/48393773/73149133-8423fb00-4085-11ea-8835-5f8644035c67.png)
![image](https://user-images.githubusercontent.com/48393773/73149136-86865500-4085-11ea-8622-45a68f51c3f8.png)
![image](https://user-images.githubusercontent.com/48393773/73149138-88501880-4085-11ea-8a0d-eb2260c44d3b.png)

## To add the Search Auto-Completion feature.

### Required Functionalities:
1. See below an illustration for a screen-shot of BestBuy web site for the Search Auto-Completion feature
2. Your auto-complete-feature must be implemented as follows: When the app-server starts up, the Products are first read into a hashmap from ProductCatalog.xml file and then stored in MySQL database; follow this sequence.
3. Since a store manager can insert/update/delete products, all of these operations must be reflected in the hashmap and then MySQL database
4. All new code added for the auto-complete-complete feature shall be placed in a class called AjaxUtility.java

![image](https://user-images.githubusercontent.com/48393773/73148094-0b6e7000-4080-11ea-9851-e5c78aff7926.png)
![image](https://user-images.githubusercontent.com/48393773/73148096-0f9a8d80-4080-11ea-9608-6d86dc91e6a0.png)

### Output Sceen Shot:
![image](https://user-images.githubusercontent.com/48393773/73149328-5f7c5300-4086-11ea-9681-a4b3a04bf4c9.png)
![image](https://user-images.githubusercontent.com/48393773/73149330-60ad8000-4086-11ea-9579-2a5685e671d4.png)
![image](https://user-images.githubusercontent.com/48393773/73149331-63a87080-4086-11ea-8a2e-4bdaab39f97b.png)

## to add two the following two features:
### Deal Match Guarantees feature
### Recommender feature

#### Required Functionalities for Deal Match Guarantees Feature:
See below an illustration for a screen-shot of SmartPortables web site for the required Deal Match feature

* Use the python script provided to create the DealMatches.txt File
* The python script will connect to Twitter server in order to get the current deals of BestBuy under the screen_name BestBuy_Deals

* The python script will connect to MySQL server for SmartPortables app in order to get the list of products
* The python script will compare the product names retrieved from MySQL server to the products names retrieved from screen_name BestBuy_Deals on Twitter server and it will write the tweets that have the products names that match the product names retrieved from MySQL server in the product table into DealMatches.txt file.

* When SmartPortables app is started by Tomcat server, it will read ANY 2 Tweets/lines from DealMatches.txt file and display them on SmartPortables app homepage along with links to the individual products that SmartPortables app can match of the offered/displayed deals by BestBuy on the homepage of SmartPortables app.

* All new code added for this feature shall be placed in a class called DealMacthes.java



### Our Approach to Implementing Recommender Systems :
For this Tutorial implementation , we have used Matrix Factorization technique for implementation which will be using Singular Value Decomposition (SVD) algorithm already implemented in the Surprise package.
* Python Version : 3.6
* Anaconda Version : 4.2 ( higher versions should be ok)


#### Matrix Factorization : The Basics
###### Consider below matrix of users as rows and items as columns.What matrix factorization does is to come up with two smaller matrices, one representing users and one representing items, which when multiplied together will roughly produce matrix of ratings, ignoring the 0 entries.

###### Let our matrix be mxn, where m is the number of users and n is the number of items.So, we need mxd and a dxn matrix as our factors, where d is chosen to be small enough for the computation to be efficient and large enough to represent the number of dimensions along which interactions between users and items are likely to vary in some significant way.If we choose d=2, the predicted rating given by a user for a given item is then the dot product of the vector representing the user and the vector representing the item.We also need to add the bias for item i, user u and overall average rating for predicting the rating.

###### Theoretically, the predicted rating is given by below formula :
Ref : http://katbailey.github.io/post/matrix-factorization-with-tensorflow/
###### Where, μ is the overall average rating, bi is the bias for item i, bu u, and is the interaction between item i and user u.
Ref : http://katbailey.github.io/post/matrix-factorization-with-tensorflow/


###### Matrix-factorization : API docsThe following class implements Matrix-factorization based prediction algorithms:
class surprise.prediction_algorithms.matrix_factorization.SVD 
###### The abstract class that defines the behavior of a prediction algorithm :
class surprise.prediction_algorithms.algo_base.AlgoBase

##### The famous SVD algorithm, as popularized by Simon Funk during the Netflix Prize uses it.







### Output Screen Shot:

### Twitter Deal
![image](https://user-images.githubusercontent.com/48393773/73148271-0827b400-4081-11ea-8fdd-f533e9013840.png)

### Deal_Match.txt
![image](https://user-images.githubusercontent.com/48393773/73148288-2ee5ea80-4081-11ea-9db4-62178ba072b2.png)

### Lenovo 130 and 330S
![image](https://user-images.githubusercontent.com/48393773/73148308-4cb34f80-4081-11ea-9a87-5c8a48eb2629.png)

### MongoData_train.csv
![image](https://user-images.githubusercontent.com/48393773/73148325-66549700-4081-11ea-8e28-6e38621fd768.png)

### MongoData_test.csv
![image](https://user-images.githubusercontent.com/48393773/73148329-6ce30e80-4081-11ea-8f74-cb6f04e53f69.png)

### Output.csv
![image](https://user-images.githubusercontent.com/48393773/73148363-99972600-4081-11ea-863b-d604ee9b0291.png)

### Mongo review
![image](https://user-images.githubusercontent.com/48393773/73148368-9e5bda00-4081-11ea-96bc-8ea33593a9e7.png)

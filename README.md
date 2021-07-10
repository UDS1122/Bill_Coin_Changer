# Bill_Coin_Changer
SpringBoot application to accept and chagne bills to coins.

1.	Ask is to build a demo application that will allow user to change bills into minimum available coins.
2.	Details about Endpoints : Once application is up and running then use following endpoints to access and test it on POSTMAN or Browser
  a.	http://localhost:8080/bill/change/{bill} : To get the change for the request {bill} i.e. 1, 2, 5, 10 etc.
  b.	http://localhost:8080/coins/all : Shows current status of different coins and their respective quantites
  c.	http://localhost:8080/coins/update/{bill} : Once we have exhausted the coins then we may want to re-fill so that we can continue to provide change for given bills
  d.	http://localhost:8080/shutdown : Once all coins have exhausted then either we may re-fill or we can shut down the application.

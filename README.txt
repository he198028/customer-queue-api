Customer Queue Management System
================================

This application exposes three end points

1. Add a customer

PUT /customers

Request body:    { "name": "Test",  
                  "phone": "+16041231234"
	                }

Response: Boolean


2. Get list of all customers in the queue

GET /customers

Response: List of all customers in the queue

e.g. { 
        { 
        "name": "Test1",  
        "phone": "+16041231234"
	       },
         {        
         "name": "Test2",  
         "phone": "+16041231235"
	       }      
      }
      
 3. Delete first customer from the queue
 
  DELETE  /customers

  Response: Long (time in milliseconds to service a single customer)
  
  

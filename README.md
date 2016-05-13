# Lora's Individual Project

###Problem Statement
I enjoy reading and do many of my family however I tend to lend my books or sell them back to buy more.  I want to recommend what I have read to my family but forget the titles at times.  If I had a list of the books that I have read and a place to put any note about what I think others should know and let them see it would be helpful.  I would like to be able to see what they have read too and if I do not know what I want to read next have a recommendation page to choose from.  I would also like to be able to keep a list of books I see on the site that I want to read at a later date.  If the book that I am looking at has a link to Barnes and Noble I am more likely to follow up and buy an eletronic copy easily.  


###Project Technologies/Techniques
* Security/Authentication
	- Admin role: create/read/update/delete all of the data
	- User role: submit books the have read, recommend books, add notes to books others have added
	- All: anyone can view the books, notes and recommendations
* Database (MySQL and Hibernate)
	- Store users and roles
	- Store book information
* Web Services or APIs
	- Used a soap webservice that gives you a new quote every time you access it
	- [Quote of the Day](http://www.swanandmokashi.com/HomePage/WebServices/QuoteOfTheDay.asmx?op=GetQuote)
* Logging
	- configure logging for production to help with errors and debugging
* Site and database hosted on OpenShift
* Unit Testing
	- Junit tests
* Independent Research Topic
	-Maven for building


###Design
* [Screen Design](DesignDocuments/ScreenDesign.md)
* [Application Flow](DesignDocuments/ApplicationFlow.md)
* [Database Design](DesignDocuments/DatabaseDesign.md)

###[Project Plan](ProjectPlan.md)


###[Development Journal](Journal.md)

## Context on the tester: 
- I am a product manager with limited experience writing code. 
- Prior to going through this exercise, I reviewed Stripe’s API documents to see sample code and checklists for getting started.
- In this document, I am detailing my experience of completing a PaymentIntents API integration using Stripe’s online resources. This document shares highlights and friction I encountered during the process. 

## My process: 
- I reviewed Stripe’s “Accept a payment” documentation
- I downloaded Stripe CLI for macOS. 
- I came back to the top of said doc and clicked into the immersive version to find an integration builder
- I selected web as my platform, React for the frontend, and Java backend. This automatically populated code into the builder for me to review. 
- I created an account with Stripe and logged into the dashboard. I browsed around the dashboard to view available features, my API test keys, and set up my business (Paul’s Hot Salsa). 
- Once I realized I needed to be logged in, I reconfigured settings in the implementation builder, reviewed the resulting lines of code, and downloaded the project to begin making adjustments to the code. 
- I used Stripe’s CLI to log into my project downloaded from the integration builder. 
- I spent most of my time on the integration and this friction log and very little time was spent on the actual website itself as my focus was completing the PaymentIntents integration. 

###Log

**Good**

_Documentation_

The availability of searchable documentation online and organization into checklists give me confidence to be able to tackle the integration. I don’t feel like the task to implement a PaymentIntents integration will be daunting. 

**Friction**

_Getting Stripe account_

I was told to follow along with the Stripe documentation which linked directly to “Accept a payment”. I was not aware that I had to create and activate a Stripe account first before going down this path. After I realized this, I created my account and came back. 

**Friction**

_Getting started_

As a user unfamiliar with writing code, it would be helpful to understand the minimum criteria required to get started, even if I’m using the interactive integration builder, such as tools to have available on my machine before jumping into the process of the PaymentIntents integration. I chose to go down the path of a Java backend but wasn’t sure what version I would need on my laptop. I had to figure this part out in order to get properly set up on Java. Essentially, while the Stripe documentation is written in mind for a developer, it would be helpful to inform the user what tools are needed in order to get started. There’s a lot of information available in various forms but I need help figuring out the basics of where to start.


**Good**

_Integration Builder when logged in_

I appreciate that when I am logged into Stripe’s dashboard, the interactive integration builder will populate my test API key so that I don’t need to search for it in the dashboard to copy and paste its correct home into the builder’s code. 

![API test key](https://github.com/mgdesiderio/Stripe-PM/blob/master/images/image_1.png)

**Friction**

_Configuring price for item in server_

I’m attempting to create a price of $12.00 USD. In my return line, I wasn’t sure if I needed to set it up as 12.00 or 12. I later realized my initial attempt of “12.00” was incorrect and needed an integer. 

**Friction**

_Accounting for the Stripe fee_

As an assumed business owner, I want $12 USD per item purchased. I did not realize I needed to account for a Stripe fee. To account for this, I would need to know the fee estimation and calculate this in the code. I decided to leave this as is and placed more emphasis on the completeness of the integration as a whole.

**Friction**

_Step 5 Testing payments for 3DS use cases_

Once I successfully completed the backend and front end steps and rendered the UI locally, I began to test the three main use cases: successful payment, 3DS complete and 3DS failed, and insufficient funds. I was able to get the messages I expected in the UI; however, when I referred back to Step 5 of the API doc and clicked “Check Payments”, the UI made it seem like I did not successfully complete the tests. I got stuck on this for a while, checked back in the code, and tried to figure out what was wrong. I ran another payment test again locally and checked Step 5, and everything looked cleared. It was confusing to me that this UI was possibly only pulling information from my last tested payment. I found this to be really confusing and had spent quite a bit of time reviewing server code again. I decided to move on from this task as it looked like my payment tests were, in fact, successful after having rechecked the payments in Step 5 again. It might make sense to add the test scenario of failing 3DS authorization. 

![Step 5 test scenario](https://github.com/mgdesiderio/Stripe-PM/blob/master/images/image_2.png)

_“Authenticated the payment” is confusing as success criteria for testing the 3DS scenarios. If I am testing the 3DS failure case, I would expect to not authenticate the payment. It seems like this step only includes the purposely successful test scenarios but not the failure experiences._

![Step 5 success](https://github.com/mgdesiderio/Stripe-PM/blob/master/images/image_3.png)



**Good**

_Viewing tested payment intents in Stripe dashboard_
 
Helpful to see a log and time stamp of all attempted payments in the Stripe dashboard. I know that there is a single place to find this information. 

**Good**

_Dynamic updates to API document_

I like that when I am logged into Stripe’s dashboard, as I work through an integration, the steps update to show me what I have completed in the documentation. This is helpful when I take breaks from the work so that I don’t lose my place.

![Step confirmation](https://github.com/mgdesiderio/Stripe-PM/blob/master/images/image_4.png)

**Friction**

_Logging successful payments to a log file_

I am attempting to create a registry of successful payments. I think I should be using the payment_intent.succeeded event but not sure how I leverage that event to log to a file. Once I successfully set up the web hook and copied code to log successful payments with payment_intent.succeeded events, I assumed that a log file would be created. I quickly realized I needed to have one ready in the same directory in order to actively log to it. I created a log file called “log.txt” and made updates to append said file with the payment_intent.succeeded event. I then re-tested the three main test scenarios to confirm that successful payments were logging to the log.txt.  
